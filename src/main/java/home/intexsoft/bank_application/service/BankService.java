package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankDAO;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);
    private static final String BANK_EXIST = "Bank with name %s already exist";
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private BankDAO bankDAO = new BankDAO(sessionFactory);

    public void addBank(String bankName, String commissionForIndividual, String commissionForEntity) {
        log.debug("Method AddBank started");
        Bank bank = createBankAndSetValuesOfAttributes(bankName, commissionForIndividual, commissionForEntity);
        if (!checkIfBankNameExist(bank.getName())) {
            bankDAO.create(bank);
        } else {
            System.out.println(String.format(BANK_EXIST, bankName));
        }
        log.debug("Method AddBank finished");
    }

    private Bank createBankAndSetValuesOfAttributes(String bankName,
                                                    String commissionForIndividual,
                                                    String commissionForEntity) {
        log.debug("Creating bank with setting its arguments started");
        final Bank bank = new Bank();
        bank.setName(bankName);
        bank.setCommissionForIndividual(Double.valueOf(commissionForIndividual));
        bank.setCommissionForEntity(Double.valueOf(commissionForEntity));
        log.debug("Creating bank with setting its arguments finished");
        return bank;
    }

    public boolean checkIfBankNameExist(String bankName) {
        return bankDAO.findByName(bankName) != null;
    }

}
