package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.command.AddSalaryPaymentCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.validation.commandValidators.*;
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
        commandValidators.put(Command.CommandType.ADD_CLIENT, AddClientCommandValidator.class);
        commandValidators.put(Command.CommandType.DELETE_CLIENT, DeleteClientCommandValidator.class);
        commandValidators.put(Command.CommandType.DELETE_ALL_CLIENTS_OF_BANK, DeleteAllClientsOfBankCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT, FindBankAccountsOfClientCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_BANK_ACCOUNT, AddBankAccountCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_CURRENCY, AddCurrencyCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_MONEY_TRANSFER, AddMoneyTransferCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_SALARY_PAYMENT, AddSalaryPaymentCommandValidator.class);

        //  commandValidators.put(Command.CommandType.FIND_OPERATIONS_OF_CLIENT_BY_DATE, DeleteBankCommandValidator.class);
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