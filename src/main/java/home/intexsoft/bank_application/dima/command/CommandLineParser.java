package home.intexsoft.bank_application.dima.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CommandLineParser {

    private static final Logger log = LoggerFactory.getLogger(CommandLineParser.class);
    private Scanner scanner = new Scanner(System.in);

    public String enterString() {
        log.info("Entering data starts");
        String string;
        do {
            string = scanner.nextLine();
        } while (string == null ||string.trim().isEmpty());
        log.info("Entering data finished");
        return string;
    }

    public String enterStringByAttribute(String commandAttribute) {
        log.info("Entering data starts");
        String string;
        do {
            System.out.println("Enter " + commandAttribute);
            string = scanner.nextLine();
        } while (string == null ||string.trim().isEmpty());
        log.info("Entering data finished");
        return string;
    }
}