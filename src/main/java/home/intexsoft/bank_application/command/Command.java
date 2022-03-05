package home.intexsoft.bank_application.command;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private CommandType name;
    private Map<CommandAttribute, String> attributes = new HashMap<>();

    public CommandType getName() {
        return name;
    }

    public void setName(CommandType name) {
        this.name = name;
    }

    Map<CommandAttribute, String> getAttributes() {
        return attributes;
    }

    public abstract void execute();

    public enum CommandType {

        BANKS("banks"),
        CLIENTS("clients"),
        ADD_BANK("add bank"),
        DELETE_BANK("delete bank"),
        DELETE_ALL_BANKS("delete all banks"),
        FIND_CLIENTS_OF_BANK("find clients of bank"),
        VIEW_ALL_BANKS("view all banks"),
        ADD_CLIENT("add client"),
        DELETE_CLIENT("delete client"),
        DELETE_ALL_CLIENTS("delete all clients"),
        FIND_BANK_ACCOUNTS_OF_CLIENT("find bank accounts of client"),
        VIEW_ALL_CLIENTS("view all clients"),
        ADD_NEW_TRANSACTION("add new transaction"),
        FIND_TRANSACTIONS_OF_CLIENT("find transactions of client");

        private String commandName;

        CommandType(String commandName) {
            this.commandName = commandName;
        }

        public String getCommandName() {
            return commandName;
        }

    }
}