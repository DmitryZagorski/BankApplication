package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.commandRepresentation.CommandRepresentation;

import java.util.Scanner;

public class DeleteClientInput extends CommandRepresentation {

    public void execute(Command command) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the client id from list: ");
        new ViewClients().execute();
        command.getAttributes().put("removeClient", new InputInteger().enterInteger(scanner).toString());
    }
}
