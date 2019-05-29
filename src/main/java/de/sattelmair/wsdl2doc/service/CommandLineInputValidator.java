package de.sattelmair.wsdl2doc.service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import javax.xml.bind.ValidationException;

public interface CommandLineInputValidator {

    CommandLine parseInput(final String[] manndLineInput) throws ParseException, ValidationException;

}
