package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.service.WSDLImportService;
import lombok.extern.slf4j.Slf4j;
import org.ow2.easywsdl.wsdl.WSDLFactory;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLException;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;
import org.ow2.easywsdl.wsdl.api.WSDLReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class WSDLImportServiceImpl implements WSDLImportService {

    @Override
    public Description readWSDLFromFile(final String filePath) throws WSDLImportException {
        return readWSDLFromFile(new File(filePath));
    }

    @Override
    public Description readWSDLFromFile(final File file) throws WSDLImportException {
        try {
            final WSDLReader wsdlReader = WSDLFactory.newInstance().newWSDLReader();
            return wsdlReader.read(new InputSource(new FileInputStream(file)));
        } catch (WSDLException | IOException | URISyntaxException e) {
            log.error(e.getLocalizedMessage());

            throw new WSDLImportException(e);
        }
    }

    @Override
    public Description readWSDLFromURL(final String url) throws WSDLImportException {
        try {
            return readWSDLFromURL(new URL(url));
        } catch (MalformedURLException e) {
            log.error(e.getLocalizedMessage());

            throw new WSDLImportException(e);
        }
    }

    @Override
    public Description readWSDLFromURL(final URL url) throws WSDLImportException {
        try {
            final WSDLReader wsdlReader = WSDLFactory.newInstance().newWSDLReader();
            return wsdlReader.read(url);
        } catch (WSDLException | IOException | URISyntaxException e) {
            log.error(e.getLocalizedMessage());

            throw new WSDLImportException(e);
        }
    }
}
