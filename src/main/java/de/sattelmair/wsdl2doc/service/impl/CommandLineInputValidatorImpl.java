package de.sattelmair.wsdl2doc.service.impl;

import de.sattelmair.wsdl2doc.constants.CommandLineConstants;
import de.sattelmair.wsdl2doc.service.CommandLineInputValidator;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.validator.routines.UrlValidator;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

public class CommandLineInputValidatorImpl implements CommandLineInputValidator {

    @Override
    public CommandLine parseInput(final String[] input) throws ParseException, ValidationException {
        final Options options = createOptions();
        final CommandLine commandLine = new DefaultParser().parse(options, input);

        final String sourceFilePathOrURL = commandLine.getOptionValue(CommandLineConstants.INPUT_SOURCE_OPTION).trim();
        final String destinationFilePath = commandLine.getOptionValue(CommandLineConstants.OUTPUT_PATH).trim();

        if (!UrlValidator.getInstance().isValid(sourceFilePathOrURL ) && !new File(sourceFilePathOrURL ).exists()) {
            throw new ValidationException("ERROR: Neither valid URL nor valid file path was passed!");
        }

        if (!canWriteFile(destinationFilePath)) {
            throw new ValidationException(MessageFormat.format("ERROR: Can not write file to given file path {0}!!", commandLine.getOptionValue(CommandLineConstants.OUTPUT_PATH)));
        }

        return commandLine;
    }

    private Options createOptions() {
        final Option inputSourceOption = Option.builder("i")
                .required()
                .hasArg()
                .longOpt(CommandLineConstants.INPUT_SOURCE_OPTION)
                .desc("Either a URL or a file path to a WSDL")
                .build();

        final Option outputPathOption = Option.builder("o")
                .required()
                .hasArg()
                .longOpt(CommandLineConstants.OUTPUT_PATH)
                .desc("Path to output directory")
                .build();

        final Option formatOption = Option.builder("f")
                .required(false)
                .hasArg()
                .longOpt(CommandLineConstants.OUTPUT_FORMAT)
                .desc("Desired output format (e.g. PDF, HTML, etc.)")
                .build();

        final Options options = new Options();
        options.addOption(inputSourceOption);
        options.addOption(outputPathOption);
        options.addOption(formatOption);
        return options;
    }

    private boolean canWriteFile(final String filePath) {
        final boolean isOperatingSystemWindows = SystemUtils.IS_OS_WINDOWS;
        final File file = new File(filePath);

        /* #Jaydeep skipping validation
        if(isOperatingSystemWindows) {
            try {
                new FileOutputStream(file, true).close();
            } catch (IOException e) {
                return false;
            }
        } else {
            if (!file.canWrite()) {
                return false;
            }
        }
        */

        return true;
    }
}
