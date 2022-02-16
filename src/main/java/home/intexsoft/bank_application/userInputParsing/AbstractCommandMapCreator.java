package home.intexsoft.bank_application.userInputParsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommandMapCreator {

    private static final Logger Log = LoggerFactory.getLogger(AbstractCommandMapCreator.class);

    public Map<Integer, String[]> createCommandMap(Enum[] enums, int numberOfCommandLine) {
        return createMapOfCommands(numberOfCommandLine, convertCommandsArrayToStringArray(enums));
    }

    public String[] convertCommandsArrayToStringArray(Enum[] enums) {
        String[] stringArray = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            stringArray[i] = enums[i].toString();
        }
        return stringArray;
    }

    private Map<Integer, String[]> createMapOfCommands(int numberOfCommandLine, String[] stringArray) {
        Map<Integer, String[]> commandsMap = new HashMap<>();
//        for (String arrayElement : stringArray) {
//            commandsMap.put(numberOfCommandLine, arrayElement);
//        }
        commandsMap.put(numberOfCommandLine, stringArray);
        return commandsMap;
    }


//    private Map<String, Map<Integer, String>> createMapOfCommandsWithMap(int numberOfCommandLine, String[] stringArray) {
//        Map<String, String> commandsMap = new HashMap<>();
//        String commandLineNumber = "" + numberOfCommandLine;
//        for (String arrayElement : stringArray) {
//            commandsMap.put(commandLineNumber, arrayElement);
//        }
//        return commandsMap;
//    }


}
