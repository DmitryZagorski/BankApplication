package home.intexsoft.bank_application.dima.userInputData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InputDouble {

    private static final Logger Log = LoggerFactory.getLogger(InputDouble.class);

    public Double enterDouble(Scanner scanner) {
        double number;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextDouble();
            if (number == 222) {
                System.exit(0);
            }
        } while (number < 0);
        return number;
    }

}
