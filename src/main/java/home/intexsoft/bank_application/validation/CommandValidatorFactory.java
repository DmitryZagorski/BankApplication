package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.ViewAllBanksCommand;
import home.intexsoft.bank_application.validation.commandValidators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static home.intexsoft.bank_application.BankAppRunner.applicationContext;

@Component
public class CommandValidatorFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandValidatorFactory.class);
    private Map<Command.CommandType, Class<? extends Validator>> commandValidators = new HashMap<>();

    {
        commandValidators.put(Command.CommandType.ADD_BANK, AddBankCommandValidator.class);
        commandValidators.put(Command.CommandType.DELETE_BANK, DeleteBankCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_CLIENTS_OF_BANK, FindClientsOfBankCommandValidator.class);
        commandValidators.put(Command.CommandType.VIEW_ALL_BANKS, ViewAllBanksCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_CLIENT, AddClientCommandValidator.class);
        commandValidators.put(Command.CommandType.DELETE_CLIENT, DeleteClientCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT, FindBankAccountsOfClientCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_BANK_ACCOUNT, AddBankAccountCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_CURRENCY, AddCurrencyCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_MONEY_TRANSFER, AddMoneyTransferCommandValidator.class);
        commandValidators.put(Command.CommandType.ADD_SALARY_PAYMENT, AddSalaryPaymentCommandValidator.class);
        commandValidators.put(Command.CommandType.FIND_OPERATIONS_OF_CLIENT, FindOperationsOfClientCommandValidator.class);
    }

    public Validator createCommandValidator(Command.CommandType commandName) {
        log.debug("Method of creationValidationCommand to command '" + commandName + "' started");
        Class<? extends Validator> commandsClass = commandValidators.get(commandName);
        Validator commandValidator = applicationContext.getBean(commandsClass);
        log.debug("Method of creationValidationCommand to command '" + commandName + "' finished");
        return commandValidator;
    }
}