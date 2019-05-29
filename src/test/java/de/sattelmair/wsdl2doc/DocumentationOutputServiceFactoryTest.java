package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.factory.DocumentationOutputServiceFactory;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.impl.HTMLDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.MarkdownDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.PDFDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.WordDocumentationOutputServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class DocumentationOutputServiceFactoryTest {

    @Test
    public void testDocumentationOutputServiceCreation() {
        Assert.assertTrue(DocumentationOutputServiceFactory.createDocumentationOutputService(null) instanceof PDFDocumentationOutputServiceImpl);
        Assert.assertTrue(DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.PDF) instanceof PDFDocumentationOutputServiceImpl);
        Assert.assertTrue(DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.WORD) instanceof WordDocumentationOutputServiceImpl);
        Assert.assertTrue(DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.MARKDOWN) instanceof MarkdownDocumentationOutputServiceImpl);
        Assert.assertTrue(DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.HTML) instanceof HTMLDocumentationOutputServiceImpl);
    }
}