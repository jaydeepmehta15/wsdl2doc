package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.service.WSDLImportService;
import de.sattelmair.wsdl2doc.service.impl.WSDLImportServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WSDLImportServiceTest {

    private static final String VALID_WSDL_FILE_PATH = "de/sattelmair/wsdl2doc/wsdl/valid.wsdl";

    @DisplayName("Read WSDL from file path")
    @Test
    public void test_should_read_wsdl_from_file_path_string_correctly() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();

        final Description description = wsdlImportService.readWSDLFromFile(new ClassPathResource(VALID_WSDL_FILE_PATH).getFile().getAbsolutePath());
        System.out.println(description);
    }

    @DisplayName("Read WSDL from URL string")
    @Test
    public void test_should_read_wsdl_from_url_string_correctly() throws WSDLImportException, IOException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();

        final Description description = wsdlImportService.readWSDLFromURL("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL");
        System.out.println(description);
    }

}
