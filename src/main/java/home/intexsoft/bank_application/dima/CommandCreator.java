package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;

import java.util.List;
import java.util.Map;

public class CommandCreator {

    private CommandValidationFactory commandValidationFactory = new CommandValidationFactory();
    private CommandFactory commandFactory = new CommandFactory();
    private CommandLineParser commandLineParser = new CommandLineParser();

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public CommandLineParser getCommandLineParser() {
        return commandLineParser;
    }

    public void setCommandLineParser(CommandLineParser commandLineParser) {
        this.commandLineParser = commandLineParser;
    }

    public Command createCommand(MenuItem activeItem) {
        System.out.println("Chosen command is " + activeItem.getName());
        Command command = null;
        Class<? extends Command> activeCommandClass = findActiveCommandClass(activeItem.getName());
        try {
            command = activeCommandClass.newInstance();
            command.setName(activeItem.getName());
            addCommandsArguments(command);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Can't create object of class " + e);
        }
        return command;
    }

    public void addCommandsArguments(Command command) {
        Validator commandValidator = commandValidationFactory.createValidationCommand(command);
        for (Map.Entry<CommandAttribute, String> commandAttribute : command.getAttributes().entrySet()) {
            do {
                commandValidator.getValidationErrors().get(commandAttribute.getKey());
                String attributeValue = commandLineParser.enterStringByAttribute(commandAttribute.getKey().getAttributeName());
                commandAttribute.setValue(attributeValue);
                commandValidator.validate(command, commandAttribute.getKey());
            }
            while (!commandValidator.getValidationErrors().get(commandAttribute.getKey()).isEmpty());
        }
    }

    // in MAP no to add!!! Need to rewrite!!


    public Class<? extends Command> findActiveCommandClass(String name) {
        Class<? extends Command> foundClass = null;
        for (Map.Entry<Command.Commands, Class<? extends Command>> commandsClassEntry : commandFactory.getFactory().entrySet()) { // GET !!!!!!!!
            if (name.equals(commandsClassEntry.getKey().getCommandName())) {
                foundClass = commandsClassEntry.getValue();
            }
        }
        return foundClass;
    }
}
