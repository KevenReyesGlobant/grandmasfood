package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.generated;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GeneratedPdfBox {
    public static void saveDocument() throws IOException {
        PDDocument document = new PDDocument();
        PDRectangle pageSize = new PDRectangle(595, 842);
        PDPage myPage = new PDPage(pageSize);
        document.addPage(myPage);
        String[] Details = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        int pageWidth = (int) myPage.getTrimBox().getWidth();
        int pageHeight = (int) myPage.getTrimBox().getHeight();
        PDFont fontType = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream content = new PDPageContentStream(document, myPage);

        //Create background
        PDImageXObject imageBack = PDImageXObject.createFromFile("C:\\Users\\keven.reyes\\Downloads\\background.jpg", document);
        content.drawImage(imageBack, 0, 0);
        content.close();

        try {
            document.save("C:\\Users\\keven.reyes\\Downloads\\menu.pdf");
            System.out.println("Document saved");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
