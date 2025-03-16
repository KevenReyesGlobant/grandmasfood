package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.generated;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.grandmasfood.springcloud.products.application.ports.input.PdfGenerator;
import org.grandmasfood.springcloud.products.domain.model.Product;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.mapper.ProductMapper;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository.ProductsRepositoy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneratedPdfBox implements PdfGenerator {

    private final ProductsRepositoy productsRepositoy;
    private final ProductMapper productMapper;

    public GeneratedPdfBox(ProductsRepositoy productsRepositoy, ProductMapper productMapper) {
        this.productsRepositoy = productsRepositoy;
        this.productMapper = productMapper;
    }

    @Override
    public void saveDocument() throws IOException {
        PDDocument document = new PDDocument();
        PDRectangle pageSize = new PDRectangle(595, 842);  // A4 Size
        PDPage myPage = new PDPage(pageSize);
        document.addPage(myPage);

        List<Product> products = productMapper.toProductList(productsRepositoy.findAll());
        String[] details = products.stream()
                .map(product -> product.getFantasyName() + "       ->     $" + product.getPrice())
                .collect(Collectors.toList())
                .toArray(new String[0]);

        int pageWidth = (int) myPage.getTrimBox().getWidth();
        int pageHeight = (int) myPage.getTrimBox().getHeight();
        PDFont fontType = PDType1Font.COURIER_BOLD_OBLIQUE;
        PDPageContentStream content = new PDPageContentStream(document, myPage);

        try {
            // Create the background of the page
            PDImageXObject imageBack = PDImageXObject.createFromFile("C:\\Users\\keven.reyes\\Downloads\\Background.jpg", document);
            content.drawImage(imageBack, 0, 0, pageWidth, pageHeight+50);

            // Food image
            PDImageXObject imageFood = PDImageXObject.createFromFile("C:\\Users\\keven.reyes\\Downloads\\Background.jpg", document);
            content.drawImage(imageFood, pageWidth / 1, pageHeight - 468, 200, 200);


            // Write text in two columns
            content.beginText();
            content.setFont(fontType, 16);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(50, pageHeight - 280);
            int half = details.length / 2;
            for (int i = 0; i < half; i++) {
                content.showText(details[i]);
                content.newLineAtOffset(0, -20);
            }
            content.endText();

            content.beginText();
            content.setFont(fontType, 16);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(pageWidth / 2 + 50, pageHeight - 280);
            for (int i = half; i < details.length; i++) {
                content.showText(details[i]);
                content.newLineAtOffset(0, -20);
            }
            content.endText();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            content.close();
            try {
                document.save("C:\\Users\\keven.reyes\\Downloads\\menu.pdf");
                System.out.println("Document saved successfully");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
        }
    }
}