package home.intexsoft.bank_application;

import home.intexsoft.bank_application.userInputParsing.UserInputParser;

public class Main {

    public static void main(String[] args) {

        UserInputParser userInputParser = new UserInputParser();
//        while(true){
//            new Greeting().chooseCommand();
//            // incupsulation of command of parser!!!!!
//            String command = userInputParser.enterCommand();
//            if ("end".equalsIgnoreCase(command)){
//                break;
//            }
//            CommandDescriptor commandWithAttributes = userInputParser.parse(command);
//            FactoryRepresentation factoryRepresentation = new FactoryRepresentation();
//            factoryRepresentation.chooseMethod(commandWithAttributes);
//        }

        userInputParser.runningOfParser();

    }
}
