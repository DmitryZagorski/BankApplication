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

    public String getInputString(Map<String, MenuItem> entities) {
        log.debug("Entering data starts");
        CommandInputValidator commandInputValidator = new CommandInputValidator();
        String input;
        do {
            input = scanner.nextLine();
        } while (input == null ||
                input.trim().isEmpty() ||
                !commandInputValidator.checkIfCommandValueExist(input, entities));
        log.debug("Entering data finished");
        return input;
    }

    String enterStringByAttribute(String commandAttribute) {
        log.debug("Entering data starts");
        String string;
        do {
            System.out.println("Enter " + commandAttribute);
            string = scanner.nextLine();
        } while (string == null || string.trim().isEmpty());
        log.debug("Entering data finished");
        return string;
    }
}