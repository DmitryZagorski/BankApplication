package home.intexsoft.bank_application.dima;

public enum Commands {

    ADD_BANK("add bank"),
    DELETE_BANK("delete bank"),
    ADD_CLIENT("add client"),
    DELETE_CLIENT("delete client");

    private String commandName;

    Commands(String commandName) {
        this.commandName = commandName;
    }

}
