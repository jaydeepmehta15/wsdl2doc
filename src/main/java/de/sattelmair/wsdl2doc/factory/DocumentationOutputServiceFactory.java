package de.sattelmair.wsdl2doc.factory;

import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.impl.HTMLDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.MarkdownDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.PDFDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.WordDocumentationOutputServiceImpl;

public class DocumentationOutputServiceFactory {

    private DocumentationOutputServiceFactory() {}

    public static DocumentationOutputService createDocumentationOutputService(final OutputFormat outputFormat) {
        switch (outputFormat) {
            case PDF: return new PDFDocumentationOutputServiceImpl(new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl(false)));
            case HTML: return new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl());
            case WORD: return new WordDocumentationOutputServiceImpl();
            case MARKDOWN: return new MarkdownDocumentationOutputServiceImpl();
            default: return new PDFDocumentationOutputServiceImpl(new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl(false)));   //Should not happen! Default is PDF output.
        }
    }

}
