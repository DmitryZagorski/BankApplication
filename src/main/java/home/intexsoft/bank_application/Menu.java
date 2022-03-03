package home.intexsoft.bank_application;

import home.intexsoft.bank_application.command.CommandLineParser;

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
