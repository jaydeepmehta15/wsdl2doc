package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.service.DocumentationOutputService;
import lombok.Data;
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

import javax.xml.namespace.QName;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class MarkdownDocumentationOutputServiceImpl implements DocumentationOutputService {

    private static final String NONE = "none";

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

        final Set<Datatype> datatypes = getDatatypes(serviceDescription, getMeesageQNames(serviceDescription), new HashSet<Datatype>());
        stringBuilder.append(createDatatypesDocumentation(datatypes));

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
            stringBuilder.append(new BoldText("Address:")).append(" ").append(new Text(address)).append("\n<br />");
            stringBuilder.append(new BoldText("Protocol:")).append(" ").append(new Text(protocol)).append("\n<br />");
            if ("HTTP".equals(protocol)) {
                stringBuilder.append(new BoldText("Verb:")).append(" ").append(new Text(verb)).append("\n<br />");
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
            stringBuilder.append(new BoldText("Description:")).append(" ").append(new Text(operationDocumentation)).append("\n<br />");
        }

        final String operationPattern = operation.getPattern().name();
        stringBuilder.append(new BoldText("Pattern:")).append(" ").append(new Text(operationPattern)).append("\n<br />");

        final String inputDocumentation = createInputDocumentation(operation.getInput());
        stringBuilder.append(new BoldText("Input:")).append(" ").append(new Text(inputDocumentation)).append("\n<br />");

        final String outputDocumentation = createOutputDocumentation(operation.getOutput());
        stringBuilder.append(new BoldText("Output:")).append(" ").append(new Text(outputDocumentation)).append("\n<br />");


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
            return NONE;
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

        return NONE;
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

        return NONE;
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

        return NONE;
    }

    private Set<QName> getMeesageQNames(final Description description) {
        final Set<QName> messageQNames = new HashSet<>();
        final List<MessageImpl> messages = ((DescriptionImpl) description).getMessages();

        for (final MessageImpl message : messages) {
            final List<Part> parts = message.getParts();
            for (final Part part : parts) {
                final Element element = part.getElement();
                QName partName = null;

                if (element != null) {
                    partName = element.getQName();
                } else {
                    partName = part.getType().getQName();
                }

                if(partName != null) {
                    messageQNames.add(partName);
                }
            }
        }

        return messageQNames;
    }

    private Set<Datatype> getDatatypes(final Description description, final Set<QName> messageParts, final Set<Datatype> datatypes) {
        for(final QName messagePart : messageParts) {
            final List<Schema> schemas = description.getTypes().getSchemas();

            for(final Schema schema : schemas) {
                Type type = schema.getType(messagePart);

                // In case of complexType tag inside element tag
                if (type == null) {
                    type = schema.getElement(messagePart).getType();
                }

                final Datatype datatype = new Datatype(messagePart);

                if (type instanceof ComplexType) {
                    final ComplexType complexType = (ComplexType) type;

                    if (complexType.getSequence() != null) {
                        final List<Element> elements = complexType.getSequence().getElements();

                        for (final Element complexTypeElement : elements) {
                            datatype.getElements().add(createComplexDatatypeElement(complexTypeElement));
                        }

                        datatypes.add(datatype);
                    } else if(complexType.getAll() != null) {
                        final All all = complexType.getAll();
                        final List<Element> elements = all.getElements();

                        for (final Element complexTypeElement : elements) {
                            datatype.getElements().add(createComplexDatatypeElement(complexTypeElement));
                        }

                        datatypes.add(datatype);
                    } else {
                        datatypes.add(datatype);
                    }
                } else {
                    datatypes.add(new Datatype(type.getQName()));
                }
            }
        }

        final Set<QName> unaddedDatatypeNames = new HashSet<>();
        for(final Datatype datatype : datatypes) {
            if(datatype.isComplex()) {
                final Optional<QName> notAddedQname = datatype.getElements().stream()
                        .filter(element -> !messageParts.contains(element.getType()))
                        .findFirst().map(element -> new QName(element.getType().getNamespaceURI(), element.getType().getLocalPart()));

                notAddedQname.ifPresent(unaddedDatatypeNames::add);
            }
        }

        if(!unaddedDatatypeNames.isEmpty()) {
            messageParts.addAll(unaddedDatatypeNames);
            getDatatypes(description, messageParts, datatypes);
        }

        return datatypes;
    }

    private ComplexDatatypeElement createComplexDatatypeElement(final Element element) {
        return new ComplexDatatypeElement(element.getQName(), element.getType().getQName(),
                        element.getMinOccurs(), element.getMaxOccurs());
    }

    private String createDatatypesDocumentation(final Set<Datatype> datatypes) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new Heading("Datatypes", 2)).append("\n");

        for(final Datatype datatype : datatypes) {
            final String datatypeName = datatype.getName().getLocalPart();
            stringBuilder.append("<a id=" + datatypeName + "></a>").append(datatypeName);

            if(datatype.isComplex()) {
                stringBuilder.append("\n");

                final List<String> parameters = datatype.elements.stream().map(element ->
                        new BoldText("Name") + ": " + new Text(element.name.getLocalPart()) + "\n<br />" +
                                new BoldText("Type") + ": " + new Text(element.getType().getLocalPart()) + "\n<br />" +
                                new BoldText("Cardinality") + ": " + new Text(element.getCardinality()) + "\n<br />"
                ).collect(Collectors.toList());

                stringBuilder.append(new UnorderedList<>(parameters)).append("\n");
            } else {
                stringBuilder.append("\n<br /><br />");
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
            stringBuilder.append(new Text("<a id=" + messageName + "></a>")).append(messageName).append("<br/>\n");

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

    @Data
    private class Datatype {

        private QName name;
        private final List<ComplexDatatypeElement> elements;

        Datatype(final QName name) {
            this.name = name;
            this.elements = new ArrayList<>();
        }

        public boolean isComplex() {
            return !this.elements.isEmpty();
        }
    }

    @Data
    private class ComplexDatatypeElement {

        private final QName name;
        private final QName type;
        private final String cardinality;

        ComplexDatatypeElement(final QName name, final QName type, final int minOccurs, final String maxOccurs) {
            this.name = name;
            this.type = type;
            this.cardinality = minOccurs + ".." + maxOccurs;
        }

        ComplexDatatypeElement(final QName name, final QName type) {
            this(name, type, 0, "unbounded");
        }
    }
}
