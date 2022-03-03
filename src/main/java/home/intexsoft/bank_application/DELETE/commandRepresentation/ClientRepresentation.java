package home.intexsoft.bank_application.DELETE.commandRepresentation;

import home.intexsoft.bank_application.connection.ConnectionPoolProvider;
import home.intexsoft.bank_application.DELETE.exceptions.EntityRemoveException;
import home.intexsoft.bank_application.DELETE.exceptions.EntityRetrievalException;
import home.intexsoft.bank_application.DELETE.exceptions.EntitySavingException;
import home.intexsoft.bank_application.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepresentation {

    private static final Logger Log = LoggerFactory.getLogger(ClientRepresentation.class);

    public void addClient(String[] attributes) {
        Log.info("Adding new client");

        String clientName = attributes[0];
        String clientSurname = attributes[1];
        Integer clientStatusId = Integer.parseInt(attributes[2]);
        Integer bankId = Integer.parseInt(attributes[3]);
        Integer currencyId = Integer.parseInt(attributes[4]);
        Double amountOfMoney = Double.parseDouble(attributes[5]);

        String insertClientSQL = "insert into clients (name, surname, status_id) values (?, ?, ?)";
        String updateClientSQL = "update clients set name = ?, surname = ?, status_id = ? where id = ?";
        String insertBankClientSQL = "insert into bank_clients (bank_id, client_id) values (?, ?)";
        String insertBankAccountSQL = "insert into bank_accounts (currency_id, amount_of_money, bank_id, client_id) values (?, ?, ?, ?)";

        if (validateDataDuringAddingClient(clientName, clientSurname, clientStatusId, bankId, currencyId, amountOfMoney)) {
            PreparedStatement prStatement = null;
            Connection connection = null;
            try {
                connection = ConnectionPoolProvider.getConnection();
                connection.setSavepoint();
                connection.setAutoCommit(false);

                Client client = createClient(clientName, clientSurname, clientStatusId);
                if (client.getId() == 0) {
                    prStatement = connection.prepareStatement(insertClientSQL, prStatement.RETURN_GENERATED_KEYS);
                } else {
                    prStatement = connection.prepareStatement(updateClientSQL, prStatement.RETURN_GENERATED_KEYS);
                }
                setClientValues(client, prStatement);
                if (client.getId() != 0) {
                    prStatement.setInt(4, client.getId());
                }
                int result = prStatement.executeUpdate();
                if (result != 1) {
                    throw new EntitySavingException("Client was not added!");
                }
                ResultSet generatedKey = prStatement.getGeneratedKeys();
                if (generatedKey.next()) {
                    client.setId(generatedKey.getInt(1));
                }

                BankClient bankClient = createBankClient(bankId, client);
                prStatement = connection.prepareStatement(insertBankClientSQL);
                setBankClientValues(bankClient, prStatement);
                int secondResult = prStatement.executeUpdate();
                if (secondResult != 1) {
                    throw new EntitySavingException("BankClient was not added");
                }

                BankAccount bankAccount = createBankAccount(currencyId, bankId, amountOfMoney, client);
                prStatement = connection.prepareStatement(insertBankAccountSQL);
                setBankAccountValues(bankAccount, prStatement);
                int thirdResult = prStatement.executeUpdate();
                if (thirdResult != 1) {
                    throw new EntitySavingException("Bank account was not added");
                }
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Log.error("Error during rollback");
                }
                Log.error("Something wrong during adding bank", e);
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
            Log.info("Client was added to tables clients, bank_clients, bank_accounts");
        }
    }

    private boolean validateDataDuringAddingClient(String clientName, String clientSurname, Integer clientStatusId, Integer bankId, Integer currencyId, Double amountOfMoney) {
        boolean isValidated = false;
        try {
            if (clientName != null &
                    clientSurname != null &
                    verifyIfClientStatusIdExist(clientStatusId) &
                    !verifyIfIdOfBankExist(bankId) &
                    verifyIfCurrencyIdExist(currencyId) &
                    verifyIfIDoubleDigitAboveZero(amountOfMoney)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
        }
        return isValidated;
    }

    private boolean verifyIfCurrencyIdExist(Integer currencyId) {
        boolean isExist = false;
        List<Currency> currencies = findAllCurrency();
        for (Currency currency : currencies) {
            if (currencyId.equals(currency.getId())) {
                isExist = true;
            }
        }
        return isExist;
    }

    private ClientStatus mapClientStatus(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of client status");
        ClientStatus clientStatus = new ClientStatus();
        clientStatus.setId(resultSet.getInt("id"));
        clientStatus.setName(resultSet.getString("status_name"));
        return clientStatus;
    }

    public void removeClient(String[] attributes) {
        Log.info("Removing client");
        Integer clientId = Integer.parseInt(attributes[0]);
        if (validateDataDuringRemovingClient(clientId)) {
            String removeClientFromClients = "delete from clients where id =".concat(String.valueOf(clientId));
            String removeClientFromBankClients = "delete from bank_clients where client_id=".concat(String.valueOf(clientId));
            String removeClientFromBankAccounts = "delete from bank_accounts where client_id=".concat(String.valueOf(clientId));
            PreparedStatement prStatement = null;
            Connection connection = null;
            try {
                connection = ConnectionPoolProvider.getConnection();
                connection.setSavepoint();
                connection.setAutoCommit(false);

                prStatement = connection.prepareStatement(removeClientFromBankAccounts);
                int thirdResult = prStatement.executeUpdate();
                if (thirdResult != 1) {
                    throw new EntitySavingException("Client was not removed from 'bank_accounts'!");
                }

                prStatement = connection.prepareStatement(removeClientFromBankClients);
                int secondResult = prStatement.executeUpdate();
                if (secondResult != 1) {
                    throw new EntitySavingException("Client was not removed from 'bank_clients'!");
                }

                prStatement = connection.prepareStatement(removeClientFromClients);
                int result = prStatement.executeUpdate();
                if (result != 1) {
                    throw new EntitySavingException("Client was not removed from 'clients'!");
                }
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Log.error("Error during rollback");
                }
                Log.error("Something wrong during adding bank", e);
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
            Log.info("Client with id = " + clientId + " was removed");
        }
    }

    public Boolean validateDataDuringRemovingClient(Integer clientId) {
        boolean isValidated = false;
        try {
            if (verifyIfIdOfClientExist(clientId)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
        }
        return isValidated;
    }

    public Boolean validateDataDuringAddClient(Integer clientId) {
        boolean isValidated = false;
        try {
            if (verifyIfIdOfClientExist(clientId)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
        }
        return isValidated;
    }

    public void viewAllClients() {
        Log.info("ViewAllClients method started");
        String selectAll = "select * from clients";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)) {
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                Client client = mapClient(resultSet);
                clients.add(client);
            }
            for (Client client : clients) {
                System.out.println(client.toString());
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval clients ", e);
            throw new EntityRetrievalException(e);
        }
        Log.info("All clients were viewed");
    }

    public void removeAllClients() {
        Log.info("Removing all clients");
        String removeAllClients = "delete * from clients";
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(removeAllClients);
        } catch (SQLException e) {
            Log.error("Something wrong during removing all clients", e);
            throw new EntityRemoveException(e);
        }
        Log.info("All clients were removed");
    }

    public void addClientsBankAccount(String[] attributes) {
        Log.info("Adding new bankAccount");

        Integer currencyId = Integer.parseInt(attributes[0]);
        Double amountOfMoney = Double.parseDouble(attributes[1]);
        Integer bankId = Integer.parseInt(attributes[2]);
        Integer clientId = Integer.parseInt(attributes[3]);

        if (validateDataDuringAddingClientsBankAccount(currencyId, amountOfMoney, bankId, clientId)) {
            String insertBankAccountSQL = "insert into bank_accounts (currency_id, amount_of_money, bank_id, client_id) values (?, ?, ?, ?)";
            String updateBankAccountSQL = "update bank_accounts set currency_id = ?, amount_of_money = ?, bank_id = ?, client_id = ? where id = ?";

            PreparedStatement prStatement = null;
            Connection connection = null;
            try {
                connection = ConnectionPoolProvider.getConnection();
                connection.setSavepoint();
                connection.setAutoCommit(false);
                BankAccount bankAccount = addBankAccount(currencyId, bankId, amountOfMoney, clientId);
                if (bankAccount.getId() == 0) {
                    prStatement = connection.prepareStatement(insertBankAccountSQL, prStatement.RETURN_GENERATED_KEYS);
                } else {
                    prStatement = connection.prepareStatement(updateBankAccountSQL, prStatement.RETURN_GENERATED_KEYS);
                }
                setBankAccountValues(bankAccount, prStatement);
                if (bankAccount.getId() != 0) {
                    prStatement.setInt(5, bankAccount.getId());
                }
                int result = prStatement.executeUpdate();
                if (result != 1) {
                    throw new EntitySavingException("Bank account was not added!");
                }
                ResultSet generatedKey = prStatement.getGeneratedKeys();
                if (generatedKey.next()) {
                    bankAccount.setId(generatedKey.getInt(1));
                }
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Log.error("Error during rollback");
                }
                Log.error("Something wrong during adding bank account", e);
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
            Log.info("Bank account was added");
        }
    }

    private Boolean validateDataDuringAddingClientsBankAccount(Integer currencyId, Double amountOfMoney, Integer bankId, Integer clientId) {
        boolean isValidated = false;
        try {
            if (verifyIfIdOfClientExist(clientId) &
                    verifyIfIdOfBankExist(bankId) &
                    verifyIfCurrencyIdExist(currencyId) &
                    verifyIfIDoubleDigitAboveZero(amountOfMoney)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
        }
        return isValidated;
    }

    public List<BankAccount> findClientsBankAccount(String[] attributes) {
        Log.info("Finding bank accounts of clients");
        Integer clientId = Integer.parseInt(attributes[0]);

        List<BankAccount> accounts = new ArrayList<>();
        if (validateDataDuringAddClient(clientId)) {
            String findAllAccountsByClientId = "select bank_accounts.id, currency.currency_name, bank_accounts.amount_of_money, banks.bank_name, clients.name, clients.surname from bank_accounts inner join currency on bank_accounts.currency_id = currency.id inner join banks on bank_accounts.bank_id = banks.id inner join clients on bank_accounts.client_id = clients.id where client_id=".concat(String.valueOf(clientId));
            Connection connection = null;
            Statement statement = null;
            try {
                connection = ConnectionPoolProvider.getConnection();
                connection.setSavepoint();
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllAccountsByClientId);
                accounts = new ArrayList<>();
                while (resultSet.next()) {
                    BankAccount bankAccount = setBankAccount(resultSet);
                    accounts.add(bankAccount);
                }
                connection.commit();

            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Log.error("Error during rollback");
                }
                Log.error("Something wrong during retrieval entity ", e);
                throw new EntityRetrievalException(e);
            }

            Log.info("All bank accounts of client with id = " + clientId + " were found");
        }
        return accounts;
    }

    public List<Client> findAllClients() {
        Log.info("FindAllClients method started");
        String selectAll = "select * from clients";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)) {
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                Client client = mapClient(resultSet);
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval clients ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public List<ClientStatus> findAllStatuses() {
        String selectAll = "select * from client_status";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(selectAll))) {
            List<ClientStatus> statuses = new ArrayList<>();
            while (resultSet.next()) {
                ClientStatus clientStatus = mapClientStatus(resultSet);
                statuses.add(clientStatus);
            }
            return statuses;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public List<Currency> findAllCurrency() {
        String selectAll = "select * from currency";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)) {
            List<Currency> currencies = new ArrayList<>();
            while (resultSet.next()) {
                Currency currency = mapCurrency(resultSet);
                currencies.add(currency);
            }
            return currencies;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Currency mapCurrency(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of currency");
        Currency currency = new Currency();
        currency.setId(resultSet.getInt("id"));
        currency.setName(resultSet.getString("currency_name"));
        currency.setRate(resultSet.getDouble("rate"));
        return currency;
    }

    private Client createClient(String clientName, String clientSurname, Integer clientStatusId) {
        Log.info("Creation of client");
        Client client = new Client();
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setStatusId(clientStatusId);
        return client;
    }

    private BankClient createBankClient(Integer bankId, Client client) {
        Log.info("Creation of bank client");
        BankClient bankClient = new BankClient();
        bankClient.setBankId(bankId);
        bankClient.setClientId(client.getId());
        return bankClient;
    }

    private BankAccount createBankAccount(Integer currencyId, Integer bankId, Double amountOfMoney, Client client) {
        Log.info("Creation of bank account");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCurrencyId(currencyId);
        bankAccount.setAmountOfMoney(amountOfMoney);
        bankAccount.setBankId(bankId);
        bankAccount.setClientId(client.getId());
        return bankAccount;
    }

    private void setClientValues(Client client, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting client values started");
        prStatement.setString(1, client.getName());
        prStatement.setString(2, client.getSurname());
        prStatement.setInt(3, client.getStatusId());
    }

    private void setBankClientValues(BankClient bankClient, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bank client values started");
        prStatement.setInt(1, bankClient.getBankId());
        prStatement.setInt(2, bankClient.getClientId());
    }

    private void setBankAccountValues(BankAccount bankAccount, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bank account values started");
        prStatement.setInt(1, bankAccount.getCurrencyId());
        prStatement.setDouble(2, bankAccount.getAmountOfMoney());
        prStatement.setInt(3, bankAccount.getBankId());
        prStatement.setInt(4, bankAccount.getClientId());
    }

    private Client mapClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setSurname(resultSet.getString("surname"));
        client.setStatusId(resultSet.getInt("status_id"));
        return client;
    }

    private BankAccount addBankAccount(Integer currencyId, Integer bankId, Double amountOfMoney, Integer clientId) {
        Log.info("Adding of bank account");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCurrencyId(currencyId);
        bankAccount.setAmountOfMoney(amountOfMoney);
        bankAccount.setBankId(bankId);
        bankAccount.setClientId(clientId);
        return bankAccount;

    }

    private BankAccount setBankAccount(ResultSet resultSet) throws SQLException {
        Log.info("Creation of bank account");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(resultSet.getInt("id"));
        bankAccount.setClientName(resultSet.getString("name"));
        bankAccount.setClientSurname(resultSet.getString("surname"));
        bankAccount.setBankName(resultSet.getString("bank_name"));
        bankAccount.setCurrencyName(resultSet.getString("currency_name"));
        bankAccount.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
        return bankAccount;
    }

    private boolean verifyIfClientStatusIdExist(Integer clientStatusId) {
        boolean isExist = false;
        List<ClientStatus> statuses = findAllStatuses();
        for (ClientStatus status : statuses) {
            if (clientStatusId.equals(status.getId())) {
                isExist = true;
            }
        }
        return isExist;
    }

    private Boolean verifyIfIdOfBankExist(Integer bankId) {
        boolean isExist = false;
        List<Bank> banks = new BankRepresentation().findAllBanks();
        for (Bank bank : banks) {
            if (bankId.equals(bank.getId())) {
                isExist = true;
            }
        }
        return isExist;
    }

    private boolean verifyIfIdOfClientExist(Integer clientId) {
        boolean isExist = false;
        List<Client> clients = findAllClients();
        for (Client client : clients) {
            if (clientId.equals(client.getId())) {
                isExist = true;
            }
        }
        return isExist;
    }

    private Boolean verifyIfIntegerDigitAboveZero(Integer clientId) {
        boolean isAboveZero = true;
        if (clientId <= 0) {
            isAboveZero = false;
        }
        return isAboveZero;
    }

//    public boolean validateDataDuringFindBankAccountOfClient(String[] attributes) {
//        boolean isValidated = false;
//        try {
//            Integer clientId = Integer.parseInt(attributes[0]);
//            if (verifyIfClientExist(clientId) &
//                    verifyIfIntegerDigitAboveZero(clientId)) {
//                isValidated = true;
//            } else {
//                System.out.println("Client with such Id doesn't exist");
//            }
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Wrong data were entered");
//        }
//        return isValidated;
//    }

    private Boolean verifyIfIDoubleDigitAboveZero(Double digit) {
        boolean isAboveZero = true;
        if (digit <= 0) {
            isAboveZero = false;
        }
        return isAboveZero;
    }
}
