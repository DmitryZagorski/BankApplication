package home.intexsoft.bank_application.dima.command;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private String name;
    private Map<CommandAttribute, String> attributes = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<CommandAttribute, String> getAttributes() {
        return attributes;
    }

    protected abstract void execute();

    public enum Commands {

        BANKS("banks"),
        CLIENTS("clients"),
        ADD_BANK("add bank"),
        DELETE_BANK("delete bank"),
        ADD_CLIENT("add client"),
        DELETE_CLIENT("delete client");

        private String commandName;

        Commands(String commandName) {
            this.commandName = commandName;
        }

        public String getCommandName() {
            return commandName;
        }

        public void setCommandName(String commandName) {
            this.commandName = commandName;
        }
    }
}
