package de.sattelmair.wsdl2doc.service;

import org.ow2.easywsdl.wsdl.api.Description;

public interface DocumentationOutputService {

    byte[] generateDocumentation(final Description serviceDescription);

}
