package home.intexsoft.bank_application.dima;

public class Menu {

    private MenuItem activeItem;
    private CommandLineParser commandLineParser = new CommandLineParser();
    private CommandCreator commandCreator;

    public CommandLineParser getCommandLineParser() {
        return commandLineParser;
    }

    public void setCommandLineParser(CommandLineParser commandLineParser) {
        this.commandLineParser = commandLineParser;
    }

    public CommandCreator getCommandCreator() {
        return commandCreator;
    }

    public void setCommandCreator(CommandCreator commandCreator) {
        this.commandCreator = commandCreator;
    }

    public MenuItem getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(MenuItem activeItem) {
        this.activeItem = activeItem;
    }

}
