package de.sattelmair.wsdl2doc;

import com.itextpdf.text.pdf.PdfReader;
import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.WSDL2DocService;
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

import javax.activation.UnsupportedDataTypeException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;

public class WSDL2DocServiceTest {

    private static final String WSDL_FILE_PATH = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid3.wsdl";
    private static final String WSDL_URL_PATH = "http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl";

    @Test
    public void wsdl2DocService_incompatible_result_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final File expected = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_markdown.md");
        Assert.assertFalse(Arrays.equals(Files.readAllBytes(expected.toPath()), wsdl2DocService.generateDocumentationFromFile(new File(WSDL_FILE_PATH), OutputFormat.PDF.name())));

    }

    @Test
    public void wsdl2DocService_markdown_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final File expected = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_markdown.md");
        Assert.assertArrayEquals(Files.readAllBytes(expected.toPath()), wsdl2DocService.generateDocumentationFromFile(WSDL_FILE_PATH, OutputFormat.MARKDOWN.name()));
    }

    @Test
    public void wsdl2DocService_html_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final File example = new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_html.html");
        Assert.assertArrayEquals(Files.readAllBytes(example.toPath()), wsdl2DocService.generateDocumentationFromURL(new URL(WSDL_URL_PATH), OutputFormat.HTML.name()));
    }

    @Test
    public void wsdl2DocService_pdf_outputtype_pdf_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final PdfReader expected = new PdfReader(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_pdf.pdf").toPath().toString());
        final PdfReader actual = new PdfReader(wsdl2DocService.generateDocumentationFromURL(WSDL_URL_PATH, OutputFormat.PDF.name()));

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
    public void wsdl2DocService_pdf_outputtype_null_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final PdfReader expected = new PdfReader(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_pdf.pdf").toPath().toString());
        final PdfReader actual = new PdfReader(wsdl2DocService.generateDocumentationFromFile(WSDL_FILE_PATH, null));

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
    public void wsdl2DocService_pdf_outputtype_unknown_test() throws WSDLImportException, IOException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final PdfReader expected = new PdfReader(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_pdf.pdf").toPath().toString());
        final PdfReader actual = new PdfReader(wsdl2DocService.generateDocumentationFromFile(WSDL_FILE_PATH, "sdadsaas"));

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
    public void wsdl2DocService_word_test() throws WSDLImportException, IOException, InvalidFormatException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();

        final XWPFDocument expected = new XWPFDocument(OPCPackage.open(new FileInputStream(new File("src/test/resources/de/sattelmair/wsdl2doc/output/output_word.docx"))));
        final XWPFDocument actual = new XWPFDocument(OPCPackage.open(new ByteArrayInputStream(wsdl2DocService.generateDocumentationFromFile(WSDL_FILE_PATH, OutputFormat.WORD.name()))));

        final XWPFWordExtractor expectedExtractor = new XWPFWordExtractor(expected);
        final XWPFWordExtractor actualExtractor = new XWPFWordExtractor(actual);

        Assert.assertEquals(expectedExtractor.getText(), actualExtractor.getText());

        actual.close();
        expected.close();
    }

}
