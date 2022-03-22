package home.intexsoft.bank_application;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandCreator;
import home.intexsoft.bank_application.configuration.BankAppRunnerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BankAppRunner {

    private static final Logger Log = LoggerFactory.getLogger(BankAppRunner.class);

    private Menu menu;
    private CommandCreator commandCreator;

    @Autowired
    public BankAppRunner(Menu menu, CommandCreator commandCreator) {
        this.menu = menu;
        this.commandCreator = commandCreator;
    }

    private void runMenu() {
        Log.debug("Starting of RunMenu");
        System.out.println("Choose operation number to proceed");
        System.out.println("Enter 'quit' to exit, enter 'back' to return to previous menu");
        String userInput = "";
        while (!"quit".equalsIgnoreCase(userInput)) {
            menu.getActiveItem().getChildren().entrySet().forEach((child -> System.out.println(
                    child.getKey() + " " + child.getValue().getName())));
            userInput = menu.getCommandLineParser().getInputString(menu.getActiveItem().getChildren());
            if ("back".equalsIgnoreCase(userInput)) {
                menu.setActiveItem(menu.getActiveItem().getParent());
            } else {
                MenuItem activeItem = menu.getActiveItem().getChildren().get(userInput);
                if (activeItem.isCommand()) {
                    Command command = createCommand(activeItem);
                    command.execute();
                    break;
                } else {
                    menu.setActiveItem(activeItem);
                }
            }
        }
        Log.debug("RunMenu finished");
    }

    private Command createCommand(MenuItem menuItem) {
        return commandCreator.createCommand(menuItem);
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BankAppRunnerConfiguration.class);

        BankAppRunner bankAppRunner = applicationContext.getBean(BankAppRunner.class);
        bankAppRunner.runMenu();


    }
}