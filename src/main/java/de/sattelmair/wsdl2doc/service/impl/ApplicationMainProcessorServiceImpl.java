package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.constants.CommandLineConstants;
import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.service.ApplicationMainProcessorService;
import de.sattelmair.wsdl2doc.service.CommandLineInputValidator;
import de.sattelmair.wsdl2doc.service.WSDL2DocService;
import org.apache.commons.cli.*;
import org.apache.commons.validator.routines.UrlValidator;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ApplicationMainProcessorServiceImpl implements ApplicationMainProcessorService {

    @Override
    public void process(final String[] args) {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        final CommandLine commandLine;

        try {
            commandLine = commandLineInputValidator.parseInput(args);
            final String outputFormat = commandLine.getOptionValue(CommandLineConstants.OUTPUT_FORMAT);
            final String sourceFilePathOrURL = commandLine.getOptionValue(CommandLineConstants.INPUT_SOURCE_OPTION);

            final byte[] generatedDocumentation = generateDocumentationOutput(sourceFilePathOrURL, outputFormat);
            Files.write(new File(createFilePath(commandLine.getOptionValue(CommandLineConstants.OUTPUT_PATH), OutputFormat.getExtension(outputFormat))).toPath(), generatedDocumentation);
        }  catch (ParseException parseException) {
            throw new IllegalArgumentException("ERROR: Unable to parse command-line arguments due to:", parseException);
        } catch (ValidationException validationException) {
            throw new IllegalArgumentException("ERROR: Validation of arguments failed:", validationException);
        } catch (WSDLImportException wsdlImportException) {
            throw new RuntimeException("ERROR: Error while parsing WSDL file at given location", wsdlImportException);
        } catch (IOException fileWriteException) {
            throw new RuntimeException("ERROR: Error while writing output file", fileWriteException);
        }
    }

    private byte[] generateDocumentationOutput(final String filePathOrURL, final String outputFormat) throws WSDLImportException {
        final WSDL2DocService wsdl2DocService = new WSDL2DocServiceImpl();
        if(UrlValidator.getInstance().isValid(filePathOrURL)) {
            return wsdl2DocService.generateDocumentationFromURL(filePathOrURL, outputFormat);
        } else {
            return wsdl2DocService.generateDocumentationFromFile(filePathOrURL, outputFormat);
        }
    }

    private String createFilePath(final String directory, final String extension) {
        return directory + File.separator + "generated_documentation_" + System.currentTimeMillis() + "." + extension;
    }

}