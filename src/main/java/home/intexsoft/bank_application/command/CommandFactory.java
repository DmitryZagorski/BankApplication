package home.intexsoft.bank_application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

class CommandFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);
    private Map<Command.CommandType, Class<? extends Command>> commands = new HashMap<>();

    {
        commands.put(Command.CommandType.ADD_BANK, AddBankCommand.class);
        commands.put(Command.CommandType.DELETE_BANK, DeleteBankCommand.class);
        commands.put(Command.CommandType.DELETE_ALL_BANKS, DeleteAllBanksCommand.class);
        commands.put(Command.CommandType.FIND_CLIENTS_OF_BANK, FindClientsOfBankCommand.class);
        commands.put(Command.CommandType.VIEW_ALL_BANKS, ViewAllBanksCommand.class);
        commands.put(Command.CommandType.ADD_CLIENT, AddClientCommand.class);
        commands.put(Command.CommandType.DELETE_CLIENT, DeleteClientCommand.class);
        commands.put(Command.CommandType.DELETE_ALL_CLIENTS_OF_BANK, DeleteAllClientsOfBankCommand.class);
        commands.put(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT, FindBankAccountsOfClientCommand.class);
        commands.put(Command.CommandType.ADD_BANK_ACCOUNT, AddBankAccountCommand.class);
        commands.put(Command.CommandType.ADD_CURRENCY, AddCurrencyCommand.class);
        commands.put(Command.CommandType.ADD_MONEY_TRANSFER, AddMoneyTransferCommand.class);
        commands.put(Command.CommandType.ADD_SALARY_PAYMENT, AddSalaryPaymentCommand.class);

        commands.put(Command.CommandType.FIND_OPERATIONS_OF_CLIENT_BY_DATE, FindTransactionsOfClientCommand.class);

    }

    Command createCommandByCommandName(Command.CommandType commandName) {
        log.debug("Creating command by commandName " + commandName + " started");
        Command command = null;
        try {
            Class<? extends Command> commandsClass = commands.get(commandName);
            Constructor<?>[] constructors = commandsClass.getConstructors();
            command = (Command) constructors[0].newInstance();
        } catch (Exception e) {
            log.error("Error during creating command", e);
        }
        log.debug("Creating command by commandName " + commandName + " finished");
        return command;
    }
}