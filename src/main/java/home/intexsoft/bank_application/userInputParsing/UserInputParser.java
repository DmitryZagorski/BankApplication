package home.intexsoft.bank_application.userInputParsing;


import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.commandRepresentation.ClientRepresentation;
import home.intexsoft.bank_application.commands.BanksCommands;
import home.intexsoft.bank_application.commands.ClientsCommands;
import home.intexsoft.bank_application.commands.FirstLineCommands;
import home.intexsoft.bank_application.commands.TransactionsCommands;
import home.intexsoft.bank_application.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInputParser extends AbstractCommandMapCreator {

    // 1
    // Принимает введенные данные; валидирует; определяет, какой метод commandDescriptor вызвать, учитывая введенные данные.
    // Парсинг введенных данных

    private static final Logger Log = LoggerFactory.getLogger(UserInputParser.class);
    private static int commandsLevel;
    private static String submenuName;

    public void runningOfParser() {
        commandsLevel = 0;
        submenuName = null;
        String[] mainCommands = printCommands(createMainMenu());
        chooseInputDataMethod(enterCommandsNumber(mainCommands));
    }

    private void runBankSubmenu() {
        String[] bankCommands = printCommands(createBankMenu());
        chooseInputDataMethod(enterCommandsNumber(bankCommands));
    }

    private void runClientSubmenu() {
        String[] clientCommands = printCommands(createClientMenu());
        chooseInputDataMethod(enterCommandsNumber(clientCommands));
    }

    private void runTransactionSubmenu() {
        String[] transactionCommands = printCommands(createTransactionMenu());
        chooseInputDataMethod(enterCommandsNumber(transactionCommands));
    }

    private Map<Integer, String[]> createMainMenu() {
        commandsLevel = 0;
        String[] firstCommands = convertCommandsArrayToStringArray(FirstLineCommands.values());
        Map<Integer, String[]> mainMenuMap = new HashMap<>();
        mainMenuMap.put(commandsLevel, firstCommands);
        return mainMenuMap;
    }

    private Map<Integer, String[]> createBankMenu() {
        commandsLevel = 1;
        return createCommandMap(BanksCommands.values(), commandsLevel);
    }

    private Map<Integer, String[]> createClientMenu() {
        commandsLevel = 1;
        return createCommandMap(ClientsCommands.values(), commandsLevel);
    }

    private Map<Integer, String[]> createTransactionMenu() {
        commandsLevel = 1;
        return createCommandMap(TransactionsCommands.values(), commandsLevel);
    }

    private String[] printCommands(Map<Integer, String[]> commandMap) {
        Log.info("Printing of Commands");
        System.out.println("Enter number of command you need: ");
        String[] mapValues = getMapValues(commandMap);
        for (int i = 0; i < mapValues.length; i++) {
            int commandIndex = i + 1;
            System.out.println(commandIndex + " " + mapValues[i]);
        }
        int indexOfCommand = 0;
        for (Map.Entry<Integer, String[]> item : commandMap.entrySet()) {
            indexOfCommand = item.getKey();
        }
        if (indexOfCommand > 0) {
            System.out.println("Enter '111' to return to previous menu");
        }
        System.out.println("Enter '222' to exit");
        Log.info("Finished of printing firstLineCommands");
        return mapValues;
    }

    private String[] getMapValues(Map<Integer, String[]> commandMap) {
        String[] mapValues = null;
        for (Map.Entry<Integer, String[]> entry : commandMap.entrySet()) {
            mapValues = entry.getValue();
        }
        return mapValues;
    }

    private String enterCommandsNumber(String[] commands) {
        Log.info("Entering of commands number");
        Scanner scanner = new Scanner(System.in);
        int commandNumber;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            commandNumber = scanner.nextInt();
            if (commandsLevel > 0) {
                if (commandNumber == 111) {
                    returnToPreviousMenu();
                }
            }
            if (commandNumber == 222) {
                System.exit(0);
            }
        } while (commandNumber < 0 || commandNumber > commands.length + 1);
        String chosenCommand = commands[commandNumber - 1];
        System.out.println("You chose command " + chosenCommand);
        return chosenCommand;
    }

    private void returnToPreviousMenu() {
        if (commandsLevel == 0) {
            new UserInputParser().runningOfParser();
        } else if (commandsLevel == 1) {
            new UserInputParser().runningOfParser();
        } else if (commandsLevel == 2 & "banks".equalsIgnoreCase(submenuName)) {
            new UserInputParser().runBankSubmenu();
        } else if (commandsLevel == 2 & "clients".equalsIgnoreCase(submenuName)) {
            new UserInputParser().runClientSubmenu();
        } else if (commandsLevel == 2 & "transactions".equalsIgnoreCase(submenuName)) {
            new UserInputParser().runTransactionSubmenu();
        }
    }


    public void chooseInputDataMethod(String command) {
        BankRepresentation bankRepresentation = new BankRepresentation();
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        if (commandsLevel == 0) {
            if ("banks".equals(command)) {
                submenuName = command;
                runBankSubmenu();
            }
            if ("clients".equals(command)) {
                submenuName = command;
                runClientSubmenu();
            }
            if ("transaction".equals(command)) {
                submenuName = command;
                runTransactionSubmenu();
            }
        }
        if (commandsLevel == 1) {
            switch (command) {
                case "addBank":
                    commandsLevel = 2;
                    enterDataToAddBank(command);
                case "viewAllBanks":
                    commandsLevel = 2;
                    bankRepresentation.viewAllBanks();
                    break;
                case "findClientsOfBank":
                    commandsLevel = 2;
                    enterDataToFindClientsOfBank(command);
                    break;
                case "removeBank":
                    commandsLevel = 2;
                    enterDataToRemoveBank(command);
                    break;
                case "removeAllBanks":
                    commandsLevel = 2;
                    bankRepresentation.removeAllBanks();
                    break;
                case "addClient":
                    commandsLevel = 2;
                    enterDataToAddClient(command);
                    break;
                case "viewAllClients":
                    commandsLevel = 2;
                    clientRepresentation.viewAllClients();
                    break;
                case "removeClient":
                    commandsLevel = 2;
                    enterDataToRemoveClient(command);
                    break;
                case "removeAllClients":
                    commandsLevel = 2;
                    clientRepresentation.removeAllClients();
                    break;
                case "addClientsBankAccount":
                    commandsLevel = 2;
                    enterDataToAddBankAccount(command);
                    break;
                case "findClientsBankAccount":
                    commandsLevel = 2;
                    enterDataToFindClientsAccount(command);
                    break;
                case "addTransaction":
                    commandsLevel = 2;
                    enterDataToAddTransaction(command);
                    break;
                case "viewClientsTransactions":
                    commandsLevel = 2;
                    enterDataToFindClientsTransactions(command);
                    break;
            }
        }

    }


    public Map<String, Map<String, String>> enterDataToAddBank(String commandName) {
        Scanner scanner = new Scanner(System.in);
        String bankName = null;
        Double commissionForIndividual = 0.0;
        Double commissionForEntity = 0.0;

        System.out.println("Enter the name of bank");
        bankName = enterString(scanner).trim();
        System.out.println("Enter the commission for individual");
        commissionForIndividual = enterDouble(scanner);
        System.out.println("Enter the commission for entity");
        commissionForEntity = enterDouble(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("bankName", bankName);
        attributes.put("commissionForIndividual", commissionForIndividual.toString());
        attributes.put("commissionForEntity", commissionForEntity.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToFindClientsOfBank(String commandName) {
        System.out.println("Enter the bank id from list: ");
        viewAllBanks();
        Scanner scanner = new Scanner(System.in);
        Integer bankId = enterInteger(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("findClientsOfBank", bankId.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToRemoveBank(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the bank id from list: ");
        viewAllBanks();
        Integer bankId = enterInteger(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("removeBank", bankId.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToAddClient(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of client");
        String clientName = enterString(scanner).trim();
        System.out.println("Enter the surname of client");
        String clientSurname = enterString(scanner).trim();
        System.out.println("Enter the number of clients status");
        viewClientStatuses();
        Integer clientStatusId = enterInteger(scanner);
        System.out.println("Enter the number of currency");
        viewAllCurrency();
        Integer currencyId = enterInteger(scanner);
        System.out.println("Enter the number of bank");
        viewAllBanks();
        Integer bankId = enterInteger(scanner);
        System.out.println("Enter amount of money");
        Double amountOfMoney = enterDouble(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("clientName", clientName);
        attributes.put("clientSurname", clientSurname);
        attributes.put("clientStatusId", clientStatusId.toString());
        attributes.put("currencyId", currencyId.toString());
        attributes.put("bankId", bankId.toString());
        attributes.put("amountOfMoney", amountOfMoney.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToRemoveClient(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the client id from list: ");
        viewAllClients();
        Integer clientId = enterInteger(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("removeClient", clientId.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToAddBankAccount(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of client");
        viewAllClients();
        String clientName = enterString(scanner).trim();
        System.out.println("Enter the number of bank");
        viewAllBanks();
        Integer bankId = enterInteger(scanner);
        System.out.println("Enter the number of currency");
        viewAllCurrency();
        Integer currencyId = enterInteger(scanner);
        System.out.println("Enter amount of money");
        Double amountOfMoney = enterDouble(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("clientName", clientName);
        attributes.put("bankId", bankId.toString());
        attributes.put("currencyId", currencyId.toString());
        attributes.put("amountOfMoney", amountOfMoney.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToFindClientsAccount(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the client id from list: ");
        viewAllClients();
        Integer clientId = enterInteger(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("findClientsAccount", clientId.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToAddTransaction(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of client from list: ");
        viewAllClients();
        Integer clientId = enterInteger(scanner);
        System.out.println("Enter bank account of sender from list: ");
        viewClientsBankAccount(clientId);
        Integer senderBankAccount = enterInteger(scanner);
        System.out.println("Enter bank account of recipient from list: ");
        viewAvailableRecipientAccounts(clientId);
        Integer recipientBankAccount = enterInteger(scanner);
        System.out.println("Enter amount of money");
        Double amountOfMoney = enterDouble(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("clientId", clientId.toString());
        attributes.put("senderBankAccount", senderBankAccount.toString());
        attributes.put("recipientBankAccount", recipientBankAccount.toString());
        attributes.put("amountOfMoney", amountOfMoney.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }

    public Map<String, Map<String, String>> enterDataToFindClientsTransactions(String commandName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of client from list: ");
        viewAllClients();
        Integer clientId = enterInteger(scanner);
        System.out.println("Enter date of transactions (mm-dd-yyyy)");
        String date = enterString(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("clientId", clientId.toString());
        attributes.put("dateBefore", date);

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;
    }


    private void viewAvailableRecipientAccounts(Integer clientId) {
        List<BankAccount> allBankAccounts = new BankRepresentation().findAvailableBankAccountsForRecipient(clientId);
        for (BankAccount allBankAccount : allBankAccounts) {
            System.out.println(allBankAccount.getId());
        }
    }

    private void viewClientsBankAccount(Integer clientId) {
        String[] id = new String[]{clientId.toString()};
        List<BankAccount> clientsBankAccount = new ClientRepresentation().findClientsBankAccount(id);
        for (BankAccount bankAccount : clientsBankAccount) {
            System.out.println(bankAccount.getId());
        }
    }

    private void viewAllBanks() {
        List<Bank> allBanks = new BankRepresentation().findAllBanks();
        Bank bank;
        for (Bank allBank : allBanks) {
            bank = allBank;
            System.out.println(bank.getId() + " " + bank.getName());
        }
    }

    private void viewAllClients() {
        List<Client> allClients = new ClientRepresentation().findAllClients();
        Client client;
        for (int i = 0; i < allClients.size(); i++) {
            client = allClients.get(i);
            System.out.println(client.getId() + " " + client.getName());
        }
    }

    public void viewClientStatuses() {
        List<ClientStatus> allStatuses = new ClientRepresentation().findAllStatuses();
        ClientStatus clientStatus;
        for (int i = 0; i < allStatuses.size(); i++) {
            clientStatus = allStatuses.get(i);
            System.out.println(clientStatus.getId() + " " + clientStatus.getName());
        }
    }

    public void viewAllCurrency() {
        List<Currency> allCurrency = new ClientRepresentation().findAllCurrency();
        Currency currency;
        for (int i = 0; i < allCurrency.size(); i++) {
            currency = allCurrency.get(i);
            System.out.println(currency.getId() + " " + currency.getName());
        }
    }

    public Double enterDouble(Scanner scanner) {
        double number;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextDouble();
            if (number == 111) {
                returnToPreviousMenu();
            }
            if (number == 222) {
                System.exit(0);
            }
        } while (number < 0);
        return number;
    }

    public Integer enterInteger(Scanner scanner) {
        int number;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextInt();
            if (number == 111) {
                returnToPreviousMenu();
            }
            if (number == 222) {
                System.exit(0);
            }
        } while (number < 0);
        return number;
    }

    public String enterString(Scanner scanner) {
        Log.info("Entering data starts");
        String string = "";
        while (string.trim().isEmpty()) {
            string = scanner.nextLine();
        }
        if ("111".equalsIgnoreCase(string)){
            returnToPreviousMenu();
        }
        if ("222".equalsIgnoreCase(string)){
            System.exit(0);
        }
        return string.trim();
    }

//    public String enterString(Scanner scanner) {
//        Log.info("Entering data starts");
//        String string;
//        do {
//            while (!scanner.hasNextLine()) {
//                System.out.println("That's not a string!");
//                scanner.next();
//            }
//            string = scanner.nextLine();
//            if ("q".equals(string)) {
//                break;
//            }
//        } while (scanner.nextLine() == null || scanner.nextLine().isEmpty());
//        Log.info("Entering data finished");
//        return string;
//    }

//    private FirstLineCommands[] printFirstLineCommands() {
//        Log.info("Printing of firstLineCommands");
//        System.out.println("Enter number of command you need: ");
//        FirstLineCommands[] firstLineCommands = FirstLineCommands.values();
//        for (int i = 0; i < firstLineCommands.length; i++) {
//            int commandIndex = i + 1;
//            System.out.println(commandIndex + " " + firstLineCommands[i]);
//        }
//        System.out.println("Enter '9' to return to previous menu");
//        System.out.println("Enter '0' to exit");
//        Log.info("Finished of printing firstLineCommands");
//        return firstLineCommands;
//    }

//    private Integer enterFirstLineCommandsNumber() {
//        Log.info("Entering of firstLineCommands number");
//        Scanner scanner = new Scanner(System.in);
//        int commandNumber;
//        do {
//            while (!scanner.hasNextInt()) {
//                System.out.println("That's not a number!");
//                scanner.next();
//            }
//            commandNumber = scanner.nextInt();
//            if (commandNumber == 9) {
//                // returning to previous menu
//            }
//            if (commandNumber == 0) {
//                break;
//            }
//        } while (commandNumber < 0 || commandNumber > printFirstLineCommands().length + 1);
//        System.out.println("Thank you! You entered command number " + commandNumber);
//        return commandNumber;
//    }


//    public String enterCommand() {
//        Log.info("Method EnterCommand started");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String string = null;
//        try {
//            string = bufferedReader.readLine();
//        } catch (IOException e) {
//            System.out.println("Try one more time");
//            enterCommand();
//        }
//        Log.info("Method EnterCommand finished");
//        return string;
//    }
//
//    public CommandDescriptor parse(String string) {
//        Log.info("Parsing of entered command started");
//        String[] enteredString = string.split(" ");
//        for (String s : enteredString) {
//            s.trim();
//        }
//        String command = enteredString[0];
//        String[] attributes = new String[enteredString.length - 1];
//        for (int i = 1; i < enteredString.length; i++) {
//            attributes[i - 1] = enteredString[i];
//        }
//        Log.info("Parsing of command finished");
//        return new CommandDescriptor(command, attributes);
//    }


//    private String[] changeBankCommandsArrayToStringArray(BanksCommands[] banksCommandsArray) {
//        String[] stringArray = new String[banksCommandsArray.length];
//        for (int i = 0; i < banksCommandsArray.length; i++) {
//            stringArray[i] = banksCommandsArray[i].toString();
//        }
//        return stringArray;
//    }
//
//    private String[] changeClientCommandsArrayToStringArray(ClientsCommands[] clientsCommandsArray) {
//        String[] stringArray = new String[clientsCommandsArray.length];
//        for (int i = 0; i < clientsCommandsArray.length; i++) {
//            stringArray[i] = clientsCommandsArray[i].toString();
//        }
//        return stringArray;
//    }
//
//    private String[] changeTransactionCommandsArrayToStringArray(TransactionsCommands[] transactionsCommandsArray) {
//        String[] stringArray = new String[transactionsCommandsArray.length];
//        for (int i = 0; i < transactionsCommandsArray.length; i++) {
//            stringArray[i] = transactionsCommandsArray[i].toString();
//        }
//        return stringArray;
//    }
//
//
//    public Map<String, String> createBankCommandsMap() {
//        String[] bankCommands = changeBankCommandsArrayToStringArray(BanksCommands.values());
//        Map<String, String> bankCommandsMap = new HashMap<>();
//        for (String bankCommand : bankCommands) {
//            bankCommandsMap.put("1", bankCommand);
//        }
//        return bankCommandsMap;
//    }
//
//    public Map<String, String> createClientCommandsMap() {
//        String[] clientCommands = changeClientCommandsArrayToStringArray(ClientsCommands.values());
//        Map<String, String> clientCommandsMap = new HashMap<>();
//        for (String clientCommand : clientCommands) {
//            clientCommandsMap.put("2", clientCommand);
//        }
//        return clientCommandsMap;
//    }
//
//    public Map<String, String> createTransactionCommandsMap() {
//        String[] transactionCommands = changeTransactionCommandsArrayToStringArray(TransactionsCommands.values());
//        Map<String, String> transactionCommandsMap = new HashMap<>();
//        for (String transactionCommand : transactionCommands) {
//            transactionCommandsMap.put("3", transactionCommand);
//        }
//        return transactionCommandsMap;
//    }


}

// заменить на Scanner
// Использовать стрингбилдер
// add Menu with submenus list
// submenu consists of list of commands

// Map (index, command)
//