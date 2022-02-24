package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.dao.BankDAO;
import home.intexsoft.bank_application.dima.dao.DAO;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;

public class MenuTest {


    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

//        BankAppRunner bankAppRunner = new BankAppRunner();
//        bankAppRunner.runMenu();

        SessionFactory factory = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Bank, String> bankDao = new BankDAO(factory);

            final Bank bank = new Bank();
            bank.setName("Dew");
            bank.setCommissionForIndividual(6.0);
            bank.setCommissionForEntity(5.0);

            bankDao.create(bank);

//            final Engine engine = new Engine();
//            engine.setModel("engine_model_03");
//            engine.setPower(12345);
//
//            engineDAO.create(engine);
//
//            final Engine result = engineDAO.read("engine_model_03");
//            System.out.println("Created : " + result);
//            System.out.println();
//
//            result.setPower(54321);
//            engineDAO.update(result);
//
//            final Engine update = engineDAO.read("engine_model_03");
//            System.out.println("Updated : " + update);
//            System.out.println();
//
//            engineDAO.delete(new Engine("engine_model_03", 54321));
//
//            System.out.println("Deleted(empty obj) : " + engineDAO.read("engine_model_03"));
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

    }



}
