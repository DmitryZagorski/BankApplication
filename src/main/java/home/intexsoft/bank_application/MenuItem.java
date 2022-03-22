package home.intexsoft.bank_application;

import home.intexsoft.bank_application.command.Command;

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

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public Map<String, MenuItem> getChildren() {
        return children;
    }

}