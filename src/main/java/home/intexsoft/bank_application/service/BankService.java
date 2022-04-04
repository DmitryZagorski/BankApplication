package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankDAO;
import home.intexsoft.bank_application.models.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankService {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);
    private BankDAO bankDAO = new BankDAO();

    public void addBank(String bankName, String commissionForIndividual, String commissionForEntity) {
        log.debug("Method AddBank (name = '" + bankName + "' started");
        Bank bank = createBankAndSetValuesOfAttributes(bankName, commissionForIndividual, commissionForEntity);
        bankDAO.create(bank);
        log.debug("Method AddBank (name = '" + bankName + "' finished");
    }

    public void deleteBankByName(String bankName) {
        log.debug("Method DeleteBank '" + bankName + "'started");
        bankDAO.deleteByName(bankName);
        log.debug("Method DeleteBank '" + bankName + "'finished");
    }

    public void viewAllBanks() {
        log.debug("Method ViewAllBanks started");
        List<Bank> bankList = bankDAO.findAll();
        bankList.forEach(System.out::println);
        log.debug("Method ViewAllBanks finished");
    }

    private Bank createBankAndSetValuesOfAttributes(String bankName,
                                                    String commissionForIndividual,
                                                    String commissionForEntity) {
        log.debug("Creating bank '" + bankName + "'with setting its arguments started");
        final Bank bank = new Bank();
        bank.setName(bankName);
        bank.setCommissionForIndividual(Double.valueOf(commissionForIndividual));
        bank.setCommissionForEntity(Double.valueOf(commissionForEntity));
        log.debug("Creating bank '" + bankName + "'with setting its arguments finished");
        return bank;
    }

    Bank findBankByName(String bankName) {
        return bankDAO.findByName(bankName);
    }

    public boolean checkIfBankNameExist(String bankName) {
        return bankDAO.findByName(bankName) != null;
    }

}