package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankAccountDAO;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Client;
import home.intexsoft.bank_application.models.Currency;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class BankAccountService {

    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);
    private BankAccountDAO bankAccountDAO = new BankAccountDAO();
    private ClientService clientService = new ClientService();
    private CurrencyService currencyService = new CurrencyService();

    public void addBankAccount(String bankName, String clientName, String clientSurname,
                               String clientStatus, String currencyName, String amountOfMoney) {
        log.debug("Method addBankAccount started");
        BankAccount bankAccount = createBankAccountAndSetValuesOfAttributes(
                bankName, clientName, clientSurname, clientStatus, currencyName, amountOfMoney);
        this.bankAccountDAO.create(bankAccount);
        log.debug("Method addBankAccount finished");
    }

    void updateBankAccountWithMoney(Action action, Session session) throws SQLException {
        log.debug("Updating of bankAccount started");
        try {
            switch (action.getActionType()) {
                case "withdraw":
                    withdrawMoneyFromBankAccount(action, session);
                    break;
                case "addition":
                    addMoneyToBankAccount(action, session);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported action type");
            }
        } finally {
            log.debug("Method of bankAccount updating finished");
        }
    }

    private void addMoneyToBankAccount(Action action, Session session) throws SQLException {
        BankAccount bankAccount = bankAccountDAO.findById(action.getBankAccount().getId());
        Double amountOfMoneyToAdd = action.getAmountOfMoney();
        bankAccount.setAmountOfMoney(bankAccount.getAmountOfMoney() + amountOfMoneyToAdd);
        bankAccountDAO.updateBankAccount(bankAccount, session);
    }

    private void withdrawMoneyFromBankAccount(Action action, Session session) throws SQLException {
        BankAccount bankAccount = bankAccountDAO.findById(action.getBankAccount().getId());
        Double amountOfMoneyToWithdraw = action.getAmountOfMoney();
        if (amountOfMoneyToWithdraw < bankAccount.getAmountOfMoney()) {
            bankAccount.setAmountOfMoney(bankAccount.getAmountOfMoney() - amountOfMoneyToWithdraw);
            bankAccountDAO.updateBankAccount(bankAccount, session);
        }
    }

    private BankAccount createBankAccountAndSetValuesOfAttributes(
            String bankName, String clientName, String clientSurname,
            String clientStatus, String currencyName, String amountOfMoney) {
        log.debug("Creating bankAccount with setting its arguments started");
        final BankAccount bankAccount = new BankAccount();
        Client clientByName = clientService.findByName(clientName);
        if (clientByName != null) {
            bankAccount.setClient(clientByName);
        } else {
            clientService.addClient(clientName, clientSurname, clientStatus, bankName);
            Client foundClient = clientService.findByName(clientName);
            bankAccount.setClient(foundClient);
        }
        bankAccount.setAmountOfMoney(Double.valueOf(amountOfMoney));
        Currency foundCurrency = currencyService.findByName(currencyName);
        bankAccount.setCurrency(foundCurrency);
        log.debug("Creating bankAccount with setting its arguments finished");
        return bankAccount;
    }

    public void findBankAccountsOfClient(String clientName) {
        log.debug("Method findBankAccountsOfClient started");
        Client clientByName = clientService.findByName(clientName);
        Integer clientId = clientByName.getId();
        List<BankAccount> bankAccountList = bankAccountDAO.findBankAccountsOfClient(clientId);
        bankAccountList.forEach(System.out::println);
        log.debug("Method findBankAccountsOfClient finished");
    }

    public boolean checkIfBankAccountExist(String bankAccount) {
        int account = Integer.parseInt(bankAccount);
        return bankAccountDAO.findById(account) != null;
    }

    public BankAccount findBankAccountById(Integer bankAccountId) {
        log.debug("Method findBankAccountById started");
        BankAccount bankAccount = bankAccountDAO.findById(bankAccountId);
        log.debug("Method findBankAccountById finished");
        return bankAccount;
    }

}
