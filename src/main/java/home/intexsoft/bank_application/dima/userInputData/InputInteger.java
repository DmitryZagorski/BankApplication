package home.intexsoft.bank_application.dima.userInputData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InputInteger {

    private static final Logger Log = LoggerFactory.getLogger(InputInteger.class);

    public Integer enterInteger(Scanner scanner) {
        int number;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextInt();
            if (number == 222) {
                System.exit(0);
            }
        } while (number < 0);
        return number;
    }

}
