package home.intexsoft.bank_application.dima.command;

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

    public Map<CommandAttribute, String> getAttributes() {
        return attributes;
    }

    public abstract void execute(Command command);

    public enum CommandType {

        BANKS("banks"),
        CLIENTS("clients"),
        ADD_BANK("add bank"),
        DELETE_BANK("delete bank"),
        ADD_CLIENT("add client"),
        DELETE_CLIENT("delete client");

        private String commandName;

        CommandType(String commandName) {
            this.commandName = commandName;
        }

        public String getCommandName() {
            return commandName;
        }

    }
}
