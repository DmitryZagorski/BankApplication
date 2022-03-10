//package home.intexsoft.bank_application.DELETE.commandRepresentation;
//
//import home.intexsoft.bank_application.connection.ConnectionPoolProvider;
//import home.intexsoft.bank_application.DELETE.exceptions.EntityRemoveException;
//import home.intexsoft.bank_application.DELETE.exceptions.EntityRetrievalException;
//import home.intexsoft.bank_application.DELETE.exceptions.EntitySavingException;
//import home.intexsoft.bank_application.models.Bank;
//import home.intexsoft.bank_application.models.BankAccount;
//import home.intexsoft.bank_application.models.BankClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BankRepresentation {
//
//    // package commandRepresentation
//// Выполняет команду и валидирует её  (данный класс работает в свзке с CommandDescriptor)
//// Этот класс знает, что именно делать, а CommandDescriptor знает, с чем именно это делать
//// Возможно просто создать метод Execute и метод Validate
//// Вызываем Validate и передаем в него Descriptor
//// Данный класс проверяет наличие данных для выполнения команды
//
//    private static final Logger Log = LoggerFactory.getLogger(BankRepresentation.class);
//
//    public void addBank(String[] attributes) {
//        Log.info("Adding new bank");
//        if (validateDataDuringAddingBank(attributes)) {
//            String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values ('".concat(attributes[0].concat("',").concat(attributes[1].concat(",".concat(attributes[2].concat(")")))));
//            PreparedStatement prStatement = null;
//            Connection connection = null;
//            try {
//                connection = ConnectionPoolProvider.getConnection();
//                connection.setSavepoint();
//                connection.setAutoCommit(false);
//
//                prStatement = connection.prepareStatement(insertBankSQL);
//                int result = prStatement.executeUpdate();
//                if (result != 1) {
//                    throw new EntitySavingException("Bank was not added!");
//                }
//                connection.commit();
//            } catch (SQLException e) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    Log.info("Error during rollback");
//                }
//                Log.error("Something wrong during adding bank", e);
//                throw new EntitySavingException(e);
//            } finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                if (prStatement != null) {
//                    try {
//                        prStatement.close();
//                    } catch (SQLException e) {
//                        throw new EntitySavingException(e);
//                    }
//                }
//            }
//        } else System.out.println("Wrong attributes for adding bank");
//        Log.info("Bank " + attributes[0] + " was added");
//    }
//
//    public void viewAllBanks() {
//        Log.info("ViewAllBanks method started");
//        String selectAll = "select * from banks";
//        try (Connection connection = ConnectionPoolProvider.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(selectAll)) {
//            List<Bank> banks = new ArrayList<>();
//            while (resultSet.next()) {
//                Bank bank = mapBank(resultSet);
//                banks.add(bank);
//            }
//            for (Bank bank : banks) {
//                System.out.println(bank.toString());
//            }
//        } catch (SQLException e) {
//            Log.error("Something wrong during retrieval entity ", e);
//            throw new EntityRetrievalException(e);
//        }
//        Log.info("All banks were viewed");
//    }
//
//    public List<Bank> findAllBanks() {
//        Log.info("FindAllBanks method started");
//        String selectAll = "select * from banks";
//        try (Connection connection = ConnectionPoolProvider.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(selectAll)) {
//            List<Bank> banks = new ArrayList<>();
//            while (resultSet.next()) {
//                Bank bank = mapBank(resultSet);
//                banks.add(bank);
//            }
//            return banks;
//        } catch (SQLException e) {
//            Log.error("Something wrong during retrieval entity ", e);
//            throw new EntityRetrievalException(e);
//        }
//    }
//
//    public void viewClientsOfBank(String[] attributes) {
//        Log.info("Getting clients of bank by bankId");
//        Integer bankId = Integer.parseInt(attributes[0]);
//        if (validateDataDuringFindClientsOfBank(bankId)) {
//            String findClientsOfBankByBankName = "select bank_clients.id, clients.name, clients.surname from bank_clients inner join clients on bank_clients.client_id = clients.id where bank_id = ".concat(String.valueOf(bankId));
//            Connection connection;
//            try {
//                connection = ConnectionPoolProvider.getConnection();
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(findClientsOfBankByBankName);
//                List<BankClient> bankClients = new ArrayList<>();
//                while (resultSet.next()) {
//                    BankClient bankClient = mapBankClient(resultSet);
//                    bankClients.add(bankClient);
//                }
//                for (BankClient bankClient : bankClients) {
//                    System.out.println(bankClient.toString());
//                }
//            } catch (SQLException e) {
//                Log.error("Something wrong during retrieval entity ", e);
//                throw new EntityRetrievalException(e);
//            }
//        } else System.out.println("Wrong attributes during viewing clients of bank");
//        Log.info("Clients of bank with Id = " + bankId + " were viewed");
//    }
//
//    public void removeBank(String[] attributes) {
//        Log.info("Removing bank by bankId");
//        Integer bankId = Integer.parseInt(attributes[0]);
//        if (verifyIfIdOfBankExist(bankId)) {
//            String removeByBankName = "delete from banks where id = '".concat(String.valueOf(bankId)).concat("'");
//            try (Connection connection = ConnectionPoolProvider.getConnection()) {
//                Statement statement = connection.createStatement();
//                int removing = statement.executeUpdate(removeByBankName);
//                if (removing != 1) {
//                    Log.error("Bank with that bankId doesn't exist.");
//                }
//            } catch (SQLException e) {
//                Log.error("Something wrong during removing bank by id " + bankId, e);
//                throw new EntityRemoveException(e);
//            }
//        } else System.out.println("Wrong bankId");
//        Log.info("Bank with id = " + bankId + " was removed");
//    }
//
//    public void removeAllBanks() {
//        Log.info("Removing all banks");
//        String removeAllBanks = "delete * from banks";
//        try (Connection connection = ConnectionPoolProvider.getConnection()) {
//            Statement statement = connection.createStatement();
//            statement.execute(removeAllBanks);
//        } catch (SQLException e) {
//            Log.error("Something wrong during removing all banks", e);
//            throw new EntityRemoveException(e);
//        }
//    }
//
//    public List<BankAccount> findAvailableBankAccountsForRecipient(Integer clientId) {
//        Log.info("Finding available bank account for recipient");
//        String findAllAccountsByClientId = "select bank_accounts.id, currency.currency_name, banks.bank_name, clients.name, clients.surname from bank_accounts inner join currency on bank_accounts.currency_id = currency.id inner join banks on bank_accounts.bank_id = banks.id inner join clients on bank_accounts.client_id = clients.id where client_id != ".concat(String.valueOf(clientId));
//        Connection connection;
//        try {
//            connection = ConnectionPoolProvider.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(findAllAccountsByClientId);
//            List<BankAccount> accounts = new ArrayList<>();
//            while (resultSet.next()) {
//                BankAccount bankAccount = createAnotherBankAccount(resultSet);
//                accounts.add(bankAccount);
//            }
//            return accounts;
//        } catch (SQLException e) {
//            Log.error("Something wrong during retrieval entity ", e);
//            throw new EntityRetrievalException(e);
//        }
//    }
//
//    private BankAccount createAnotherBankAccount(ResultSet resultSet) throws SQLException {
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setId(resultSet.getInt("id"));
//        bankAccount.setClientName(resultSet.getString("name"));
//        bankAccount.setClientSurname(resultSet.getString("surname"));
//        bankAccount.setBankName(resultSet.getString("bank_name"));
//        bankAccount.setCurrencyName(resultSet.getString("currency_name"));
//        return bankAccount;
//    }
//
//    private BankClient mapBankClient(ResultSet resultSet) throws SQLException {
//        BankClient bankClient = new BankClient();
//        bankClient.setId(resultSet.getInt("id"));
//        bankClient.setClientName(resultSet.getString("name"));
//        bankClient.setClientSurname(resultSet.getString("surname"));
//        return bankClient;
//    }
//
//    private Bank mapBank(ResultSet resultSet) throws SQLException {
//        Bank bank = new Bank();
//        bank.setId(resultSet.getInt("id"));
//        bank.setName(resultSet.getString("bank_name"));
//        bank.setCommissionForIndividual(resultSet.getDouble("commission_for_individual"));
//        bank.setCommissionForEntity(resultSet.getDouble("commission_for_entity"));
//        return bank;
//    }
//
//    private boolean validateDataDuringAddingBank(String[] attributes) {
//        boolean isValidated = false;
//        try {
//            String bankName = attributes[0];
//            Double commissionForIndividual = Double.parseDouble(attributes[1]);
//            Double commissionForEntity = Double.parseDouble(attributes[2]);
//            if (!verifyIfNameOfBankExist(bankName) &
//                    verifyIfIDoubleDigitAboveZero(commissionForIndividual) &
//                    verifyIfIDoubleDigitAboveZero(commissionForEntity)) {
//                isValidated = true;
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Wrong data were entered.");
//        }
//        return isValidated;
//    }
//
//    private Boolean verifyIfNameOfBankExist(String bankName) {
//        boolean isExist = false;
//        List<Bank> banks = findAllBanks();
//        for (Bank bank : banks) {
//            if (bankName.equalsIgnoreCase(bank.getName())) {
//                isExist = true;
//            }
//        }
//        return isExist;
//    }
//
//    private Boolean verifyIfIdOfBankExist(Integer bankId) {
//        boolean isExist = false;
//        List<Bank> banks = findAllBanks();
//        for (Bank bank : banks) {
//            if (bankId.equals(bank.getId())) {
//                isExist = true;
//            }
//        }
//        return isExist;
//    }
//
//    private Boolean verifyIfIDoubleDigitAboveZero(Double digit) {
//        boolean isAboveZero = true;
//        if (digit <= 0) {
//            isAboveZero = false;
//        }
//        return isAboveZero;
//    }
//
//    private Boolean validateDataDuringFindClientsOfBank(Integer bankId) {
//        boolean isValidated = true;
//        if (!verifyIfIdOfBankExist(bankId)) {
//            isValidated = false;
//            System.out.println("That bank doesn't exist");
//        }
//        return isValidated;
//    }
//}
