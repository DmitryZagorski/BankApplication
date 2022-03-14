package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommandInputValidator {

    private static final Logger log = LoggerFactory.getLogger(CommandInputValidator.class);
    private static final String INPUT_NUMBER_ERROR = "Incorrect value '%s'. Try again.";

    public boolean checkIfCommandValueExist(String string, Map<String, MenuItem> menuItems) {
        log.debug("Checking if entered value exist");
        boolean isExist = false;
        MenuItem menuItem = menuItems.get(string);
        if (menuItem != null || "quit".equals(string) || "back".equals(string)) {
            isExist = true;
        } else {
            System.out.println(String.format(INPUT_NUMBER_ERROR, string));
        }
        log.debug("Checking if entered value exist finished");
        return isExist;
    }
}
