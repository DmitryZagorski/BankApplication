package home.intexsoft.bank_application.dima;

import java.util.Map;

public class CommandCreator {

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
        for (Map.Entry<Commands, Class<? extends Command>> commandsClassEntry : commandFactory.getFactory().entrySet()) {
            if (activeItem.getName().equalsIgnoreCase(commandsClassEntry.getKey().getCommandName())) {
                try {
                    command = commandsClassEntry.getValue().newInstance();
                    command.setName(activeItem.getName());
                    commandLineParser.addCommandsArguments(command);
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return command;
    }


}
