package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.commandRepresentation.CommandRepresentation;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    Map<String, Class<? extends CommandRepresentation>> factory = new HashMap<>();

    public Map<String, Class<? extends CommandRepresentation>> getFactory() {
        return factory;
    }

    public void setFactory(Map<String, Class<? extends CommandRepresentation>> factory) {
        this.factory = factory;
    }
}
