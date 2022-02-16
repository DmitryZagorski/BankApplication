package home.intexsoft.bank_application.userInputParsing;


import home.intexsoft.bank_application.commands.BanksCommands;
import home.intexsoft.bank_application.commands.ClientsCommands;
import home.intexsoft.bank_application.commands.FirstLineCommands;
import home.intexsoft.bank_application.commands.TransactionsCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UserInputParser extends AbstractCommandMapCreator {

    // 1
    // Принимает введенные данные; валидирует; определяет, какой метод commandDescriptor вызвать, учитывая введенные данные.
    // Парсинг введенных данных

    private static final Logger Log = LoggerFactory.getLogger(UserInputParser.class);
    private static int commandsLevel;


    public void createApplicationMenu() {
        createMainMenu();
        createBankMenu();
        createClientMenu();
        createTransactionMenu();
    }

    public void runningOfParser() {
        String[] mainCommands = printCommands(createMainMenu());
        chooseInputDataMethod( enterCommandsNumber(mainCommands));
    }

    public void runBankSubmenu() {

        String[] bankCommands = printCommands(createBankMenu());
        chooseInputDataMethod(enterCommandsNumber(bankCommands));


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
            System.out.println("Enter '9' to return to previous menu");
        }
        System.out.println("Enter '0' to exit");
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
                if (commandNumber == 9) {
                    // returning to previous menu
                    returnToPreviousMenu();
                }
            }
            if (commandNumber == 0) {
                break;
            }
        } while (commandNumber < 0 || commandNumber > commands.length + 1);
        String chosenCommand = commands[commandNumber - 1];
        System.out.println("You chose command " + chosenCommand);
        return chosenCommand;
    }

    private void returnToPreviousMenu() {
        // How to realize this method ?????????????
    }


    public void chooseInputDataMethod(String command) {
        if (commandsLevel == 0) {
            if ("banks".equals(command)) {
                runBankSubmenu();
            }
        } else {
            if ("addBank".equals(command)) {
                enterDataToAddBank(command);
                //                case "viewAllBanks":
//                    bankRepresentation.viewAllBanks();
//                    break;
//                case "findClientsOfBank":
//                    bankRepresentation.viewClientsOfBank(attributes);
//                    break;
//                case "removeBank":
//                    bankRepresentation.removeBank(attributes);
//                    break;
//                case "removeAllBanks":
//                    bankRepresentation.removeAllBanks();
//                    break;
//                case "addClient":
//                    clientRepresentation.addClient(attributes);
//                    break;
//                case "viewAllClients":
//                    clientRepresentation.viewAllClients();
//                    break;
//                case "removeClient":
//                    clientRepresentation.removeClient(attributes);
//                    break;
//                case "removeAllClients":
//                    clientRepresentation.removeAllClients();
//                    break;
//                case "addClientsBankAccount":
//                    clientRepresentation.addClientsBankAccount(attributes);
//                    break;
//                case "findClientsBankAccount":
//                    clientRepresentation.findClientsBankAccount(attributes);
//                    break;
//                case "addTransaction":
//                    transactionRepesentation.addTransaction(attributes);
//                    break;
//                case "viewClientsTransactions":
//                    transactionRepesentation.viewClientsTransactions(attributes);
//                    break;
            }
        }
    }

    public Map<String, Map<String, String>> enterDataToAddBank(String commandName) {
        System.out.println("Enter the name of bank");
        Scanner scanner = new Scanner(System.in);
        String bankName = enterString(scanner).trim();
        System.out.println("Enter the commission for individual");
        Double commissionForIndividual = enterDouble(scanner);
        System.out.println("Enter the commission for entity");
        Double commissionForEntity = enterDouble(scanner);

        CommandDescriptor commandDescriptor = new CommandDescriptor();
        Map<String, String> attributes = commandDescriptor.getAttributes();
        attributes.put("Bank name", bankName);
        attributes.put("Commission For Individual", commissionForIndividual.toString());
        attributes.put("Commission For Entity", commissionForEntity.toString());

        Map<String, Map<String, String>> commandWithAttributes = new HashMap<>();
        commandWithAttributes.put(commandName, attributes);

        return commandWithAttributes;


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
                break;
            }
        } while (number < 0);
        return number;
    }

    public String enterString(Scanner scanner) {
        Log.info("Entering data starts");
        String string = "";
        while (string.trim().isEmpty()){
            System.out.println("Wrong data. Try one more time.");
            string = scanner.nextLine();
        }
        return string;
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