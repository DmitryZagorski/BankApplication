package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.validation.commandValidators.AddBankCommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandValidatorFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandValidatorFactory.class);
    private Map<Command.CommandType, Class<? extends Validator>> commandValidators = new HashMap<>();

    {
        commandValidators.put(Command.CommandType.ADD_BANK, AddBankCommandValidator.class);
    }

    public Validator createCommandValidator(Command.CommandType commandName) {
        log.debug("Method of creationValidationCommand to command '" + commandName + "' started");
        Validator commandValidator = null;
        try {
            Class<? extends Validator> commandsClass = commandValidators.get(commandName);
            Constructor<?>[] constructors = commandsClass.getConstructors();
            commandValidator = (Validator) constructors[0].newInstance();
        } catch (Exception e) {
            log.error("Error during creating commandValidator");
        }
        log.debug("Method of creationValidationCommand to command '" + commandName + "' finished");
        return commandValidator;
    }
}