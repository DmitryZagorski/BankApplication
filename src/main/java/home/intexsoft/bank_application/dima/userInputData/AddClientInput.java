package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.commandRepresentation.CommandRepresentation;

import java.util.Scanner;

public class AddClientInput extends CommandRepresentation {

    public void execute(Command command) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of client");
        command.getAttributes().put("clientName", new InputString().enterString(scanner).trim());
        System.out.println("Enter the surname of client");
        command.getAttributes().put("clientSurname", new InputString().enterString(scanner).trim());
        System.out.println("Enter the number of clients status");
        new ViewStatuses().execute();
        command.getAttributes().put("clientStatusId", new InputInteger().enterInteger(scanner).toString());
        System.out.println("Enter the number of currency");
        new ViewCurrency().execute();
        command.getAttributes().put("currencyId", new InputInteger().enterInteger(scanner).toString());
        System.out.println("Enter the number of bank");
        new ViewBanks().execute();
        command.getAttributes().put("bankId", new InputInteger().enterInteger(scanner).toString());
        System.out.println("Enter amount of money");
        command.getAttributes().put("amountOfMoney", new InputDouble().enterDouble(scanner).toString());
    }
}
