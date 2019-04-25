package de.sattelmair.wsdl2doc.domain;

import javax.activation.UnsupportedDataTypeException;
import java.util.Arrays;

public enum OutputFormat {

    HTML("html"), PDF("pdf"), MARKDOWN("md"), WORD("docx");

    private String extension;

    private OutputFormat(final String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static OutputFormat getOutputFormat(final String outputFormat) throws UnsupportedDataTypeException {
        return Arrays.stream(values()).filter(format -> format.name().equalsIgnoreCase(outputFormat)).findFirst().orElseThrow(UnsupportedDataTypeException::new);
    }

    public static String getExtension(final String outputFormat) throws UnsupportedDataTypeException {
        return Arrays.stream(values()).filter(format -> format.name().equalsIgnoreCase(outputFormat)).findFirst().orElseThrow(UnsupportedDataTypeException::new).getExtension();
    }

}
