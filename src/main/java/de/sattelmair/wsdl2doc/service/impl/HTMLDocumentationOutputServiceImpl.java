package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import htmlflow.StaticHtml;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.ow2.easywsdl.wsdl.api.Description;

@RequiredArgsConstructor
public class HTMLDocumentationOutputServiceImpl implements DocumentationOutputService {

    private final DocumentationOutputService documentationOutputService;

    @Override
    public byte[] generateDocumentation(final Description serviceDescription) {
        final Parser parser = Parser.builder().build();
        final Node document = parser.parse(new String(this.documentationOutputService.generateDocumentation(serviceDescription)));
        final HtmlRenderer renderer = HtmlRenderer.builder().build();

        return StaticHtml
                .view()
                    .html()
                        .head()
                            .title().text("Webservice documentation").__()
                        .__()
                        .body()
                            .div()
                                .p().text(renderer.render(document)).__()
                            .__()
                        .__()
                    .__()
                .render().getBytes();
    }
}
