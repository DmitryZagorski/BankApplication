package home.intexsoft.bank_application.dima;

public enum Commands {

    BANKS("banks"),
    CLIENTS("clients"),
    ADD_BANK("add bank"),
    VALIDATE_ADD_BANK("validate adding bank"),
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
