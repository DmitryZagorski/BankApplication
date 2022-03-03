package home.intexsoft.bank_application.dima.service;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.dima.dao.BankDAO;
import home.intexsoft.bank_application.dima.dao.DAO;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BankService {

    public void addBank(String bankName, String commissionForIndividual, String commissionForEntity) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        DAO<Bank, Integer> bankDao = new BankDAO(sessionFactory);
        Bank bank = createBankAndSetValuesOfAttributes(bankName, commissionForIndividual, commissionForEntity);
        if (!checkIfBankNameExist(bank.getName())) {
            bankDao.create(bank);
        }
    }

    private Bank createBankAndSetValuesOfAttributes(String bankName, String commissionForIndividual, String commissionForEntity) {
        final Bank bank = new Bank();
        bank.setName(bankName);
        bank.setCommissionForIndividual(Double.valueOf(commissionForIndividual));
        bank.setCommissionForEntity(Double.valueOf(commissionForEntity));
        return bank;
    }

    private boolean checkIfBankNameExist(String bankName) {
        boolean isExist = false;
        List<Bank> allBanks = new BankRepresentation().findAllBanks();
        for (Bank bank : allBanks) {
            if (bank.getName().equals(bankName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
