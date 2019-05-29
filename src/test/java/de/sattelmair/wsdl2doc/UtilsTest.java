package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.domain.Datatype;
import de.sattelmair.wsdl2doc.service.WSDLImportService;
import de.sattelmair.wsdl2doc.service.impl.WSDLImportServiceImpl;
import de.sattelmair.wsdl2doc.utills.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.xml.namespace.QName;
import java.util.HashSet;
import java.util.Set;

public class UtilsTest {

    private static final String WSDL_FILE_PATH_1 = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid1.wsdl";
    private static final String WSDL_FILE_PATH_2 = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid2.wsdl";
    private static final String WSDL_FILE_PATH_3 = "src/test/resources/de/sattelmair/wsdl2doc/wsdl/valid3.wsdl";

    @Test
    public void testGetMessageQNames_1() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Set<QName> messageQNames = Utils.getMeesageQNames(wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_1));

        Assert.assertEquals(1, messageQNames.size());
    }

    @Test
    public void testGetMessageQNames_2() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Set<QName> messageQNames = Utils.getMeesageQNames(wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_2));

        Assert.assertEquals(1, messageQNames.size());
    }

    @Test
    public void testGetMessageQNames_3() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Set<QName> messageQNames = Utils.getMeesageQNames(wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_3));

        Assert.assertEquals(10, messageQNames.size());
    }

    @Test
    public void testGetDatatypes_1() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_1);
        final Set<Datatype> datatypes = Utils.getDatatypes(description, Utils.getMeesageQNames(description), new HashSet<Datatype>());

        Assert.assertEquals(0, datatypes.size());
    }

    @Test
    public void testGetDatatypes_2() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_2);
        final Set<Datatype> datatypes = Utils.getDatatypes(description, Utils.getMeesageQNames(description), new HashSet<Datatype>());

        Assert.assertEquals(1, datatypes.size());
    }

    @Test
    public void testGetDatatypes_3() throws WSDLImportException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(WSDL_FILE_PATH_3);
        final Set<Datatype> datatypes = Utils.getDatatypes(description, Utils.getMeesageQNames(description), new HashSet<Datatype>());

        Assert.assertEquals(18, datatypes.size());
    }
}
