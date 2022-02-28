package home.intexsoft.bank_application.dima.viewLists;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
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
