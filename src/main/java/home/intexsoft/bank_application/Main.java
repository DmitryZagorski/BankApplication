package home.intexsoft.bank_application;

import home.intexsoft.bank_application.greeting.Greeting;

public class Main {

    public static void main(String[] args) {

        while(true){
            new Greeting().chooseCommand();
            UserInputParser inputParser = new UserInputParser();
            String command = inputParser.enterCommand();
            if (command.equalsIgnoreCase("end")){
                break;
            }
            CommandDescriptor commandWithAttributes = inputParser.parse(command);
            FactoryRepresentation factoryRepresentation = new FactoryRepresentation();
            factoryRepresentation.chooseMethod(commandWithAttributes);
        }
    }
}
// Разбить commandRepresentation на классы? Также отдельные методы в классах BankRepresentation & ClientsRepresentation вынести в отдельный класс?
