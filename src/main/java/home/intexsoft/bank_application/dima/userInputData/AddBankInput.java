package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.dima.Command;

import java.util.Scanner;

public class AddBankInput implements ExecutionCommand{

    @Override
    public void execute(Command command) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of bank");
        String bankName = new InputString().enterString(scanner).trim();
        System.out.println("Enter the commission for individual");
        Double commissionForIndividual = new InputDouble().enterDouble(scanner);
        System.out.println("Enter the commission for entity");
        Double commissionForEntity = new InputDouble().enterDouble(scanner);

        command.getAttributes().put("bankName", bankName);
        command.getAttributes().put("commissionForIndividual", commissionForIndividual.toString());
        command.getAttributes().put("commissionForEntity", commissionForEntity.toString());
    }
}
