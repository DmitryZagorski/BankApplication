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
        Log.debug("Initializing in static block");
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

        MenuItem deleteAllBanksItem = new MenuItem();
        deleteAllBanksItem.setCommand(Boolean.TRUE);
        deleteAllBanksItem.setName(Command.CommandType.DELETE_ALL_BANKS);
        deleteAllBanksItem.setParent(banksItem);

        MenuItem findClientsOfBankItem = new MenuItem();
        findClientsOfBankItem.setCommand(Boolean.TRUE);
        findClientsOfBankItem.setName(Command.CommandType.FIND_CLIENTS_OF_BANK);
        findClientsOfBankItem.setParent(banksItem);

        MenuItem viewAllBanksItem = new MenuItem();
        viewAllBanksItem.setCommand(Boolean.TRUE);
        viewAllBanksItem.setName(Command.CommandType.VIEW_ALL_BANKS);
        viewAllBanksItem.setParent(banksItem);

        MenuItem clientsItem = new MenuItem();
        clientsItem.setName(Command.CommandType.CLIENTS);
        clientsItem.setParent(mainMenuItem);

        MenuItem addClientItem = new MenuItem();
        addClientItem.setCommand(Boolean.TRUE);
        addClientItem.setName(Command.CommandType.ADD_CLIENT);
        addClientItem.setParent(clientsItem);

        MenuItem deleteClientItem = new MenuItem();
        deleteClientItem.setCommand(Boolean.TRUE);
        deleteClientItem.setName(Command.CommandType.DELETE_CLIENT);
        deleteClientItem.setParent(clientsItem);

        MenuItem deleteAllClientsItem = new MenuItem();
        deleteAllClientsItem.setCommand(Boolean.TRUE);
        deleteAllClientsItem.setName(Command.CommandType.DELETE_ALL_CLIENTS);
        deleteAllClientsItem.setParent(clientsItem);

        MenuItem findBankAccountOfClientItem = new MenuItem();
        findBankAccountOfClientItem.setCommand(Boolean.TRUE);
        findBankAccountOfClientItem.setName(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT);
        findBankAccountOfClientItem.setParent(clientsItem);

        MenuItem viewAllClientsItem = new MenuItem();
        viewAllClientsItem.setCommand(Boolean.TRUE);
        viewAllClientsItem.setName(Command.CommandType.VIEW_ALL_CLIENTS);
        viewAllClientsItem.setParent(banksItem);

        MenuItem transactionsItem = new MenuItem();
        transactionsItem.setName(Command.CommandType.TRANSACTIONS);
        transactionsItem.setParent(mainMenuItem);

        MenuItem addTransactionItem = new MenuItem();
        addTransactionItem.setCommand(Boolean.TRUE);
        addTransactionItem.setName(Command.CommandType.ADD_TRANSACTION);
        addTransactionItem.setParent(banksItem);

        MenuItem findTransactionsOfClientItem = new MenuItem();
        findTransactionsOfClientItem.setCommand(Boolean.TRUE);
        findTransactionsOfClientItem.setName(Command.CommandType.FIND_TRANSACTIONS_OF_CLIENT);
        findTransactionsOfClientItem.setParent(banksItem);

        mainMenuItem.getChildren().put("1", banksItem);
        mainMenuItem.getChildren().put("2", clientsItem);
        mainMenuItem.getChildren().put("3", transactionsItem);

        banksItem.getChildren().put("1", addBankItem);
        banksItem.getChildren().put("2", deleteBankItem);
        banksItem.getChildren().put("3", deleteAllBanksItem);
        banksItem.getChildren().put("4", findClientsOfBankItem);
        banksItem.getChildren().put("5", viewAllBanksItem);

        clientsItem.getChildren().put("1", addClientItem);
        clientsItem.getChildren().put("2", deleteClientItem);
        clientsItem.getChildren().put("3", deleteAllClientsItem);
        clientsItem.getChildren().put("4", findBankAccountOfClientItem);
        clientsItem.getChildren().put("5", viewAllClientsItem);

        transactionsItem.getChildren().put("1", addTransactionItem);
        transactionsItem.getChildren().put("2", findTransactionsOfClientItem);

        menu.setActiveItem(mainMenuItem);
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
        BankAppRunner bankAppRunner = new BankAppRunner();
        bankAppRunner.runMenu();
    }
}