package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.userInputData.AddBankInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MenuRunner {

    private static final Logger Log = LoggerFactory.getLogger(MenuRunner.class);

    private static Menu menu;

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
                    createCommand(activeItem);
                    break;
                } else {
                    menu.setActiveItem(activeItem);
                }
            }
        }
    }

    private Command createCommand(MenuItem activeItem) {
        System.out.println("Chosen command is " + activeItem.getName());
        Command command = new Command();
        command.setName(activeItem.getName());
        return command;
    }

    private Command parseCommand(Command command) {
        switch (command.getName()) {
            case "add bank":
                new AddBankInput().execute(command);
                break;
            case "delete bank":

                break;
            case "add client":

                break;
            case "delete client":

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command.getName());
        }
        return command;
    }


}
