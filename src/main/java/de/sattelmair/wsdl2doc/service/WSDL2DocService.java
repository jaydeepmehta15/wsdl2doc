package de.sattelmair.wsdl2doc.service;

import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import java.io.File;
import java.net.URL;

public interface WSDL2DocService {

    byte[] generateDocumentationFromURL(final String url, final String outputFormat) throws WSDLImportException;

    byte[] generateDocumentationFromURL(final URL url, final String outputFormat) throws WSDLImportException;

    byte[] generateDocumentationFromFile(final String filePath, final String outputFormat) throws WSDLImportException;

    byte[] generateDocumentationFromFile(final File file, final String outputFormat) throws WSDLImportException;

}