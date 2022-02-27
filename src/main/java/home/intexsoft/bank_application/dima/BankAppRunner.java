package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class BankAppRunner {

    private static final Logger Log = LoggerFactory.getLogger(BankAppRunner.class);

    private static Menu menu;
    private static CommandCreator commandCreator;
    private static Validator validator;
    private static CommandValidationFactory commandValidationFactory;

    static {
        menu = new Menu();
        commandCreator = new CommandCreator();
        commandValidationFactory = new CommandValidationFactory();

        MenuItem mainMenuItem = new MenuItem();
        mainMenuItem.setName("mainMenuItem");
        mainMenuItem.setParent(mainMenuItem);

        MenuItem banksItem = new MenuItem();
        banksItem.setName(Commands.BANKS.getCommandName());
        banksItem.setParent(mainMenuItem);

        MenuItem addBankItem = new MenuItem();
        addBankItem.setCommand(Boolean.TRUE);
        addBankItem.setName(Commands.ADD_BANK.getCommandName());
        addBankItem.setParent(banksItem);

        MenuItem deleteBankItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteBankItem.setName(Commands.DELETE_BANK.getCommandName());
        deleteBankItem.setParent(banksItem);

        MenuItem clientsItem = new MenuItem();
        clientsItem.setName(Commands.CLIENTS.getCommandName());
        clientsItem.setParent(mainMenuItem);

        MenuItem addClientItem = new MenuItem();
        addClientItem.setCommand(Boolean.TRUE);
        addClientItem.setName(Commands.ADD_CLIENT.getCommandName());
        addClientItem.setParent(clientsItem);

        MenuItem deleteClientItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteClientItem.setName(Commands.DELETE_CLIENT.getCommandName());
        deleteClientItem.setParent(clientsItem);

        mainMenuItem.getChildren().put("1", banksItem);
        mainMenuItem.getChildren().put("2", clientsItem);

        banksItem.getChildren().put("1", addBankItem);
        banksItem.getChildren().put("2", deleteBankItem);

        clientsItem.getChildren().put("1", addClientItem);
        clientsItem.getChildren().put("2", deleteClientItem);

        menu.setActiveItem(mainMenuItem);
    }

    public void runMenu() {
        System.out.println("Choose operation number to proceed");
        System.out.println("Enter 'quit' to exit, enter 'back' to return to previous menu");
        String command = "";
        while (!"quit".equalsIgnoreCase(command)) {
            menu.getActiveItem().getChildren().entrySet().forEach((child -> System.out.println(child.getKey() + " " + child.getValue().getName())));
            //command = scanner.nextLine();
            command = menu.getCommandLineParser().enterString();
            if ("back".equalsIgnoreCase(command)) {
                menu.setActiveItem(menu.getActiveItem().getParent());
            } else {
                MenuItem activeItem = menu.getActiveItem().getChildren().get(command);
                if (activeItem.isCommand()) {
                    createCommand(activeItem);
                    break;
                } else {
                    menu.setActiveItem(activeItem);
                }
            }
        }
    }

    public void createCommand(MenuItem menuItem) {
        Command command = commandCreator.createCommand(menuItem);
        if (commandValidationFactory.createCommandValidator(command).validate(command)) {
            command.execute();
        }
    }
}

//    public void createCommand(MenuItem menuItem) {
//        Command command = commandCreator.createCommand(menuItem);
//        CommandLineParser commandLineParser = commandCreator.getCommandLineParser();
//        commandLineParser.addCommandsArguments(command);
//        CommandValidator commandValidator = commandValidationFactory.createCommandValidator(command);
//        if (commandValidator.validate(command)) {
//            command.execute();
//        }
//    }
