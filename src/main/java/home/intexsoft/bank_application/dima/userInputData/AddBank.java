package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.dima.Command;

import java.util.Scanner;

public class AddBank extends Command {

    @Override
    protected void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of bank");
      //  command.getAttributes().put("bankName", new InputString().enterString(scanner).trim());
        System.out.println("Enter the commission for individual");
        //command.getAttributes().put("commissionForIndividual", new InputDouble().enterDouble(scanner).toString());
        System.out.println("Enter the commission for entity");
        //command.getAttributes().put("commissionForEntity", new InputDouble().enterDouble(scanner).toString());
    }



}
