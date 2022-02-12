package home.intexsoft.bank_application.greeting;

import home.intexsoft.bank_application.commands.BanksCommands;
import home.intexsoft.bank_application.commands.ClientsCommands;
import home.intexsoft.bank_application.commands.Commands;
import home.intexsoft.bank_application.commands.TransactionsCommands;

import java.util.Arrays;

public class Greeting {

    public void firstPhrase() {
        System.out.println("Hello. Let's start our application.");
    }

    public void chooseCommand() {
        System.out.println("Enter one of these commands");
    }

    public void printFirstCommands() {
        System.out.println(Arrays.toString(Commands.values()));
    }

    public void printBankCommands() {
        System.out.println(Arrays.toString(BanksCommands.values()));
    }

    public void printClientCommands() {
        System.out.println(Arrays.toString(ClientsCommands.values()));
    }

    public void printTransactionCommand() {
        System.out.println(Arrays.toString(TransactionsCommands.values()));
    }



}
