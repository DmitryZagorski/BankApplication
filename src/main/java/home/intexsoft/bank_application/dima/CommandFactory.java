package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.AddBankCommand;
import home.intexsoft.bank_application.dima.command.AddClient;
import home.intexsoft.bank_application.dima.command.DeleteBank;
import home.intexsoft.bank_application.dima.command.DeleteClient;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<Commands, Class<? extends Command>> factory = new HashMap<>();

    public Map<Commands, Class<? extends Command>> getFactory() {
        return factory;
    }

    public void setFactory(Map<Commands, Class<? extends Command>> factory) {
        this.factory = factory;
    }

    {
        factory.put(Commands.ADD_BANK, AddBankCommand.class);
        factory.put(Commands.DELETE_BANK, DeleteBank.class);
        factory.put(Commands.ADD_CLIENT, AddClient.class);
        factory.put(Commands.DELETE_CLIENT, DeleteClient.class);
    }

    public Command createCommand(MenuItem activeItem) {
        System.out.println("Chosen command is " + activeItem.getName());
        Command command = null;
        for (Map.Entry<Commands, Class<? extends Command>> commandsClassEntry : getFactory().entrySet()) {
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

