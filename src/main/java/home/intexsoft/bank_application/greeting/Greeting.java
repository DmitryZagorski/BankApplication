package home.intexsoft.bank_application.greeting;

import home.intexsoft.bank_application.commands.BanksCommands;
import home.intexsoft.bank_application.commands.ClientsCommands;
import home.intexsoft.bank_application.commands.TransactionsCommands;

import java.util.Arrays;

public class Greeting {

    public void chooseCommand() {
        System.out.println("Hello. Let's start our application." +
                             "Enter one of these commands");
        printBankCommands();
        printClientCommands();
        printTransactionCommand();
    }

    private void printBankCommands() {
        System.out.print("Bank commands: ");
        System.out.println(Arrays.toString(BanksCommands.values()));
    }

    private void printClientCommands() {
        System.out.print("Client commands: ");
        System.out.println(Arrays.toString(ClientsCommands.values()));
    }

    private void printTransactionCommand() {
        System.out.print("Transaction commands: ");
        System.out.println(Arrays.toString(TransactionsCommands.values()));
    }
}
