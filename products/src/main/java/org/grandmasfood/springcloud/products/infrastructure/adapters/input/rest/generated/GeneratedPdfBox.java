package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.generated;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

public class GeneratedPdfBox {
    public void saveDocument() {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage();
        document.addPage(firstPage);
        try {
            document.save("C:\\Users\\keven.reyes\\Downloads\\document.pdf");
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
