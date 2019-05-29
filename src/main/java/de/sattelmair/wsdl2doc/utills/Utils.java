package de.sattelmair.wsdl2doc.utills;

import de.sattelmair.wsdl2doc.domain.ComplexDatatypeElement;
import de.sattelmair.wsdl2doc.domain.Datatype;
import org.ow2.easywsdl.schema.api.*;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.Part;
import org.ow2.easywsdl.wsdl.api.Types;
import org.ow2.easywsdl.wsdl.impl.wsdl11.DescriptionImpl;
import org.ow2.easywsdl.wsdl.impl.wsdl11.MessageImpl;

import javax.xml.namespace.QName;
import java.util.*;

public class Utils {

    public static final String NO_VALUE = "none";

    private Utils() {}

    public static Set<Datatype> getDatatypes(final Description description, final Set<QName> messageParts, final Set<Datatype> datatypes) {
        for(final QName messagePart : messageParts) {
            final Types types = description.getTypes();

            if(types != null) {
                final List<Schema> schemas = types.getSchemas();

                for (final Schema schema : schemas) {
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
                        } else if (complexType.getAll() != null) {
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

    public static Set<QName> getMeesageQNames(final Description description) {
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

    private static ComplexDatatypeElement createComplexDatatypeElement(final Element element) {
        return new ComplexDatatypeElement(element.getQName(), element.getType().getQName(),
                element.getMinOccurs(), element.getMaxOccurs());
    }

}
