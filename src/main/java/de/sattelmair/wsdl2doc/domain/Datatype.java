package de.sattelmair.wsdl2doc.domain;

import de.sattelmair.wsdl2doc.Utils;
import lombok.Data;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

@Data
public class Datatype {

    private QName name;
    private final List<ComplexDatatypeElement> elements;

    public Datatype(final QName name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    public boolean isComplex() {
        return !this.elements.isEmpty();
    }

}
