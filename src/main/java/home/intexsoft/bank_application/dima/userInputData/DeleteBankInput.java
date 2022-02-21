package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.models.Bank;

import java.util.List;
import java.util.Scanner;

public class DeleteBankInput {

    public void enterDataToRemoveBank(Command command) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the bank id from list: ");
        viewAllBanks();
        Integer bankId = new InputInteger().enterInteger(scanner);
        command.getAttributes().put("removeBank", bankId.toString());
    }

    private void viewAllBanks() {
        List<Bank> allBanks = new BankRepresentation().findAllBanks();
        Bank bank;
        for (Bank allBank : allBanks) {
            bank = allBank;
            System.out.println(bank.getId() + " " + bank.getName());
        }
    }
}
