package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.MenuItem;
import home.intexsoft.bank_application.validation.commandValidators.CommandInputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class CommandLineParser {

    private static final Logger log = LoggerFactory.getLogger(CommandLineParser.class);
    private Scanner scanner = new Scanner(System.in);

    public String enterStringWithIntegerNumber(Map<String, MenuItem> entities) {
        log.info("Entering data starts");
        CommandInputValidator commandInputValidator = new CommandInputValidator();
        String string;
        do {
            string = scanner.nextLine();
        } while (string == null ||
                string.trim().isEmpty() ||
                !commandInputValidator.checkIfCommandValueExist(string, entities));
        log.info("Entering data finished");
        return string;
    }

    String enterStringByAttribute(String commandAttribute) {
        log.info("Entering data starts");
        String string;
        do {
            System.out.println("Enter " + commandAttribute);
            string = scanner.nextLine();
        } while (string == null || string.trim().isEmpty());
        log.info("Entering data finished");
        return string;
    }
}