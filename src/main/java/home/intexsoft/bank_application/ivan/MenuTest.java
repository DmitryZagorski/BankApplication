//package home.intexsoft.bank_application.ivan;
//
//import java.util.Scanner;
//
//public class MenuTest {
//
//    private static Menu menu;
//
//    static {
//        menu = new Menu();
//
//        MenuItem root = new MenuItem();
//        root.setName("root");
//        root.setParent(root);
//
//        MenuItem banksItem = new MenuItem();
//        banksItem.setName("banks");
//        banksItem.setParent(root);
//
//        MenuItem addBankItem = new MenuItem();
//        addBankItem.setCommand(Boolean.TRUE);
//        addBankItem.setName("add bank");
//        addBankItem.setParent(banksItem);
//
//        MenuItem deleteBankItem = new MenuItem();
//        deleteBankItem.setCommand(Boolean.TRUE);
//        deleteBankItem.setName("delete bank");
//        deleteBankItem.setParent(banksItem);
//
//        MenuItem clientsItem = new MenuItem();
//        clientsItem.setName("clients");
//        clientsItem.setParent(root);
//
//        MenuItem addClientItem = new MenuItem();
//        addClientItem.setCommand(Boolean.TRUE);
//        addClientItem.setName("add client");
//        addClientItem.setParent(clientsItem);
//
//        MenuItem deleteClientItem = new MenuItem();
//        deleteBankItem.setCommand(Boolean.TRUE);
//        deleteClientItem.setName("delete client");
//        deleteClientItem.setParent(clientsItem);
//
//        root.getChildren().put("1", banksItem);
//        root.getChildren().put("2", clientsItem);
//
//        banksItem.getChildren().put("1", addBankItem);
//        banksItem.getChildren().put("2", deleteBankItem);
//
//        clientsItem.getChildren().put("1", addClientItem);
//        clientsItem.getChildren().put("2", deleteClientItem);
//
//        menu.setActiveItem(root);
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Choose operation number to proceed");
//        System.out.println("Enter 'quit' to exit, enter 'back' to return to previous menu");
//        Scanner scanner = new Scanner(System.in);
//        String command = "";
//        while (!"quit".equalsIgnoreCase(command)) {
//            menu.getActiveItem().getChildren().entrySet().forEach((child -> System.out.println(child.getKey() + " " + child.getValue().getName())));
//            command = scanner.nextLine();
//            if ("back".equalsIgnoreCase(command)) {
//                menu.setActiveItem(menu.getActiveItem().getParent());
//            } else {
//                MenuItem activeItem = menu.getActiveItem().getChildren().get(command);
//                if (activeItem.isCommand()) {
//                    createCommand(activeItem);
//                    break;
//                } else {
//                    menu.setActiveItem(activeItem);
//                }
//            }
//        }
//    }
//
//    private static void createCommand(MenuItem activeItem) {
//        System.out.println("Chosen command is " + activeItem.getName());
//    }
//
//}
