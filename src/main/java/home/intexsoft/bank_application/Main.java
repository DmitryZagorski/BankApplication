package home.intexsoft.bank_application;

import home.intexsoft.bank_application.greeting.Greeting;

public class Main {

    public static void main(String[] args) {

        new Greeting().chooseCommand();

        UserInputParser inputParser = new UserInputParser();
        String command = inputParser.enterCommand();
        CommandDescriptor commandWithAttributes = inputParser.parse(command);
        FactoryRepresentation factoryRepresentation = new FactoryRepresentation();
        factoryRepresentation.chooseMethod(commandWithAttributes);


    }
}
// Нужно ли делать отдельный пакет для BankRepresentation, ClientRepresentation чтобы соманды разбить по отдельным классам?
