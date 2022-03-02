package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.validation.commandValidators.AddBankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandValidatorFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandValidatorFactory.class);

    private Map<Command.CommandType, Class<? extends Validator>> commandValidators = new HashMap<>();

    {
        commandValidators.put(Command.CommandType.ADD_BANK, AddBankValidator.class);
    }

    public Validator createCommandValidator(Command command) throws IllegalAccessException, InstantiationException {
        log.info("Method of creationValidationCommand to command '" + command.getName()+ "' started");
        return commandValidators.get(command.getName()).newInstance();
    }
}
