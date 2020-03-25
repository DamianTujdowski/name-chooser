package pl.imionator.imionator.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.Sex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class PdfGenerator {


    public ByteArrayInputStream generatePdf(Map<String, Long> drawnNamesFromUserInputStatistics, List<Name> namesDrawnFromPropositionLists) {
        Document document = new Document(setDocumentSizeAndBackgroundColor(namesDrawnFromPropositionLists));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Font headerFont = generateHeaderFont(), bodyFont = generateBodyFont();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            if (drawnNamesFromUserInputStatistics.size() > 0) {
                document.add(generateDrawingFromUserInputStatisticsTitle(headerFont));
                document.add(generateTableFromUserInputStatistics(drawnNamesFromUserInputStatistics, headerFont, bodyFont));
            }
            if (namesDrawnFromPropositionLists.size() > 0) {
                document.add(generateNamesDrawnFromPropositionListsTitle(headerFont));
                document.add(generateTableFromPropositionListStatistics(namesDrawnFromPropositionLists, bodyFont));
            }
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private PdfPTable generateTableFromUserInputStatistics(Map<String, Long> drawnNamesFromUserInputStatistics, Font headerFont, Font bodyFont) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(60);

        table.addCell(generateHeaderCell("Imię", headerFont));
        table.addCell(generateHeaderCell("Ilość wylosowań", headerFont));

        drawnNamesFromUserInputStatistics.forEach((name, occurrence) -> {
            table.addCell(generateBodyCell(name, bodyFont));
            table.addCell(generateBodyCell(occurrence.toString(), bodyFont));
        });
        return table;
    }

    private PdfPTable generateTableFromPropositionListStatistics(List<Name> namesDrawnFromPropositionList, Font bodyFont) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(60);
        namesDrawnFromPropositionList.forEach(name -> table.addCell(generateBodyCell(name.getFirstName(), bodyFont)));
        return table;
    }

    private PdfPCell generateHeaderCell(String title, Font headerFont) {
        PdfPCell headerCell = new PdfPCell(new Phrase(title, headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setBorder(Rectangle.NO_BORDER);
        return headerCell;
    }

    private PdfPCell generateBodyCell(String data, Font bodyFont) {
        PdfPCell cell = new PdfPCell(new Phrase(data, bodyFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private Rectangle setDocumentSizeAndBackgroundColor(List<Name> names) {
        Rectangle rectangle = new Rectangle(550, 800);
        BaseColor color = names.size() == 0 ? new BaseColor(255, 255, 255) :
                names.get(0).getSex() == Sex.BOY ? new BaseColor(224, 255, 255)
                        : new BaseColor(255, 228, 225);
        rectangle.setBackgroundColor(color);
        return rectangle;
    }

    private Font generateHeaderFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                11, Font.BOLD, BaseColor.GRAY);
    }

    private Font generateBodyFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                11, Font.NORMAL, BaseColor.GRAY);
    }

    private Paragraph generateDrawingFromUserInputStatisticsTitle(Font headerFont) {
        Paragraph userInputTitle = new Paragraph("Imiona wylosowane spośród imion podanych przez Ciebie:", headerFont);
        userInputTitle.setAlignment(Element.ALIGN_CENTER);
        userInputTitle.setSpacingBefore(5);
        userInputTitle.setSpacingAfter(10);
        return userInputTitle;
    }

    private Paragraph generateNamesDrawnFromPropositionListsTitle(Font headerFont) {
        Paragraph propositionListTitle = new Paragraph("Imiona wylosowane spośród propozycji:", headerFont);
        propositionListTitle.setAlignment(Element.ALIGN_CENTER);
        propositionListTitle.setSpacingBefore(10);
        propositionListTitle.setSpacingAfter(5);
        return propositionListTitle;
    }

}
