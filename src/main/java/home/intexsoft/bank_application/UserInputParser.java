package home.intexsoft.bank_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UserInputParser {

    // 1
    // Принимает введенные данные; валидирует; определяет, какой метод commandDescriptor вызвать, учитывая введенные данные.
    // Парсинг введенных данных


    public void printCommands() {
        System.out.println(Arrays.toString(FirstCommands.values()));
    }

    public String enterCommand() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = null;
        try {
            string = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Try one more time");
            enterCommand();
        }
        return string;
    }

    public CommandDescriptor parse(String string) {
        String[] enteredString = string.split(" ");
        for (String s : enteredString) {
            s.trim();
        }
        String command = enteredString[1];
        String[] params = new String[enteredString.length - 1];
        for (int i = 1; i < enteredString.length; i++) {
            params[i - 1] = enteredString[i];
        }
        return new CommandDescriptor(command, params);
    }
}