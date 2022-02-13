package home.intexsoft.bank_application;

public class FactoryRepresentation {

    // Получаем класс команды или команду, которая должна выполняться

    public void chooseMethod(CommandDescriptor commandDescriptor) {

        CommandRepresentation commandRepresentation = new CommandRepresentation();
        String[] attributes = commandDescriptor.getAttributes();

        switch (commandDescriptor.getCommand()) {
            case "addBank":
                commandRepresentation.addBank(attributes);
                break;
            case "viewAllBanks":
                commandRepresentation.viewAllBanks();
                break;
            case "findClientsOfBank":
                commandRepresentation.viewClientsOfBank(attributes);
                break;
            case "removeBank":
                commandRepresentation.removeBank(attributes);
                break;
            case "removeAllBanks":
                commandRepresentation.removeAllBanks();
                break;
            case "addClient":
                commandRepresentation.addClient(attributes);
                break;
            case "viewAllClients":
                commandRepresentation.viewAllClients();
                break;
            case "removeClient":
                commandRepresentation.removeClient(attributes);
                break;
            case "removeAllClients":
                commandRepresentation.removeAllClients();
                break;
            case "addClientsBankAccount":
                commandRepresentation.addClientsBankAccount(attributes);
                break;
            case "findClientsBankAccount":
                commandRepresentation.findClientsBankAccount(attributes);
                break;
            case "addTransaction":
                commandRepresentation.addTransaction(attributes);
                break;
            case "findClientsTransactions":
                commandRepresentation.findClientsTransactions(attributes);
                break;

        }
    }

}
