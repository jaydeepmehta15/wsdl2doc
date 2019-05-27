package de.sattelmair.wsdl2doc.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import lombok.extern.slf4j.Slf4j;
import org.ow2.easywsdl.wsdl.api.Description;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class PDFDocumentationOutputServiceImpl implements DocumentationOutputService {

    @Override
    public byte[] generateDocumentation(Description serviceDescription) {
        final ByteArrayOutputStream result = new ByteArrayOutputStream();

        final Document document = new Document();
        try {
            final PdfWriter writer = PdfWriter.getInstance(document, result);
            document.open();

            final DocumentationOutputService documentationOutputService = new HTMLDocumentationOutputServiceImpl();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new ByteArrayInputStream(documentationOutputService.generateDocumentation(serviceDescription)));
            document.close();
        } catch (DocumentException | IOException e) {
            log.error("ERROR while trying to create webservice documentation from WSDL!!");
        }

        return result.toByteArray();
    }

}
