package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.commandRepresentation.*;
import home.intexsoft.bank_application.dima.userInputData.AddBankInput;
import home.intexsoft.bank_application.dima.userInputData.AddClientInput;
import home.intexsoft.bank_application.dima.userInputData.DeleteBankInput;
import home.intexsoft.bank_application.dima.userInputData.DeleteClientInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class MenuRunner {

    private static final Logger Log = LoggerFactory.getLogger(MenuRunner.class);

    private static Menu menu;
    private static CommandFactory commandFactory;

    static {
        menu = new Menu();

        MenuItem mainMenuItem = new MenuItem();
        mainMenuItem.setName("mainMenuItem");
        mainMenuItem.setParent(mainMenuItem);

        MenuItem banksItem = new MenuItem();
        banksItem.setName("banks");
        banksItem.setParent(mainMenuItem);

        MenuItem addBankItem = new MenuItem();
        addBankItem.setCommand(Boolean.TRUE);
        addBankItem.setName("add bank");
        addBankItem.setParent(banksItem);

        MenuItem deleteBankItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteBankItem.setName("delete bank");
        deleteBankItem.setParent(banksItem);

        MenuItem clientsItem = new MenuItem();
        clientsItem.setName("clients");
        clientsItem.setParent(mainMenuItem);

        MenuItem addClientItem = new MenuItem();
        addClientItem.setCommand(Boolean.TRUE);
        addClientItem.setName("add client");
        addClientItem.setParent(clientsItem);

        MenuItem deleteClientItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteClientItem.setName("delete client");
        deleteClientItem.setParent(clientsItem);

        mainMenuItem.getChildren().put("1", banksItem);
        mainMenuItem.getChildren().put("2", clientsItem);

        banksItem.getChildren().put("1", addBankItem);
        banksItem.getChildren().put("2", deleteBankItem);

        clientsItem.getChildren().put("1", addClientItem);
        clientsItem.getChildren().put("2", deleteClientItem);

        menu.setActiveItem(mainMenuItem);
    }

    void runMenu() {
        System.out.println("Choose operation number to proceed");
        System.out.println("Enter 'quit' to exit, enter 'back' to return to previous menu");
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!"quit".equalsIgnoreCase(command)) {
            menu.getActiveItem().getChildren().entrySet().forEach((child -> System.out.println(child.getKey() + " " + child.getValue().getName())));
            command = scanner.nextLine();
            if ("back".equalsIgnoreCase(command)) {
                menu.setActiveItem(menu.getActiveItem().getParent());
            } else {
                MenuItem activeItem = menu.getActiveItem().getChildren().get(command);
                if (activeItem.isCommand()) {
                    createCommandFactory(activeItem);
                    break;
                } else {
                    menu.setActiveItem(activeItem);
                }
            }
        }
    }

    private CommandFactory createCommandFactory(MenuItem activeItem) {
        System.out.println("Chosen command is " + activeItem.getName());
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.factory.put(Commands.valueOf(activeItem.getName()), null);
        return commandFactory;
    }

    private Command addCommandArguments(Command command) {
        switch (command.getName()) {
            case "add bank":
                new AddBankInput().execute(command);
                break;
            case "delete bank":
                new DeleteBankInput().execute(command);
                break;
            case "add client":
                new AddClientInput().execute(command);
                break;
            case "delete client":
                new DeleteClientInput().execute(command);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command.getName());
        }
        return command;
    }

    static {
        commandFactory = new CommandFactory();
        commandFactory.getFactory().put("add bank", AddBank.class);
        commandFactory.getFactory().put("delete bank", DeleteBank.class);
        commandFactory.getFactory().put("add client", AddClient.class);
        commandFactory.getFactory().put("delete client", DeleteClient.class);
    }

    private void executeCommand(Command command) {
        commandFactory.getFactory().forEach((key, value) -> {
            if (key.equalsIgnoreCase(command.getName())) {
                value;
            }
        });

//        for (Map.Entry<String, Class<? extends CommandRepresentation>> stringClassEntry : commandFactory.getFactory().entrySet()) {
//            if (command.getName().equalsIgnoreCase(stringClassEntry.getKey())) {
//                stringClassEntry.getValue();
//            }
//        }


    }


}
