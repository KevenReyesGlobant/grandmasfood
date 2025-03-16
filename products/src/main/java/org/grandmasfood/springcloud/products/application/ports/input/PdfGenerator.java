package org.grandmasfood.springcloud.products.application.ports.input;

import java.io.IOException;

public interface PdfGenerator {
    void saveDocument() throws IOException;
}

