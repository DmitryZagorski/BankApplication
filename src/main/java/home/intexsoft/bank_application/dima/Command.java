package home.intexsoft.bank_application.dima;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private String name;
    private Map<String, String> attributes = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    protected abstract void execute();

}
