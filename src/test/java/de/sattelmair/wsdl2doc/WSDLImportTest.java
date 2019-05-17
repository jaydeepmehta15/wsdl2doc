package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.WSDLImportService;
import de.sattelmair.wsdl2doc.service.impl.MarkdownDocumentationOutputServiceImpl;
import de.sattelmair.wsdl2doc.service.impl.WSDLImportServiceImpl;
import org.junit.Test;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WSDLImportTest {

    private static final String WSDL_FILE_PATH = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid3.wsdl";

    @Test
    public void test() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH);

        final DocumentationOutputService documentationOutputService = new MarkdownDocumentationOutputServiceImpl();
        File file = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output.md");
        Files.write(file.toPath(), documentationOutputService.generateDocumentation(description));
    }
}
