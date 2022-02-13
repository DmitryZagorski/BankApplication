package home.intexsoft.bank_application;

import home.intexsoft.bank_application.commandRepresentation.ClientRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputParser {

    // 1
    // Принимает введенные данные; валидирует; определяет, какой метод commandDescriptor вызвать, учитывая введенные данные.
    // Парсинг введенных данных

    private static final Logger Log = LoggerFactory.getLogger(UserInputParser.class);

    public String enterCommand() {
        Log.info("Method EnterCommand started");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = null;
        try {
            string = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Try one more time");
            enterCommand();
        }
        Log.info("Method EnterCommand finished");
        return string;
    }

    public CommandDescriptor parse(String string) {
        Log.info("Parsing of entered command started");
        String[] enteredString = string.split(" ");
        for (String s : enteredString) {
            s.trim();
        }
        String command = enteredString[0];
        String[] attributes = new String[enteredString.length-1];
        for (int i = 1; i < enteredString.length; i++) {
            attributes[i - 1] = enteredString[i];
        }
        Log.info("Parsing of command finished");
        return new CommandDescriptor(command, attributes);
    }
}