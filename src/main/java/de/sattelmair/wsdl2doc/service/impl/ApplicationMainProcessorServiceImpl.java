package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.domain.OutputFormat;
import de.sattelmair.wsdl2doc.service.ApplicationMainProcessorService;
import de.sattelmair.wsdl2doc.service.WSDL2DocService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.ow2.easywsdl.wsdl.api.WSDLImportException;

import javax.activation.UnsupportedDataTypeException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

public class ApplicationMainProcessorServiceImpl implements ApplicationMainProcessorService {

    @Override
    public void process(final String[] args) {
        if(args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Worng number if input parameters! Usage must be as follows:" +
                    "\n\t- First parameter: Either a URL or a file path to a WSDL \t(mandatory)" +
                    "\n\t- Second parameter: Path to output directory \t(mandatory)" +
                    "\n\t- Third parameter: Desired output format (e.g. PDF, HTML, etc.) \t(optional, default is PDF)");
        }

        final String outputFormat = args.length == 3 && StringUtils.isNotBlank(args[2]) ? args[2] : OutputFormat.PDF.name();
        final byte[] generatedDocumentation = generateDocumentationOutput(args[0], outputFormat);

        if(!canWriteFile(args[1])) {
            throw new IllegalArgumentException(MessageFormat.format("Can not write file to given file path {0}!!", args[1]));
        }

        try {
            Files.write(new File(createFilePath(args[1], OutputFormat.getExtension(outputFormat))).toPath(), generatedDocumentation);
        } catch (IOException e) {
            new RuntimeException("Error while writing output file", e);
        }
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
