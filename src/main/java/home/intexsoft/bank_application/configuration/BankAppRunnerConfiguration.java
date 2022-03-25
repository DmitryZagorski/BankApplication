package home.intexsoft.bank_application.configuration;

import home.intexsoft.bank_application.Menu;
import home.intexsoft.bank_application.MenuItem;
import home.intexsoft.bank_application.command.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("home.intexsoft.bank_application")
public class BankAppRunnerConfiguration {

    @Bean
    public Menu menu() {

        Menu menu = new Menu();

        MenuItem mainMenuItem = new MenuItem();
        mainMenuItem.setName(null);
        mainMenuItem.setParent(mainMenuItem);

        MenuItem banksItem = new MenuItem();
        banksItem.setName(Command.CommandType.BANKS);
        banksItem.setParent(mainMenuItem);

        MenuItem addBankItem = new MenuItem();
        addBankItem.setCommand(Boolean.TRUE);
        addBankItem.setName(Command.CommandType.ADD_BANK);
        addBankItem.setParent(banksItem);

        MenuItem deleteBankItem = new MenuItem();
        deleteBankItem.setCommand(Boolean.TRUE);
        deleteBankItem.setName(Command.CommandType.DELETE_BANK);
        deleteBankItem.setParent(banksItem);

        MenuItem findClientsOfBankItem = new MenuItem();
        findClientsOfBankItem.setCommand(Boolean.TRUE);
        findClientsOfBankItem.setName(Command.CommandType.FIND_CLIENTS_OF_BANK);
        findClientsOfBankItem.setParent(banksItem);

        MenuItem viewAllBanksItem = new MenuItem();
        viewAllBanksItem.setCommand(Boolean.TRUE);
        viewAllBanksItem.setName(Command.CommandType.VIEW_ALL_BANKS);
        viewAllBanksItem.setParent(banksItem);

        MenuItem clientsItem = new MenuItem();
        clientsItem.setName(Command.CommandType.CLIENTS);
        clientsItem.setParent(mainMenuItem);

        MenuItem addClientItem = new MenuItem();
        addClientItem.setCommand(Boolean.TRUE);
        addClientItem.setName(Command.CommandType.ADD_CLIENT);
        addClientItem.setParent(clientsItem);

        MenuItem deleteClientItem = new MenuItem();
        deleteClientItem.setCommand(Boolean.TRUE);
        deleteClientItem.setName(Command.CommandType.DELETE_CLIENT);
        deleteClientItem.setParent(clientsItem);

        MenuItem deleteAllClientsItem = new MenuItem();
        deleteAllClientsItem.setCommand(Boolean.TRUE);
        deleteAllClientsItem.setName(Command.CommandType.DELETE_ALL_CLIENTS_OF_BANK);
        deleteAllClientsItem.setParent(clientsItem);

        MenuItem findBankAccountOfClientItem = new MenuItem();
        findBankAccountOfClientItem.setCommand(Boolean.TRUE);
        findBankAccountOfClientItem.setName(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT);
        findBankAccountOfClientItem.setParent(clientsItem);

        MenuItem operationsItem = new MenuItem();
        operationsItem.setName(Command.CommandType.OPERATIONS);
        operationsItem.setParent(mainMenuItem);

        MenuItem addMoneyTransferItem = new MenuItem();
        addMoneyTransferItem.setCommand(Boolean.TRUE);
        addMoneyTransferItem.setName(Command.CommandType.ADD_MONEY_TRANSFER);
        addMoneyTransferItem.setParent(operationsItem);

        MenuItem addSalaryPaymentItem = new MenuItem();
        addSalaryPaymentItem.setCommand(Boolean.TRUE);
        addSalaryPaymentItem.setName(Command.CommandType.ADD_SALARY_PAYMENT);
        addSalaryPaymentItem.setParent(operationsItem);

        MenuItem addCurrencyItem = new MenuItem();
        addCurrencyItem.setCommand(Boolean.TRUE);
        addCurrencyItem.setName(Command.CommandType.ADD_CURRENCY);
        addCurrencyItem.setParent(banksItem);

        MenuItem findTransactionsOfClientItem = new MenuItem();
        findTransactionsOfClientItem.setCommand(Boolean.TRUE);
        findTransactionsOfClientItem.setName(Command.CommandType.FIND_OPERATIONS_OF_CLIENT);
        findTransactionsOfClientItem.setParent(banksItem);

        MenuItem addBankAccountItem = new MenuItem();
        addBankAccountItem.setCommand(Boolean.TRUE);
        addBankAccountItem.setName(Command.CommandType.ADD_BANK_ACCOUNT);
        addBankAccountItem.setParent(clientsItem);

        mainMenuItem.getChildren().put("1", banksItem);
        mainMenuItem.getChildren().put("2", clientsItem);
        mainMenuItem.getChildren().put("3", operationsItem);

        banksItem.getChildren().put("1", addBankItem);
        banksItem.getChildren().put("2", deleteBankItem);
        banksItem.getChildren().put("3", findClientsOfBankItem);
        banksItem.getChildren().put("4", viewAllBanksItem);
        banksItem.getChildren().put("5", addCurrencyItem);

        clientsItem.getChildren().put("1", addClientItem);
        clientsItem.getChildren().put("2", deleteClientItem);
        clientsItem.getChildren().put("3", deleteAllClientsItem);
        clientsItem.getChildren().put("4", findBankAccountOfClientItem);
        clientsItem.getChildren().put("5", addBankAccountItem);

        operationsItem.getChildren().put("1", addMoneyTransferItem);
        operationsItem.getChildren().put("2", findTransactionsOfClientItem);
        operationsItem.getChildren().put("3", addSalaryPaymentItem);

        menu.setActiveItem(mainMenuItem);
        return menu;
    }

}
