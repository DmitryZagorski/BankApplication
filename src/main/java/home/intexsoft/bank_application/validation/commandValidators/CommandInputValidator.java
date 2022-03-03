package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommandInputValidator {

    private static final Logger log = LoggerFactory.getLogger(CommandInputValidator.class);
    private static final String INPUT_NUMBER_ERROR = "Entered value '%s' doesn't exist in the list. Try again.";

    public boolean checkIfCommandValueExist(String string, Map<String, MenuItem> entities) {
        log.info("Checking if entered value exist");
        boolean isExist = false;
        MenuItem menuItem = entities.get(string);
        if (menuItem != null) {
            isExist = true;
        } else {
            System.out.println(String.format(INPUT_NUMBER_ERROR, string));
        }
        log.info("Checking if entered value exist finished");
        return isExist;
    }

}
