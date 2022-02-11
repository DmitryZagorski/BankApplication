package home.intexsoft.bank_application;

public class Main {

    public static void main(String[] args) {
        UserInputParser userInputParser = new UserInputParser();
        userInputParser.printCommands();
        CommandDescriptor commandDescriptor = userInputParser.parse(userInputParser.enterCommand());


    }
}
