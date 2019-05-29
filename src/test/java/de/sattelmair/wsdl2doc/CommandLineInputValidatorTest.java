package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.service.CommandLineInputValidator;
import de.sattelmair.wsdl2doc.service.impl.CommandLineInputValidatorImpl;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.ValidationException;

public class CommandLineInputValidatorTest {

    @Test(expected = ParseException.class)
    public void testCommandLineArgumentsNull() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(null);
    }

    @Test(expected = ParseException.class)
    public void testCommandLineNoEmpty() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineArgumentsEmpty() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"",""});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineArgumentsIncomplete() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{""});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineOnlyOptionalArguments() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"-f pdf"});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineArgumentsBlank() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"  ","  "});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineArgumentsWrong() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"aa","bb"});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineSecondArgumentEmpty() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"","-b bb"});
    }

    @Test(expected = ParseException.class)
    public void testCommandLineFirstArgumentEmpty() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"-a aa", ""});
    }

    @Test(expected = ValidationException.class)
    public void testCommandLineInputSourceNotValid() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"-i srcsda", "-o scr/de/main"});
    }

    @Test(expected = ValidationException.class)
    public void testCommandLineOutputDestinationNotValid() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        commandLineInputValidator.parseInput(new String[]{"-i http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl", "-o sadsadsa"});
    }

    @Test
    public void testCommandLineOutputValid() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        final CommandLine commandLine = commandLineInputValidator.parseInput(new String[]{"-i http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl", "-o /Users/erik"});

        Assert.assertNotNull(commandLine);
        Assert.assertEquals(2, commandLine.getOptions().length);
        Assert.assertTrue(commandLine.hasOption('i'));
        Assert.assertTrue(commandLine.hasOption('o'));
        Assert.assertEquals(" http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl", commandLine.getOptionValue("i"));
        Assert.assertEquals(" /Users/erik", commandLine.getOptionValue("o"));
    }

    @Test
    public void testCommandLineOutputValidWithOutputFormat() throws ParseException, ValidationException {
        final CommandLineInputValidator commandLineInputValidator = new CommandLineInputValidatorImpl();
        final CommandLine commandLine = commandLineInputValidator.parseInput(new String[]{"-i http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl", "-o /Users/erik", "-f PDF"});

        Assert.assertNotNull(commandLine);
        Assert.assertEquals(3, commandLine.getOptions().length);
        Assert.assertTrue(commandLine.hasOption('i'));
        Assert.assertTrue(commandLine.hasOption('o'));
        Assert.assertTrue(commandLine.hasOption('f'));
        Assert.assertEquals(" http://wsf.cdyne.com/WeatherWS/Weather.asmx?wsdl", commandLine.getOptionValue("i"));
        Assert.assertEquals(" /Users/erik", commandLine.getOptionValue("o"));
        Assert.assertEquals(" PDF", commandLine.getOptionValue("f"));
    }

}
