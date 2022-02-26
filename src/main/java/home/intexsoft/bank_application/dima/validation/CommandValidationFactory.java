package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.CommandAttributeName;
import home.intexsoft.bank_application.dima.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandValidationFactory {

    private Map<Commands, Class<? extends Validator>> factory = new HashMap<>();

    public Map<Commands, Class<? extends Validator>> getFactory() {
        return factory;
    }

    public void setFactory(Map<Commands, Class<? extends Validator>> factory) {
        this.factory = factory;
    }

    {
        factory.put(Commands.ADD_BANK, AddBankValidator.class);
    }

    public Validator createCommandValidator (Command command) {
        Validator validator = null;
        for (Map.Entry<Commands, Class<? extends Validator>> commandsClassEntry : getFactory().entrySet()) {
            if (command.getName().equalsIgnoreCase(commandsClassEntry.getKey().getCommandName())) {
                try {
                    validator = commandsClassEntry.getValue().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return validator;
    }
}
