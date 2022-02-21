package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.userInputParsing.CommandDescriptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputCommandParser {

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
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


}
