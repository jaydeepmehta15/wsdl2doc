package de.sattelmair.wsdl2doc;

import com.itextpdf.text.pdf.PdfReader;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.WSDLImportService;
import de.sattelmair.wsdl2doc.service.impl.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;
import org.junit.Test;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class WSDLDocumentationGeneratorTest {

    private static final String WSDL_FILE_PATH = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid3.wsdl";

    @Test
    public void markdown_documentation_creator_test() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH);

        final DocumentationOutputService documentationOutputService = new MarkdownDocumentationOutputServiceImpl();
        final File example = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_markdown.md");
        Assert.assertArrayEquals(Files.readAllBytes(example.toPath()), documentationOutputService.generateDocumentation(description));
    }

    @Test
    public void html_documentation_creator_test() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH);

        final DocumentationOutputService documentationOutputService = new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl());
        final File example = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_html.html");
        Assert.assertArrayEquals(Files.readAllBytes(example.toPath()), documentationOutputService.generateDocumentation(description));
    }

    @Test
    public void pdf_documentation_creator_test() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH);

        final DocumentationOutputService documentationOutputService = new PDFDocumentationOutputServiceImpl(new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl(false)));
        final PdfReader expected = new PdfReader(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_pdf.pdf").toPath().toString());
        final PdfReader actual = new PdfReader(documentationOutputService.generateDocumentation(description));

        final int numberOfPagesExample = expected.getNumberOfPages();
        final int numberOfPagesResult = actual.getNumberOfPages();
        Assert.assertEquals(numberOfPagesExample, numberOfPagesResult);

        for(int i=1; i <= numberOfPagesExample; i++) {
            Assert.assertArrayEquals(expected.getPageContent(i), actual.getPageContent(i));
        }

        actual.close();
        expected.close();
    }

    @Test
    public void word_documentation_creator_test() throws WSDLImportException, IOException, InvalidFormatException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH);
        final DocumentationOutputService documentationOutputService = new WordDocumentationOutputServiceImpl(new HTMLDocumentationOutputServiceImpl(new MarkdownDocumentationOutputServiceImpl()));

        final XWPFDocument expected = new XWPFDocument(OPCPackage.open(new FileInputStream(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_word.docx"))));
        final XWPFDocument actual = new XWPFDocument(OPCPackage.open(new ByteArrayInputStream(documentationOutputService.generateDocumentation(description))));

        final XWPFWordExtractor expectedExtractor = new XWPFWordExtractor(expected);
        final XWPFWordExtractor actualExtractor = new XWPFWordExtractor(actual);

        Assert.assertEquals(expectedExtractor.getText(), actualExtractor.getText());

        actual.close();
        expected.close();
    }
}
