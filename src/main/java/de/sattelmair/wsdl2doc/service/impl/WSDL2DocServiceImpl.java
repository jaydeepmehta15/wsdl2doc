package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.factory.DocumentationOutputServiceFactory;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import de.sattelmair.wsdl2doc.service.WSDL2DocService;
import de.sattelmair.wsdl2doc.service.WSDLImportService;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.activation.UnsupportedDataTypeException;
import java.io.File;
import java.net.URL;

public class WSDL2DocServiceImpl implements WSDL2DocService {

    @Override
    public byte[] generateDocumentationFromURL(final String url, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromURL(url);

        final DocumentationOutputService documentationOutputService =
                DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.getOutputFormat(outputFormat));

        return documentationOutputService.createDocumentation(description);
    }

    @Override
    public byte[] generateDocumentationFromURL(final URL url, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromURL(url);

        final DocumentationOutputService documentationOutputService =
                DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.getOutputFormat(outputFormat));

        return documentationOutputService.createDocumentation(description);
    }

    @Override
    public byte[] generateDocumentationFromFile(final String filePath, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(filePath);

        final DocumentationOutputService documentationOutputService =
                DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.getOutputFormat(outputFormat));

        return documentationOutputService.createDocumentation(description);
    }

    @Override
    public byte[] generateDocumentationFromFile(final File file, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException {
        final WSDLImportService wsdlImportService = new WSDLImportServiceImpl();
        final Description description = wsdlImportService.readWSDLFromFile(file);

        final DocumentationOutputService documentationOutputService =
                DocumentationOutputServiceFactory.createDocumentationOutputService(OutputFormat.getOutputFormat(outputFormat));

        return documentationOutputService.createDocumentation(description);
    }
}
