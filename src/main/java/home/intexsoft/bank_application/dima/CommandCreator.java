package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;

import java.util.List;
import java.util.Map;

public class CommandCreator {

    private Validator validator;
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
        for (Map.Entry<Commands, Class<? extends Command>> commandsClassEntry : commandFactory.getFactory().entrySet()) { // GET !!!!!!!!
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
        CommandAttribute commandAttr = new CommandAttribute();
        Map<String, String> attributes = command.getAttributes();
        boolean isValidated = false;
        for (Map.Entry<String, String> commandAttribute : attributes.entrySet()) {
            List<String> commandErrors = validator.getValidationErrors().get(commandAttribute.getKey());
            while (!validator.getValidationErrors().get(commandAttribute.getKey()).isEmpty()) {
                System.out.println("Enter " + commandAttribute.getKey().getAttributedName());
                String attributesParameter = commandLineParser.enterString();
                commandAttribute.setValue(attributesParameter);
                AttributeDescriptor attributeDescriptor = commandAttr.getAttributeRules().get(command.getName());
                AttributeType attrType = attributeDescriptor.getType();
                commandValidationFactory.createCommandValidator(attrType).validate(attributeDescriptor, commandErrors);

            }
        }

    }


}
