package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.TransactionDAO;

public class TransactionService extends ModelService {

    private TransactionDAO transactionDAO = new TransactionDAO();

    public void addTransaction(
            String clientName, String clientSurname, String senderBankAccountId,String recipientBankAccountId,
            String currencyName, String amountOfMoney, String currentDate) {

    }

}
