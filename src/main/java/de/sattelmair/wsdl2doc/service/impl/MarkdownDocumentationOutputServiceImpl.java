package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.utills.Utils;
import de.sattelmair.wsdl2doc.domain.Datatype;
import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang.StringUtils;
import org.ow2.easywsdl.schema.api.*;
import org.ow2.easywsdl.wsdl.api.*;
import org.ow2.easywsdl.wsdl.api.abstractItf.AbsItfBinding;
import org.ow2.easywsdl.wsdl.impl.wsdl11.DescriptionImpl;
import org.ow2.easywsdl.wsdl.impl.wsdl11.MessageImpl;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MarkdownDocumentationOutputServiceImpl implements DocumentationOutputService {

    private static final String LINE_BREAK = "\n<br />";

    private boolean doubleLink = true;

    @Override
    public byte[] generateDocumentation(final Description serviceDescription) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<Service> services = serviceDescription.getServices();

        for (final Service service : services) {
            stringBuilder.append(createServiceDocumentation(service));
        }

        stringBuilder.append("\n");

        stringBuilder.append(createMessageInformation(serviceDescription));

        stringBuilder.append("\n");

        /* CREATING ERROR HERE #Jaydeep 
        final Set<Datatype> datatypes = Utils.getDatatypes(serviceDescription, Utils.getMeesageQNames(serviceDescription), new HashSet<>());
        stringBuilder.append(createDatatypesDocumentation(datatypes));
		*/
		
        return stringBuilder.toString().getBytes();
    }

    private String createServiceDocumentation(final Service service) {
        final StringBuilder stringBuilder = new StringBuilder();

        final String serviceName = createServiceName(service);
        stringBuilder.append(new Heading("Service: " + serviceName)).append("\n");

        final String serviceDocumentation = createDocumentationFromDocumentationTag(service.getDocumentation());
        if (!serviceDocumentation.isEmpty()) {
            stringBuilder.append(new Heading("Description", 2)).append("\n").append(new Text(serviceDocumentation)).append("\n");
        }

        stringBuilder.append(createInterfaceDocumentation(service));

        return stringBuilder.toString();
    }

    private String createInterfaceDocumentation(final Service service) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new Heading("Endpoints", 2)).append("\n");

        for (final Endpoint endpoint : service.getEndpoints()) {
            final String name = endpoint.getName();
            final String address = endpoint.getAddress();
            final Binding binding = endpoint.getBinding();
            final String protocol = getProtocol(binding.getTypeOfBinding());
            final String verb = binding.getTransportProtocol();
            final InterfaceType interfaceType = binding.getInterface();
            final List<Operation> operations = interfaceType.getOperations();

            stringBuilder.append(new Heading(name, 3)).append("\n");
            stringBuilder.append(new BoldText("Address:")).append(" ").append(new Text(address)).append(LINE_BREAK);
            stringBuilder.append(new BoldText("Protocol:")).append(" ").append(new Text(protocol)).append(LINE_BREAK);
            if ("HTTP".equals(protocol)) {
                stringBuilder.append(new BoldText("Verb:")).append(" ").append(new Text(verb)).append(LINE_BREAK);
            }

            stringBuilder.append("\n").append(new Heading("Operations", 4)).append("\n");

            for (final Operation operation : operations) {
                stringBuilder.append(createOperationDocumentation(operation));
            }
        }

        return stringBuilder.toString();
    }

    private String getProtocol(final AbsItfBinding.BindingConstants bindingConstants) {
        switch (bindingConstants) {
            case HTTP11_BINDING4WSDL11:
            case HTTP_BINDING4WSDL20:
                return "HTTP";
            case SOAP_BINDING4WSDL20:
            case SOAP11_BINDING4WSDL11:
            case SOAP12_BINDING4WSDL11:
                return "SOAP";
            case MIME_BINDING4WSDL11:
                return "MIME";
            case RPC_BINDING4WSDL20:
                return "RCP";
            default:
                return null;
        }
    }

    private String createOperationDocumentation(final Operation operation) {
        final StringBuilder stringBuilder = new StringBuilder();

        final String operationName = operation.getQName().getLocalPart();
        stringBuilder.append(new Heading(operationName, 5)).append("\n");

        final String operationDocumentation = createDocumentationFromDocumentationTag(operation.getDocumentation());
        if (!operationDocumentation.isEmpty()) {
            stringBuilder.append(new BoldText("Description:")).append(" ").append(new Text(operationDocumentation)).append(LINE_BREAK);
        }

        final String operationPattern = operation.getPattern().name();
        stringBuilder.append(new BoldText("Pattern:")).append(" ").append(new Text(operationPattern)).append(LINE_BREAK);

        final String inputDocumentation = createInputDocumentation(operation.getInput());
        stringBuilder.append(new BoldText("Input:")).append(" ").append(new Text(inputDocumentation)).append(LINE_BREAK);

        final String outputDocumentation = createOutputDocumentation(operation.getOutput());
        stringBuilder.append(new BoldText("Output:")).append(" ").append(new Text(outputDocumentation)).append(LINE_BREAK);


        final String faultsDocumentation = createFaulstDocumentation(operation.getFaults());
        stringBuilder.append(new BoldText("Faults:")).append(" ").append(new Text(faultsDocumentation)).append("\n");

        return stringBuilder.toString();
    }

    private String createFaulstDocumentation(final List<Fault> faults) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (faults != null && !faults.isEmpty()) {
            for (final Fault fault : faults) {
                stringBuilder.append(createFaultDocumentation(fault));
            }
        } else {
            return Utils.NO_VALUE;
        }

        return stringBuilder.toString();
    }

    private String createFaultDocumentation(final Fault fault) {
        final Element faultElement = fault.getElement();

        if (faultElement != null) {
            final String faultName = fault.getMessageName().getLocalPart();

            return new Link(faultName, "#" + faultName).toString();
        }

        final List<Part> parts = fault.getParts();
        if (!parts.isEmpty()) {
            final String partName = parts.get(0).getType().getQName().getLocalPart();
            return new Text(partName).toString();
        }

        return Utils.NO_VALUE;
    }

    private String createOutputDocumentation(final Output output) {
        if (output != null) {
            final Element element = output.getElement();

            if (element != null) {
                final String outputName = output.getMessageName().getLocalPart();

                return new Link(outputName, "#" + outputName).toString();
            }

            final List<Part> parts = output.getParts();
            if (!parts.isEmpty()) {
                final String partName = parts.get(0).getType().getQName().getLocalPart();
                return new Text(partName).toString();
            }

        }

        return Utils.NO_VALUE;
    }

    private String createInputDocumentation(final Input input) {
        if (input != null) {
            final Element element = input.getElement();

            if (element != null) {
                final String inputName = input.getMessageName().getLocalPart();

                return new Link(inputName, "#" + inputName).toString();
            }

            final List<Part> parts = input.getParts();
            if (!parts.isEmpty()) {
                final String partName = parts.get(0).getType().getQName().getLocalPart();
                return new Text(partName).toString();
            }
        }

        return Utils.NO_VALUE;
    }

    private String createDatatypesDocumentation(final Set<Datatype> datatypes) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new Heading("Datatypes", 2)).append("\n");

        for(final Datatype datatype : datatypes) {
            final String datatypeName = datatype.getName().getLocalPart();
            stringBuilder.append("<a id=" + datatypeName + "></a>").append(this.doubleLink ? datatypeName : "");

            if(datatype.isComplex()) {
                stringBuilder.append("\n");

                final List<String> parameters = datatype.getElements().stream().map(element ->
                        new BoldText("Name") + ": " + new Text(element.getName().getLocalPart()) + "\n" +
                                new BoldText("Type") + ": " + new Text(element.getType().getLocalPart()) + "\n" +
                                new BoldText("Cardinality") + ": " + new Text(element.getCardinality()) + "\n"
                ).collect(Collectors.toList());

                stringBuilder.append(new UnorderedList<>(parameters)).append("\n");
            } else {
                stringBuilder.append(LINE_BREAK + "<br />");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


       private String createMessageInformation(final Description description) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new Heading("Messages", 2)).append("\n");

        final List<MessageImpl> messages = ((DescriptionImpl) description).getMessages();
        for (final MessageImpl message : messages) {
            final String messageName = message.getQName().getLocalPart();
            stringBuilder.append(new Text("<a id=" + messageName + "></a>")).append(this.doubleLink ? messageName : "").append("<br/>\n");

            final List<Part> parts = message.getParts();
            if(!parts.isEmpty()) {
                stringBuilder.append(new BoldText("Parts:")).append("\n");

                for (final Part part : parts) {
                    final List<String> partElements = new ArrayList<>();

                    partElements.add(new BoldText("Name") + ": " + part.getPartQName().getLocalPart());

                    final Element element = part.getElement();
                    final Type type = part.getType();

                    if(element != null) {
                        final String elementName = element.getQName().getLocalPart();
                        partElements.add(new BoldText("Type") + ": " + new Link(elementName , "#" + elementName ));
                    }

                    if(type != null) {
                        final String elementName = type.getQName().getLocalPart();

                        partElements.add(new BoldText("Type") + ": " +
                                new Link(elementName , "#" + elementName ));
                    }

                    stringBuilder.append(new UnorderedList<>(partElements)).append("\n");
                }
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private String createServiceName(final Service service) {
        if (service.getQName() != null) {
            return StringUtils.defaultString(service.getQName().getLocalPart());
        }

        log.warn("No service name provided!");

        return "";
    }

    private String createDocumentationFromDocumentationTag(final Documentation documentation) {
        if (documentation != null && StringUtils.isNotBlank(documentation.getContent())) {
            return documentation.getContent().trim();
        }

        return "";
    }

}
