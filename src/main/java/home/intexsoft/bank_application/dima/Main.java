package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.dao.BankDAO;
import home.intexsoft.bank_application.dima.dao.DAO;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        DAO<Bank, Integer> bankDao = new BankDAO(sessionFactory);
//
//        final Bank bank = new Bank();
//        bank.setName("eeee");
//        bank.setCommissionForIndividual(10.0);
//        bank.setCommissionForEntity(15.0);
//
////        bankDao.create(bank);
////        bankDao.read(8);
//        System.out.println(bankDao.read(8));

        BankAppRunner bankAppRunner = new BankAppRunner();
        bankAppRunner.runMenu();

    }

}



