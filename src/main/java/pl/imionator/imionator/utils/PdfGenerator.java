package pl.imionator.imionator.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.Sex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PdfGenerator {


    public ByteArrayInputStream generatePdf(List<Name> names) {
        Document document = new Document(setDocumentSizeAndBackgroundColor(names));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Font headerFont = generateHeaderFont(), bodyFont = generateBodyFont();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(generateNamesListTitle(headerFont));
            for (Name name : names) {
                document.add(setNameStyling(name, bodyFont));
            }
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private Rectangle setDocumentSizeAndBackgroundColor(List<Name> names) {
        Rectangle rectangle = new Rectangle(400, 600);
        BaseColor color = names.size() == 0 ? new BaseColor(255, 255, 255) :
                names.get(0).getSex() == Sex.BOY ? new BaseColor(224, 255, 255)
                        : new BaseColor(255, 228, 225);
        rectangle.setBackgroundColor(color);
        return rectangle;
    }

    private Font generateHeaderFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                15, Font.BOLD, BaseColor.GRAY);
    }

    private Font generateBodyFont() throws DocumentException, IOException {
        return new Font(BaseFont.createFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),
                13, Font.NORMAL, BaseColor.GRAY);
    }

    private Paragraph generateNamesListTitle(Font font) {
        Paragraph namesListTitle = new Paragraph("Imiona wylosowane z listy:", font);
        namesListTitle.setAlignment(Element.ALIGN_CENTER);
        namesListTitle.setSpacingBefore(7);
        namesListTitle.setSpacingAfter(7);
        return namesListTitle;
    }

    private Paragraph setNameStyling(Name name, Font font) {
        Paragraph paragraph = new Paragraph(name.getFirstName(), font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

}
