package de.sattelmair.wsdl2doc.domain;

import lombok.Data;

import javax.xml.namespace.QName;

@Data
public class ComplexDatatypeElement {

    private final QName name;
    private final QName type;
    private final String cardinality;

    public ComplexDatatypeElement(final QName name, final QName type, final int minOccurs, final String maxOccurs) {
        this.name = name;
        this.type = type;
        this.cardinality = minOccurs + ".." + maxOccurs;
    }

    ComplexDatatypeElement(final QName name, final QName type) {
        this(name, type, 0, "unbounded");
    }
}
