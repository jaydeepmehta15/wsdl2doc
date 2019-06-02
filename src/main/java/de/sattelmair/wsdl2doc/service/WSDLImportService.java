package de.sattelmair.wsdl2doc.service;

import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import java.io.File;
import java.net.URL;

public interface WSDLImportService {

    Description readWSDLFromFile(final String filePath) throws WSDLImportException;

    Description readWSDLFromFile(final File file) throws WSDLImportException;

    Description readWSDLFromURL(final String url) throws WSDLImportException;

    Description readWSDLFromURL(final URL url) throws WSDLImportException;

    Description readWSDLFromStream(final byte[] content) throws WSDLImportException;
}
