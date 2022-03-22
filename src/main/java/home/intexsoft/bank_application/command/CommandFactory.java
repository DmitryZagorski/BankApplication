package home.intexsoft.bank_application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class CommandFactory {

    private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);
    private Map<Command.CommandType, Command> commands;

    @Autowired
    public CommandFactory(Set<Command> commands) {
        this.commands = commands.stream().collect(Collectors.toMap(Command::getName, command -> command));
    }

    Command createCommandByCommandName(Command.CommandType commandName) {
        log.debug("Creating command by command name " + commandName + " started");
        Command command = commands.get(commandName);
        log.debug("Creating command by command name " + commandName + " finished");
        return command;
    }
}