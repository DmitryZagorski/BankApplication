package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.CommandLineParser;

public class Menu {

    private MenuItem activeItem;
    private CommandLineParser commandLineParser = new CommandLineParser();

    CommandLineParser getCommandLineParser() {
        return commandLineParser;
    }

    MenuItem getActiveItem() {
        return activeItem;
    }

    void setActiveItem(MenuItem activeItem) {
        this.activeItem = activeItem;
    }

}
