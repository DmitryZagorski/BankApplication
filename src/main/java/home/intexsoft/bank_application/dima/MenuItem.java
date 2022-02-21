package home.intexsoft.bank_application.dima;

import java.util.HashMap;
import java.util.Map;

public class MenuItem {

    private String name;
    private MenuItem parent;
    private Map<String, MenuItem> children = new HashMap<>();
    private boolean command;

    public boolean isCommand() {
        return command;
    }

    public void setCommand(boolean command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public void setChildren(Map<String, MenuItem> children) {
        this.children = children;
    }


}
