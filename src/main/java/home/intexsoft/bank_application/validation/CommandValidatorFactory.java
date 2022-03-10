package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.FindClientsOfBankCommand;
import home.intexsoft.bank_application.validation.commandValidators.AddBankCommandValidator;
import home.intexsoft.bank_application.validation.commandValidators.DeleteBankCommandValidator;
import home.intexsoft.bank_application.validation.commandValidators.FindClientsOfBankCommandValidator;
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
        commandValidators.put(Command.CommandType.DELETE_BANK, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_CLIENTS_OF_BANK, FindClientsOfBankCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_CLIENT, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.DELETE_CLIENT, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_TRANSACTION, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_TRANSACTIONS_OF_CLIENT, DeleteBankCommandValidator.class);

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