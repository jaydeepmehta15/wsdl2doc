package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.service.ApplicationMainProcessorService;
import de.sattelmair.wsdl2doc.service.WSDL2DocService;
import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.activation.UnsupportedDataTypeException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.Arrays;

public class ApplicationMainProcessorServiceImpl implements ApplicationMainProcessorService {

    private static final String INPUT_SOURCE_OPTION = "input_source";
    private static final String OUTPUT_PATH = "output_path";
    private static final String OUTPUT_FORMAT = "output_format";

    @Override
    public void process(final String[] args) {
        final CommandLine commandLine = parseInput(createOptions(), args);

        final String outputFormat = commandLine.hasOption(OUTPUT_FORMAT) &&
                StringUtils.isNotBlank(commandLine.getOptionValue(OUTPUT_FORMAT)) ? commandLine.getOptionValue(OUTPUT_FORMAT) : OutputFormat.PDF.name();

        if(!canWriteFile(commandLine.getOptionValue(OUTPUT_FORMAT))) {
            throw new IllegalArgumentException(MessageFormat.format("Can not write file to given file path {0}!!", commandLine.getOptionValue(OUTPUT_PATH)));
        }

        final byte[] generatedDocumentation = generateDocumentationOutput(commandLine.getOptionValue(INPUT_SOURCE_OPTION), outputFormat);

        try {
            Files.write(new File(createFilePath(commandLine.getOptionValue(OUTPUT_PATH), OutputFormat.getExtension(outputFormat))).toPath(), generatedDocumentation);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing output file", e);
        }
    }

    private CommandLine parseInput(final Options options, final String[] input) {
        try {
            return new DefaultParser().parse(options, input);
        } catch (ParseException parseException) {
            throw new RuntimeException("ERROR: Unable to parse command-line arguments "
                    + Arrays.toString(input) + " due to: "
                    + parseException);
        }
    }

    private Options createOptions() {
        final Option inputSourceOption = Option.builder("i")
                .required()
                .hasArg()
                .longOpt(INPUT_SOURCE_OPTION)
                .desc("Either a URL or a file path to a WSDL")
                .build();

        final Option outputPathOption = Option.builder("o")
                .required()
                .hasArg()
                .longOpt(OUTPUT_PATH)
                .desc("Path to output directory")
                .build();

        final Option formatOption = Option.builder("f")
                .required(false)
                .hasArg()
                .longOpt(OUTPUT_FORMAT)
                .desc("Desired output format (e.g. PDF, HTML, etc.)")
                .build();

        final Options options = new Options();
        options.addOption(inputSourceOption);
        options.addOption(outputPathOption);
        options.addOption(formatOption);
        return options;
    }

    private byte[] generateDocumentationOutput(final String filePathOrURL, final String outputFormat) {
        if(StringUtils.isBlank(filePathOrURL)) {
            throw new IllegalArgumentException("File path or URL must not be empty!!");
        }

        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();
        if(UrlValidator.getInstance().isValid(filePathOrURL)) {
            try {
                return wsdl2DocService.generateDocumentationFromURL(filePathOrURL, outputFormat);
            } catch (WSDLImportException e) {
                throw new RuntimeException(MessageFormat.format("WSDL at given URL {0} could not be parsed correctly!", filePathOrURL));
            } catch (UnsupportedDataTypeException e) {
                throw new IllegalArgumentException(MessageFormat.format("{0} is not a valid output format!", outputFormat));
            }
        } else if(new File(filePathOrURL).exists()) {
            try {
                return wsdl2DocService.generateDocumentationFromFile(filePathOrURL, outputFormat);
            } catch (WSDLImportException e) {
                throw new RuntimeException(MessageFormat.format("File at given file path {0} could not be parsed correctly!", filePathOrURL));
            } catch (UnsupportedDataTypeException e) {
                throw new IllegalArgumentException(MessageFormat.format("{0} is not a valid output format!", outputFormat));
            }
        } else {
            throw new IllegalArgumentException("Neither valid URL nor valid file path was passed!");
        }
    }

    private boolean canWriteFile(final String filePath) {
        final File file = new File(filePath);
        if (!file.canWrite()) {
            return false;
        }

        /* Java lies on Windows */
        try {
            new FileOutputStream(file, true).close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private String createFilePath(final String directory, final String extension) {
        return directory + "generated_documentation_" + System.currentTimeMillis() + "." + extension;
    }

}
