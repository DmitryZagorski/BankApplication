package home.intexsoft.bank_application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

class CommandFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);
    private Map<Command.CommandType, Class<? extends Command>> commands = new HashMap<>();

    {
        commands.put(Command.CommandType.ADD_BANK, AddBankCommand.class);
    }

    Command createCommandByCommandName(Command.CommandType commandName) {
        log.debug("Creating command by commandName " + commandName + " started");
        Command command = null;
        try {
            Class<? extends Command> commandsClass = commands.get(commandName);
            Constructor<?>[] constructors = commandsClass.getConstructors();
            command = (Command) constructors[0].newInstance();
        } catch (Exception e) {
            log.error("Error during creating command", e);
        }
        log.debug("Creating command by commandName " + commandName + " finished");
        return command;
    }
}