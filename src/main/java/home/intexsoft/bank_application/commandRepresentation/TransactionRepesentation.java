package home.intexsoft.bank_application.commandRepresentation;

import home.intexsoft.bank_application.connection.ConnectionPoolProvider;
import home.intexsoft.bank_application.exceptions.EntityRetrievalException;
import home.intexsoft.bank_application.exceptions.EntitySavingException;
import home.intexsoft.bank_application.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepesentation {

    private static final Logger Log = LoggerFactory.getLogger(TransactionRepesentation.class);

    public void addTransaction(String[] attributes) {
        Log.info("Adding transaction");

        String insertIntoTransactionSQL = "insert into transactions (client_id, sender_bank_account_id, recipient_bank_account_id, currency_id, amount_of_money, creation_date) values (?,?,?,?,?,?)";
        String updateTransactionSQL = "update transactions set client_id = ?, sender_bank_account_id = ?, recipient_bank_account_id = ?, currency_id = ?, amount_of_money = ?, creation_date = ? where id = ?";

        Integer clientId = Integer.parseInt(attributes[0]);
        Integer senderAccountId = Integer.parseInt(attributes[1]);
        Integer recipientAccountId = Integer.parseInt(attributes[2]);
        Integer currencyId = Integer.parseInt(attributes[3]);
        Double amountOfMoney = Double.parseDouble(attributes[4]);
        Date creationDate = new Date(System.currentTimeMillis());

        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            Transaction transaction = createTransaction1(clientId, senderAccountId, recipientAccountId, currencyId, amountOfMoney, creationDate);
            if (transaction.getId() == 0) {
                prStatement = connection.prepareStatement(insertIntoTransactionSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateTransactionSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setTransactionValues(transaction, prStatement);
            if (transaction.getId() != 0) {
                prStatement.setInt(7, transaction.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Transaction was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                transaction.setId(generatedKey.getInt(1));
            }

            Integer clientStatusId = getStatusIdByClientId(clientId, connection);
            Double commission = commissionController(senderAccountId, recipientAccountId, clientStatusId, connection);
            Double amountOfCommission = commission * amountOfMoney;

            Double moneyOfSender = getAmountOfMoneyByBankAccountId(senderAccountId);
            Double moneyOfRecipient = getAmountOfMoneyByBankAccountId(recipientAccountId);
            Integer currencyIdOfRecipient = getCurrencyIdOfBankAccountByAccountId(recipientAccountId);

            String updateSenderAccount = "update bank_accounts set amount_of_money = ".concat(String.valueOf(moneyOfSender - amountOfMoney - amountOfCommission)) + " where id = ".concat(String.valueOf(senderAccountId));
            prStatement = connection.prepareStatement(updateSenderAccount);
            int secondResult = prStatement.executeUpdate();
            if (secondResult != 1) {
                throw new EntitySavingException("Sender account was not updated");
            }

            Double receivedMoney = currencyConverter(currencyId, currencyIdOfRecipient, amountOfMoney, connection);
            String updateRecipientMoney = "update bank_accounts set amount_of_money = ".concat(String.valueOf(moneyOfRecipient + receivedMoney)) + " where id = ".concat(String.valueOf(recipientAccountId));
            prStatement = connection.prepareStatement(updateRecipientMoney);
            int thirdResult = prStatement.executeUpdate();
            if (thirdResult != 1) {
                throw new EntitySavingException("Recipient account was not updated");
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Log.error("Error during rollback");
            }
            Log.error("Something wrong during adding transaction", e);
            throw new EntitySavingException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
        Log.info("Transaction was added");
    }

    public Integer getCurrencyIdOfBankAccountByAccountId(Integer accountId) {
        Log.info("Getting currencyId of bank account by bank accountId");
        String findCurrencyByAccountId = "select currency_id from bank_accounts where id =".concat(String.valueOf(accountId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findCurrencyByAccountId);
            BankAccount bankAccount = new BankAccount();
            if (resultSet.next()) {
                bankAccount.setCurrencyId(resultSet.getInt("currency_id"));
            }
            return bankAccount.getCurrencyId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Double getAmountOfMoneyByBankAccountId(Integer bankAccountId) {
        Log.info("Getting amount of money By bank account");
        String findMoneyByBankAccount = "select bank_accounts.amount_of_money from bank_accounts where id = ".concat(String.valueOf(bankAccountId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findMoneyByBankAccount);
            BankAccount bankAccount = new BankAccount();
            while (resultSet.next()) {
                bankAccount.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
            }
            return bankAccount.getAmountOfMoney();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public List<Transaction> findClientsTransactions(String[] attributes) {
        Log.info("Getting list of transactions by clientId and date");
        Integer clientId = Integer.parseInt(attributes[0]);
        String getTransaction = "select transactions.id, clients.name, clients.surname, transactions.sender_bank_account_id, transactions.recipient_bank_account_id, currency.currency_name, transactions.amount_of_money, transactions.creation_date from transactions inner join clients on client_id = clients.id inner join currency on transactions.currency_id = currency.id where client_id =".concat(String.valueOf(clientId));
        List<Transaction> transactions = new ArrayList<>();
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getTransaction);
            while (resultSet.next()) {
                Transaction transaction = createTransaction(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
        return transactions;
    }

    private Transaction createTransaction(ResultSet resultSet) throws SQLException {
        Log.info("Creation transaction");
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setClientName(resultSet.getString("name"));
        transaction.setClientSurname(resultSet.getString("surname"));
        transaction.setSenderBankAccountId(resultSet.getInt("sender_bank_account_id"));
        transaction.setRecipientBankAccountId(resultSet.getInt("recipient_bank_account_id"));
        transaction.setCurrencyName(resultSet.getString("currency_name"));
        transaction.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
        transaction.setCreationDate(resultSet.getDate("creation_date"));
        return transaction;
    }

    private Transaction createTransaction1(Integer clientId, Integer senderAccountId, Integer recipientAccountId, Integer currencyId, Double amountOfMoney, Date creationDate) {
        Log.info("Creation of transaction");
        Transaction transaction = new Transaction();
        transaction.setClientId(clientId);
        transaction.setSenderBankAccountId(senderAccountId);
        transaction.setRecipientBankAccountId(recipientAccountId);
        transaction.setCurrencyId(currencyId);
        transaction.setAmountOfMoney(amountOfMoney);
        transaction.setCreationDate(creationDate);
        return transaction;
    }

    private void setTransactionValues(Transaction transaction, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting transaction values started");
        prStatement.setInt(1, transaction.getClientId());
        prStatement.setInt(2, transaction.getSenderBankAccountId());
        prStatement.setInt(3, transaction.getRecipientBankAccountId());
        prStatement.setInt(4, transaction.getCurrencyId());
        prStatement.setDouble(5, transaction.getAmountOfMoney());
        prStatement.setDate(6, transaction.getCreationDate());
    }

    private Double currencyConverter(Integer senderCurrencyId, Integer recipientCurrencyId, Double amountOfMoney, Connection connection) throws SQLException {
        Log.info("Converter started");
        Double senderRate = getRateOfCurrency(senderCurrencyId, connection);
        Double recipientRate = getRateOfCurrency(recipientCurrencyId, connection);
        return senderRate / recipientRate * amountOfMoney;
    }

    private Double commissionController(Integer senderAccountId, Integer recipientAccountId, Integer clientStatusId, Connection connection) {
        Log.info("Commission controller started");
        Integer senderBankId = getBankIdByBankAccountId(senderAccountId, connection);
        Integer recipientBankId = getBankIdByBankAccountId(recipientAccountId, connection);
        Double commission = 0.0;
        if (senderBankId.equals(recipientBankId)) {
            return commission;
        } else {
            commission = getCommissionOfBankByBankAccountId(recipientAccountId, clientStatusId, connection);
        }
        return commission;
    }

    private Integer getBankIdByBankAccountId(Integer bankAccountId, Connection connection) {
        Log.info("Getting bank id by Account id");
        String getBank = "select bank_id from bank_accounts where id =".concat(String.valueOf(bankAccountId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getBank);
            BankAccount bankAccount = new BankAccount();
            if (resultSet.next()) {
                bankAccount.setBankId(resultSet.getInt("bank_id"));
            }
            return bankAccount.getBankId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Double getCommissionOfBankByBankAccountId(Integer bankAccountId, Integer statusId, Connection connection) {
        Log.info("Getting commission of bank by bank account");
        String getCommissionForIndividual = "select commission_for_individual from banks where id =".concat(String.valueOf(bankAccountId));
        String getCommissionForEntity = "select commission_for_entity from banks where id =".concat(String.valueOf(bankAccountId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            Double commission = null;
            Bank bank = new Bank();
            if (statusId == 1) {
                resultSet = statement.executeQuery(getCommissionForIndividual);
                if (resultSet.next()) {
                    bank.setCommissionForIndividual(resultSet.getDouble("commission_for_individual"));
                    commission = bank.getCommissionForIndividual();
                }
            } else {
                resultSet = statement.executeQuery(getCommissionForEntity);
                if (resultSet.next()) {
                    bank.setCommissionForEntity(resultSet.getDouble("commission_for_entity"));
                    commission = bank.getCommissionForEntity();
                }
            }
            return commission;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Integer getStatusIdByClientId(Integer clientId, Connection connection) {
        Log.info("Getting status of client by clientId");
        String getStatusId = "select status_id from clients where id =".concat(String.valueOf(clientId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStatusId);
            Client client = new Client();
            if (resultSet.next()) {
                client.setStatusId(resultSet.getInt("status_id"));
            }
            return client.getStatusId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Double getRateOfCurrency(Integer currencyId, Connection connection) {
        Log.info("Getting rate of currency");
        String getRateOfCurrency = "select rate from currency where id =".concat(String.valueOf(currencyId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRateOfCurrency);
            Currency currency = new Currency();
            if (resultSet.next()) {
                currency.setRate(resultSet.getDouble("rate"));
            }
            return currency.getRate();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

}
