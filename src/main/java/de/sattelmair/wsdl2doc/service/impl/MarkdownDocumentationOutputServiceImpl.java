package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import org.ow2.easywsdl.wsdl.api.Description;

public class MarkdownDocumentationOutputServiceImpl implements DocumentationOutputService {

    @Override
    public byte[] createDocumentation(Description serviceDescription) {
        return new byte[0];
    }

}
