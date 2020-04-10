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
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class PdfGenerator {


    public ByteArrayInputStream generatePdf(Map<String, Long> namesFromUserInputStatistics, List<Name> namesDrawnFromPropositionLists) {
        Document document = new Document(setDocumentSizeAndBackgroundColor());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Font headerFont = generateHeaderFont(), bodyFont = generateBodyFont();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            if (namesFromUserInputStatistics.size() > 0) {
                document.add(generateTitleForNamesFromUserInputStatistics(headerFont));
                document.add(generateTableForNamesFromUserInputStatistics(namesFromUserInputStatistics, headerFont, bodyFont));
            }
            if (namesDrawnFromPropositionListBySex(namesDrawnFromPropositionLists, name -> name.getSex() == Sex.GIRL).size() > 0) {
                document.add(generateTitleForNamesDrawnFromPropositionLists(new Paragraph("Dziewczęce imiona wylosowane spośród propozycji:", headerFont)));
                document.add(generateTableForNamesDrawnFromPropositionListStatistics(
                        namesDrawnFromPropositionListBySex(namesDrawnFromPropositionLists, name -> name.getSex() == Sex.GIRL), bodyFont));
            }
            if (namesDrawnFromPropositionListBySex(namesDrawnFromPropositionLists, name -> name.getSex() == Sex.BOY).size() > 0) {
                document.add(generateTitleForNamesDrawnFromPropositionLists(new Paragraph("Chłopięce imiona wylosowane spośród propozycji:", headerFont)));
                document.add(generateTableForNamesDrawnFromPropositionListStatistics(
                        namesDrawnFromPropositionListBySex(namesDrawnFromPropositionLists, name -> name.getSex() == Sex.BOY), bodyFont));
            }
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private PdfPTable generateTableForNamesFromUserInputStatistics(Map<String, Long> drawnNamesFromUserInputStatistics, Font headerFont, Font bodyFont) {
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

    private PdfPTable generateTableForNamesDrawnFromPropositionListStatistics(List<Name> namesDrawnFromPropositionList, Font bodyFont) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(60);
        namesDrawnFromPropositionList.forEach(name -> table.addCell(generateBodyCell(name.getFirstName(), name.getSex(), bodyFont)));
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

    private PdfPCell generateBodyCell(String data, Sex sex, Font bodyFont) {
        PdfPCell cell = new PdfPCell(new Phrase(data, bodyFont));
        if (sex == Sex.BOY) {
            cell.setBackgroundColor(new BaseColor(0, 151, 255));
        } else if (sex == Sex.GIRL) {
            cell.setBackgroundColor(new BaseColor(170, 0, 204));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private Rectangle setDocumentSizeAndBackgroundColor() {
        Rectangle rectangle = new Rectangle(550, 800);
        rectangle.setBackgroundColor(new BaseColor(0, 204, 204));
        return rectangle;
    }

    private Font generateHeaderFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                12, Font.BOLD, BaseColor.WHITE);
    }

    private Font generateBodyFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                12, Font.NORMAL, BaseColor.WHITE);
    }

    private Paragraph generateTitleForNamesFromUserInputStatistics(Font headerFont) {
        Paragraph userInputTitle = new Paragraph("Imiona wylosowane spośród imion podanych przez Ciebie:", headerFont);
        userInputTitle.setAlignment(Element.ALIGN_CENTER);
        userInputTitle.setSpacingBefore(5);
        userInputTitle.setSpacingAfter(10);
        return userInputTitle;
    }

    private Paragraph generateTitleForNamesDrawnFromPropositionLists(Paragraph paragraph) {
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(5);
        return paragraph;
    }

    private List<Name> namesDrawnFromPropositionListBySex(List<Name> names, Predicate<Name> predicate) {
        return names.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
