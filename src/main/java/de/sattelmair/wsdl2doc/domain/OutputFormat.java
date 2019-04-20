package de.sattelmair.wsdl2doc.domain;

import javax.activation.UnsupportedDataTypeException;
import java.util.Arrays;

public enum OutputFormat {

    HTML, PDF, MARKDOWN, WORD;

    public static OutputFormat getOutputFormat(final String outputFormat) throws UnsupportedDataTypeException {
        return Arrays.stream(values()).filter(format -> format.name().equalsIgnoreCase(outputFormat)).findFirst().orElseThrow(UnsupportedDataTypeException::new);
    }
}
