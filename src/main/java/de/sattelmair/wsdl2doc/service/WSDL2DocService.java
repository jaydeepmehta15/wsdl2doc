package de.sattelmair.wsdl2doc.service;

import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.activation.UnsupportedDataTypeException;
import java.io.File;
import java.net.URL;

public interface WSDL2DocService {

    byte[] generateDocumentationFromURL(final String url, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException;

    byte[] generateDocumentationFromURL(final URL url, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException;

    byte[] generateDocumentationFromFile(final String filePath, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException;

    byte[] generateDocumentationFromFile(final File file, final String outputFormat) throws WSDLImportException, UnsupportedDataTypeException;

}