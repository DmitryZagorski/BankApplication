package home.intexsoft.bank_application.DELETE.viewLists;

import home.intexsoft.bank_application.DELETE.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.models.Bank;

import java.util.List;

public class ViewBanks {

    public void execute(){
        List<Bank> allBanks = new BankRepresentation().findAllBanks();
        Bank bank;
        for (Bank allBank : allBanks) {
            bank = allBank;
            System.out.println(bank.getId() + " " + bank.getName());
        }
    }

}
