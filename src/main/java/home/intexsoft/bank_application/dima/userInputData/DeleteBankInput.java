package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.commandRepresentation.CommandRepresentation;

import java.util.Scanner;

public class DeleteBankInput extends CommandRepresentation {

    public void execute(Command command) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the bank id from list: ");
        new ViewBanks().execute();
        command.getAttributes().put("removeBank", new InputInteger().enterInteger(scanner).toString());
    }

}