package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankAccountDAO;
import home.intexsoft.bank_application.models.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccountService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);
    private BankAccountDAO bankAccountDAO = new BankAccountDAO();

    public void addBankAccount(String currencyId, String amountOfMoney, String bankId, String clientId) {
        log.debug("Method addBankAccount started");
        BankAccount bankAccount = createBankAccountAndSetValuesOfAttributes(currencyId, amountOfMoney, bankId, clientId);
        this.bankAccountDAO.create(bankAccount);
        log.debug("Method addBankAccount finished");
    }

    private BankAccount createBankAccountAndSetValuesOfAttributes(String currencyId, String amountOfMoney, String bankId, String clientId) {
        log.debug("Creating bankAccount with setting its arguments started");
        final BankAccount bankAccount = new BankAccount();
        bankAccount.setCurrencyId(Integer.valueOf(currencyId));
        bankAccount.setAmountOfMoney(Double.valueOf(amountOfMoney));
        bankAccount.setBankId(Integer.valueOf(bankId));
        bankAccount.setClientId(Integer.valueOf(clientId));
        log.debug("Creating bankAccount with setting its arguments finished");
        return bankAccount;
    }

}
