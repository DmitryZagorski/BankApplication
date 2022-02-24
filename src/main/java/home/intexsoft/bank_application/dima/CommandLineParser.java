package home.intexsoft.bank_application.dima;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class CommandLineParser {

    private static final Logger Log = LoggerFactory.getLogger(CommandLineParser.class);

    public void addCommandsArguments(Command command) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> attributes = command.getAttributes();
        for (Map.Entry<String, String> stringStringEntry : attributes.entrySet()) {
            System.out.println("Enter " + stringStringEntry.getKey());
            stringStringEntry.setValue(enterString(scanner));
        }
    }

    public String enterString(Scanner scanner) {
        Log.info("Entering data starts");
        String string = "";
        do {
            string = scanner.nextLine();
        } while (string == null ||string.trim().isEmpty());
        Log.info("Entering data finished");
        return string;
    }

}