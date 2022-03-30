package home.intexsoft.bank_application;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandLineCommandCreator;
import home.intexsoft.bank_application.command.CommandLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Menu {

    private static final Logger log = LoggerFactory.getLogger(Menu.class);

    private MenuItem activeItem;
    private CommandLineParser commandLineParser = new CommandLineParser();

    @Autowired
    private CommandLineCommandCreator commandLineCommandCreator;

    CommandLineParser getCommandLineParser() {
        return commandLineParser;
    }

    MenuItem getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(MenuItem activeItem) {
        this.activeItem = activeItem;
    }

    void runMenu() {
        log.debug("Starting of RunMenu");
        System.out.println("Choose operation number to proceed");
        System.out.println("Enter 'quit' to exit, enter 'back' to return to previous menu");
        String userInput = "";
        while (!"quit".equalsIgnoreCase(userInput)) {
            this.getActiveItem().getChildren().entrySet().forEach((child -> System.out.println(
                    child.getKey() + " " + child.getValue().getName())));
            userInput = this.getCommandLineParser().getInputString(this.getActiveItem().getChildren());
            if ("back".equalsIgnoreCase(userInput)) {
                this.setActiveItem(this.getActiveItem().getParent());
            } else {
                MenuItem activeItem = this.getActiveItem().getChildren().get(userInput);
                if (activeItem.isCommand()) {
                    Command command = createCommand(activeItem);
                    command.execute();
                    break;
                } else {
                    this.setActiveItem(activeItem);
                }
            }
        }
        log.debug("RunMenu finished");
    }

    private Command createCommand(MenuItem menuItem) {
        return commandLineCommandCreator.createCommand(menuItem.getName());
    }
}