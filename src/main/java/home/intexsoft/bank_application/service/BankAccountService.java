package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankAccountDAO;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Client;
import home.intexsoft.bank_application.models.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    void executeActionList(List<Action> actions) {
        actions.forEach(this::updateBankAccountWithMoney);
    }

    private void updateBankAccountWithMoney(Action action) {
        log.debug("Updating of bankAccount started");
        switch (action.getActionType()) {
            case WITHDRAW:
                withdrawMoneyFromBankAccount(action);
                break;
            case ADDITION:
                addMoneyToBankAccount(action);
                break;
            default:
                throw new IllegalArgumentException("Unsupported action type");
        }
    }

    private void addMoneyToBankAccount(Action action) {
        BankAccount bankAccount = bankAccountDAO.findById(action.getBankAccount().getId());
        bankAccount.setAmountOfMoney(bankAccount.getAmountOfMoney() + action.getAmountOfMoney());
        bankAccountDAO.updateBankAccount(bankAccount);
    }

    private void withdrawMoneyFromBankAccount(Action action) {
        BankAccount bankAccount = bankAccountDAO.findById(action.getBankAccount().getId());
        Double amountOfMoneyToWithdraw = action.getAmountOfMoney();
        if (amountOfMoneyToWithdraw < bankAccount.getAmountOfMoney()) {
            bankAccount.setAmountOfMoney(bankAccount.getAmountOfMoney() - amountOfMoneyToWithdraw);
            bankAccountDAO.updateBankAccount(bankAccount);
        } else {
            throw new IllegalArgumentException("Not enough money for transfer");
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

    public List<BankAccount> findBankAccountsOfClient(String clientName) {
        log.debug("Method findBankAccountsOfClient started");
        Client clientByName = clientService.findByName(clientName);
        Integer clientId = clientByName.getId();
        List<BankAccount> bankAccountList = bankAccountDAO.findBankAccountsOfClient(clientId);
        bankAccountList.forEach(System.out::println);
        log.debug("Method findBankAccountsOfClient finished");
        return bankAccountList;
    }

    public boolean checkIfBankAccountExist(String bankAccount) {
        int account = Integer.parseInt(bankAccount);
        return bankAccountDAO.findById(account) != null;
    }
}