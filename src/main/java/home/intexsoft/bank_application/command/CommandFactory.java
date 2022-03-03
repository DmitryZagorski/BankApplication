package home.intexsoft.bank_application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);
    private Map<Command.CommandType, Class<? extends Command>> validators = new HashMap<>();

    Map<Command.CommandType, Class<? extends Command>> getValidators() {
        return validators;
    }

    {
        validators.put(Command.CommandType.ADD_BANK, AddBankCommand.class);
    }

    Command createCommandByCommandName(Command.CommandType commandName) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        log.info("Creating command by commandName " + commandName + " started");
        Command command = null;
        try{
            Class<? extends Command> commandsClass = validators.get(commandName);
            Constructor<?>[] constructors = commandsClass.getConstructors();
            command = (Command) constructors[0].newInstance();
        } catch (NullPointerException e) {
            log.error("Error during creating command");
        }
        log.info("Creating command by commandName " + commandName + " finished");
        return command;
    }
}

