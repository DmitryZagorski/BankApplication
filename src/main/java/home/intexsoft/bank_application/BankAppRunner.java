package home.intexsoft.bank_application;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAppRunner {

    private static final Logger Log = LoggerFactory.getLogger(BankAppRunner.class);

    private static Menu menu;
    private static CommandCreator commandCreator;

    static {
        Log.info("Initializing in static block");
        menu = new Menu();
        commandCreator = new CommandCreator();

        MenuItem mainMenuItem = new MenuItem();
        mainMenuItem.setName(null);
        mainMenuItem.setParent(mainMenuItem);

        MenuItem banksItem = new MenuItem();
        banksItem.setName(Command.CommandType.BANKS);
        banksItem.setParent(mainMenuItem);

        MenuItem addBankItem = new MenuItem();
        addBankItem.setCommand(Boolean.TRUE);
        addBankItem.setName(Command.CommandType.ADD_BANK);
        addBankItem.setParent(banksItem);

        MenuItem deleteBankItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteBankItem.setName(Command.CommandType.DELETE_BANK);
        deleteBankItem.setParent(banksItem);

        MenuItem clientsItem = new MenuItem();
        clientsItem.setName(Command.CommandType.CLIENTS);
        clientsItem.setParent(mainMenuItem);

        MenuItem addClientItem = new MenuItem();
        addClientItem.setCommand(Boolean.TRUE);
        addClientItem.setName(Command.CommandType.ADD_CLIENT);
        addClientItem.setParent(clientsItem);

        MenuItem deleteClientItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteClientItem.setName(Command.CommandType.DELETE_CLIENT);
        deleteClientItem.setParent(clientsItem);

        mainMenuItem.getChildren().put("1", banksItem);
        mainMenuItem.getChildren().put("2", clientsItem);

        banksItem.getChildren().put("1", addBankItem);
        banksItem.getChildren().put("2", deleteBankItem);

        clientsItem.getChildren().put("1", addClientItem);
        clientsItem.getChildren().put("2", deleteClientItem);

        menu.setActiveItem(mainMenuItem);
    }

    private void runMenu() {
        Log.info("Starting of RunMenu");
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
        Log.info("RunMenu finished");
    }

    private Command createCommand(MenuItem menuItem) {
        return commandCreator.createCommand(menuItem);
    }

    public static void main(String[] args) {
        BankAppRunner bankAppRunner = new BankAppRunner();
        bankAppRunner.runMenu();
    }
}