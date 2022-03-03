package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.validation.commandValidators.AddBankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandValidatorFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandValidatorFactory.class);
    private Map<Command.CommandType, Class<? extends Validator>> commandValidators = new HashMap<>();

    {
        commandValidators.put(Command.CommandType.ADD_BANK, AddBankValidator.class);
    }

    public Validator createCommandValidator(Command command) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        log.info("Method of creationValidationCommand to command '" + command.getName() + "' started");
        Validator commandValidator = null;
        try {
            Class<? extends Validator> commandsClass = commandValidators.get(command.getName());
            Constructor<?>[] constructors = commandsClass.getConstructors();
            commandValidator = (Validator) constructors[0].newInstance();
        } catch (NullPointerException e) {
            log.error("Error during creating commandValidator");
        }
        log.info("Method of creationValidationCommand to command '" + command.getName() + "' finished");
        return commandValidator;
    }
}