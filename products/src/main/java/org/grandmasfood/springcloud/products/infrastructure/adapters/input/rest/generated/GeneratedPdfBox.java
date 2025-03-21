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
import org.grandmasfood.springcloud.products.domain.model.enums.Category;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.mapper.ProductMapper;
import org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository.ProductsRepositoy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        PDRectangle pageSize = new PDRectangle(595, 842);
        PDPage myPage = new PDPage(pageSize);
        document.addPage(myPage);

        List<Product> products = productMapper.toProductList(productsRepositoy.findAll());
        Map<Category, List<Product>> productsByCategory = products.stream()
                .filter(Product::isAvailable)
                .collect(Collectors.groupingBy(Product::getCategory));


        int pageWidth = (int) myPage.getTrimBox().getWidth();
        int pageHeight = (int) myPage.getTrimBox().getHeight();
        PDFont fontType = PDType1Font.COURIER_BOLD_OBLIQUE;
        PDPageContentStream content = new PDPageContentStream(document, myPage);

        try {
            PDImageXObject imageBack = PDImageXObject.createFromFile("C:\\Users\\keven.reyes\\Downloads\\Background.png", document);
            content.drawImage(imageBack, 0, 0, pageWidth, pageHeight + 50);

            int yOffset = pageHeight - 160;
            int columnWidth = pageWidth / 2 - 50;

            for (Map.Entry<Category, List<Product>> entry : productsByCategory.entrySet()) {
                Category category = entry.getKey();
                List<Product> categoryProducts = entry.getValue();
                content.beginText();
                content.setFont(fontType, 14);
                if (isValidRGB(41, 161, 61)) {
                    content.setNonStrokingColor(new Color(49, 75, 57));
                } else {
                    content.setNonStrokingColor(Color.BLACK);  // Fallback color
                }
                content.newLineAtOffset((pageWidth - fontType.getStringWidth(category.name()) / 1000 * 20) / 2, yOffset);
                content.showText(category.name());
                content.endText();
                yOffset -= 30;

                int half = (categoryProducts.size() + 1) / 2;
                int xOffset = 50;
                int initialYOffset = yOffset;

                for (int i = 0; i < categoryProducts.size(); i++) {
                    if (i == half) {
                        xOffset = columnWidth + 100;
                        yOffset = initialYOffset;
                    }
                    Product product = categoryProducts.get(i);
                    content.beginText();
                    content.setFont(fontType, 12);
                    if (isValidRGB(41, 161, 61)) {
                        content.setNonStrokingColor(new Color(49, 75, 57));
                    } else {
                    }
                    double value = product.getPrice() + product.getPrice() * 0.19;
                    content.newLineAtOffset(xOffset, yOffset);
                    content.showText(String.format("%s -> $%.1f", product.getFantasyName(), value));
                    content.endText();
                    yOffset -= 20;
                }
                yOffset -= 60;
            }

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

    private boolean isValidRGB(int red, int green, int blue) {
        return isValidColorValue(red) && isValidColorValue(green) && isValidColorValue(blue);
    }

    private boolean isValidColorValue(int value) {
        return value >= 0 && value <= 255;
    }
}