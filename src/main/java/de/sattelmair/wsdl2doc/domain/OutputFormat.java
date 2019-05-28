package de.sattelmair.wsdl2doc.domain;

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

    public static OutputFormat getOutputFormat(final String outputFormat) {
        return Arrays.stream(values()).filter(format -> format.name().equalsIgnoreCase(outputFormat)).findFirst().orElse(PDF);
    }

    public static String getExtension(final String outputFormat) {
        return Arrays.stream(values()).filter(format -> format.name().equalsIgnoreCase(outputFormat)).findFirst().orElse(PDF).getExtension();
    }

}
