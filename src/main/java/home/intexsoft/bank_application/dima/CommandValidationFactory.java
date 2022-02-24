package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.AddBank;
import home.intexsoft.bank_application.dima.validation.AddingBankCommandValidator;
import home.intexsoft.bank_application.dima.validation.CommandValidator;

import java.util.HashMap;
import java.util.Map;

public class CommandValidationFactory {

    private Map<Commands, Class<? extends CommandValidator>> factory = new HashMap<>();

    public Map<Commands, Class<? extends CommandValidator>> getFactory() {
        return factory;
    }

    public void setFactory(Map<Commands, Class<? extends CommandValidator>> factory) {
        this.factory = factory;
    }

    {
        factory.put(Commands.ADD_BANK, AddingBankCommandValidator.class);
    }

    public CommandValidator createCommandValidator (Command command) {
        CommandValidator commandValidator = null;
        for (Map.Entry<Commands, Class<? extends CommandValidator>> commandsClassEntry : getFactory().entrySet()) {
            if (command.getName().equalsIgnoreCase(commandsClassEntry.getKey().getCommandName())) {
                try {
                    commandValidator = commandsClassEntry.getValue().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return commandValidator;
    }
}
