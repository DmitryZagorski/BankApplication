package home.intexsoft.bank_application;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.commandRepresentation.ClientRepresentation;
import home.intexsoft.bank_application.commandRepresentation.TransactionRepesentation;
import home.intexsoft.bank_application.userInputParsing.CommandDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryRepresentation {

    // Получаем класс команды или команду, которая должна выполняться

    private static final Logger Log = LoggerFactory.getLogger(FactoryRepresentation.class);

//    public void chooseMethod(CommandDescriptor commandDescriptor) {
//        Log.info("Choosing of method by command started");
//
//        BankRepresentation bankRepresentation = new BankRepresentation();
//        TransactionRepesentation transactionRepesentation = new TransactionRepesentation();
//        ClientRepresentation clientRepresentation = new ClientRepresentation();
//        String[] attributes = commandDescriptor.getAttributes();
//
//        switch (commandDescriptor.getCommand()) {
//            case "addBank":
//                bankRepresentation.addBank(attributes);
//                break;
//            case "viewAllBanks":
//                bankRepresentation.viewAllBanks();
//                break;
//            case "findClientsOfBank":
//                bankRepresentation.viewClientsOfBank(attributes);
//                break;
//            case "removeBank":
//                bankRepresentation.removeBank(attributes);
//                break;
//            case "removeAllBanks":
//                bankRepresentation.removeAllBanks();
//                break;
//            case "addClient":
//                clientRepresentation.addClient(attributes);
//                break;
//            case "viewAllClients":
//                clientRepresentation.viewAllClients();
//                break;
//            case "removeClient":
//                clientRepresentation.removeClient(attributes);
//                break;
//            case "removeAllClients":
//                clientRepresentation.removeAllClients();
//                break;
//            case "addClientsBankAccount":
//                clientRepresentation.addClientsBankAccount(attributes);
//                break;
//            case "findClientsBankAccount":
//                clientRepresentation.findClientsBankAccount(attributes);
//                break;
//            case "addTransaction":
//                transactionRepesentation.addTransaction(attributes);
//                break;
//            case "viewClientsTransactions":
//                transactionRepesentation.viewClientsTransactions(attributes);
//                break;
//        }
//    }
}
