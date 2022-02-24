package home.intexsoft.bank_application.dima.userInputData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InputString {

    private static final Logger Log = LoggerFactory.getLogger(InputString.class);

    public String enterString(Scanner scanner) {
        Log.info("Entering data starts");
        String string = "";
        while (string.trim().isEmpty()) {
            string = scanner.nextLine();
        }
        if ("quit".equalsIgnoreCase(string)) {
            System.exit(0);
        }
        return string.trim();
    }

}
