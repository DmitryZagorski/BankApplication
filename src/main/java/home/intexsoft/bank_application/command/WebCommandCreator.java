package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.CommandDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//@Profile("web")
public class WebCommandCreator implements CommandCreator {

    private static final Logger log = LoggerFactory.getLogger(WebCommandCreator.class);
    private final CommandFactory commandFactory;

    public Command createCommand(Command.CommandType commandName, CommandDto commandDto) {
        log.debug("Method createCommand started");
        Command command = commandFactory.createCommandByCommandName(commandName);
        command.getAttributes().entrySet().
                forEach(attribute -> attribute.setValue(commandDto.getByAttributeName(attribute.getKey())));
        log.debug("Method createCommand finished");
        return command;
    }

    @Override
    public Command createCommand(Command.CommandType commandName) {
        log.debug("Method createCommand started");
        Command command = commandFactory.createCommandByCommandName(commandName);
        log.debug("Method createCommand finished");
        return command;
//        throw new IllegalArgumentException("Unsupported type of arguments");
    }
}