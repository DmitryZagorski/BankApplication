package home.intexsoft.bank_application.greeting;

import home.intexsoft.bank_application.Commands;

import java.util.Arrays;

public class Greeting {

    public void firstPhrase() {
        System.out.println("Hello. Let's start our application.");
    }

    public void printFirstCommands() {
        System.out.println("Write one of these commands");
        System.out.println(Arrays.toString(Commands.values()));
    }



}
