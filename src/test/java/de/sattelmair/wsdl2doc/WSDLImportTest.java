package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.service.WSDLImportService;
import de.sattelmair.wsdl2doc.service.impl.WSDLImportServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WSDLImportTest {

    private static final String WSDL_FILE_PATH = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid3.wsdl";
    private static final String WSDL_URL_PATH = "http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl";

    @Test(expected = WSDLImportException.class)
    public void testWSDLImportFromFileObjectWrongFilePath() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        wsdlImportService.readWSDLFromFile(new File("de/de/de"));
    }

    @Test
    public void testWSDLImportFromFileObject() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        Assert.assertNotNull(wsdlImportService.readWSDLFromFile(new File(WSDL_FILE_PATH)));
    }

    @Test(expected = WSDLImportException.class)
    public void testWSDLImportFromFilePathWrongFilePath() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        wsdlImportService.readWSDLFromFile("de/de/de");
    }

    @Test
    public void testWSDLImportFromFilePath() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        Assert.assertNotNull(wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH));
    }

    @Test(expected = WSDLImportException.class)
    public void testWSDLImportFromURLObjectWrongURL() throws WSDLImportException, MalformedURLException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        wsdlImportService.readWSDLFromURL(new URL("http://www.google.de"));
    }

    @Test
    public void testWSDLImportFromURLObject() throws WSDLImportException, MalformedURLException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        Assert.assertNotNull(wsdlImportService.readWSDLFromURL(new URL(WSDL_URL_PATH)));
    }

    @Test(expected = WSDLImportException.class)
    public void testWSDLImportFromURLStringWrongURL() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        wsdlImportService.readWSDLFromURL("de/de/de");
    }

    @Test
    public void testWSDLImportFromURLString() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        Assert.assertNotNull(wsdlImportService.readWSDLFromURL(WSDL_URL_PATH));
    }

}
