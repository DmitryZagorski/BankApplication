package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.AddBank;
import home.intexsoft.bank_application.dima.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<Command.Commands, Class<? extends Command>> factory = new HashMap<>();

    public Map<Command.Commands, Class<? extends Command>> getFactory() {
        return factory;
    }

    public void setFactory(Map<Command.Commands, Class<? extends Command>> factory) {
        this.factory = factory;
    }

    {
        factory.put(Command.Commands.ADD_BANK, AddBank.class);
    }

    public Command createCommand(MenuItem activeItem) {
        System.out.println("Chosen command is " + activeItem.getName());
        Command command = null;
        for (Map.Entry<Command.Commands, Class<? extends Command>> commandsClassEntry : getFactory().entrySet()) {
            if (activeItem.getName().equals(commandsClassEntry.getKey().getCommandName())) {
                try {
                    command = commandsClassEntry.getValue().newInstance();
                    command.setName(activeItem.getName());
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return command;
    }


}

