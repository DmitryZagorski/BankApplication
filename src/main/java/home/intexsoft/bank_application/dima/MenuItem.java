package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.Command;

import java.util.HashMap;
import java.util.Map;

public class MenuItem {

    private Command.CommandType name;
    private MenuItem parent;
    private Map<String, MenuItem> children = new HashMap<>();
    private boolean command;

    public boolean isCommand() {
        return command;
    }

    public void setCommand(boolean command) {
        this.command = command;
    }


    public Command.CommandType getName() {
        return name;
    }

    public void setName(Command.CommandType name) {
        this.name = name;
    }

    MenuItem getParent() {
        return parent;
    }

    void setParent(MenuItem parent) {
        this.parent = parent;
    }

    Map<String, MenuItem> getChildren() {
        return children;
    }

}
