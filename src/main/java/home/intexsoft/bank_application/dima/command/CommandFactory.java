package home.intexsoft.bank_application.dima.command;

import home.intexsoft.bank_application.dima.command.AddBankCommand;
import home.intexsoft.bank_application.dima.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<Command.CommandType, Class<? extends Command>> commandClasses = new HashMap<>();

    Map<Command.CommandType, Class<? extends Command>> getCommandClasses() {
        return commandClasses;
    }

    {
        commandClasses.put(Command.CommandType.ADD_BANK, AddBankCommand.class);
    }

}

