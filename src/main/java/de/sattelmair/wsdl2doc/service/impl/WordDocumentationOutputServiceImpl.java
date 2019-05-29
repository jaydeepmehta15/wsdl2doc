package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;
import org.ow2.easywsdl.wsdl.api.Description;

import java.io.ByteArrayOutputStream;

@Slf4j
@RequiredArgsConstructor
public class WordDocumentationOutputServiceImpl implements DocumentationOutputService {

    private final DocumentationOutputService documentationOutputService;

    @Override
    public byte[] generateDocumentation(Description serviceDescription) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            final WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            final AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/hw.html"));
            afiPart.setBinaryData(this.documentationOutputService.generateDocumentation(serviceDescription));
            afiPart.setContentType(new ContentType("text/html"));

            final Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(afiPart);
            final CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
            ac.setId(altChunkRel.getId() );
            wordMLPackage.getMainDocumentPart().addObject(ac);

            wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
            wordMLPackage.save(out);
        } catch (Docx4JException e) {
            log.error("ERROR while trying to create Word based webservice documentation from WSDL!!");
        }

        return out.toByteArray();
    }
}
