package home.intexsoft.bank_application.dima;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CommandLineParser {

    private static final Logger Log = LoggerFactory.getLogger(CommandLineParser.class);

    public String enterString() {
        Log.info("Entering data starts");
        Scanner scanner = new Scanner(System.in);
        String string = "";
        do {
            string = scanner.nextLine();
        } while (string == null ||string.trim().isEmpty());
        Log.info("Entering data finished");
        return string;
    }
}