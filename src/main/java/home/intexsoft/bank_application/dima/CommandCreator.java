package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;

import java.util.List;
import java.util.Map;

public class CommandCreator {

    private Validator validator = new Validator();
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
        for (Map.Entry<Command.Commands, Class<? extends Command>> commandsClassEntry : commandFactory.getFactory().entrySet()) { // GET !!!!!!!!
            if (activeItem.getName().equals(commandsClassEntry.getKey().getCommandName())) {
                try {
                    command = commandsClassEntry.getValue().newInstance();
                    command.setName(activeItem.getName());
                    addCommandsArguments(command);
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return command;
    }

//    public Command createCommand(MenuItem activeItem) {
//        System.out.println("Chosen command is " + activeItem.getName());
//        Command command = null;
//        Class<? extends Command> aClass = commandFactory.getFactory().get(activeItem.getName());
//        try {
//            command = aClass.newInstance();
//            command.setName(activeItem.getName());
//            addCommandsArguments(command);
//        } catch (InstantiationException | IllegalAccessException e) {
//            System.out.println("Can't create object of class " + e);
//        }
//        return command;
//    }

    public void addCommandsArguments(Command command) {
        Validator commandValidator = commandValidationFactory.createValidationCommand(command);
        for (Map.Entry<CommandAttribute, String> commandAttribute : command.getAttributes().entrySet()) {
            List<String> commandErrors = commandValidator.getValidationErrors().get(commandAttribute.getKey());
            commandErrors.add(" ");
            while (!commandErrors.isEmpty()) {      // do-while
                commandErrors.clear();
                System.out.println("Enter " + commandAttribute.getKey());
                String attributesParameter = commandLineParser.enterString();  //in parser
                commandAttribute.setValue(attributesParameter);
                commandValidator.validate(command);


            }
        }
    }
    // in MAP no to add!!! Need to Change!! Or From change to add!!!

    private String findCommandDescriptor(String constantName) {
        String readableName = "";


        return readableName;
    }
}
