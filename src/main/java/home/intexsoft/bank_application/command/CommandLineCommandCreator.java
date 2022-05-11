package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.CommandDto;
import home.intexsoft.bank_application.validation.CommandValidatorFactory;
import home.intexsoft.bank_application.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("commandLine")
public class CommandLineCommandCreator implements CommandCreator {

    private static final Logger log = LoggerFactory.getLogger(CommandLineCommandCreator.class);
    private final CommandValidatorFactory commandValidatorFactory;
    private final CommandFactory commandFactory;
    private final CommandLineParser commandLineParser;

    @Override
    public Command createCommand(Command.CommandType commandName) {
        log.debug("Creation of executable command " + commandName.getCommandName() + " started");
        System.out.println("Chosen command is " + commandName.getCommandName());
        Command command = commandFactory.createCommandByCommandName(commandName);
        addCommandsArguments(command);
        log.debug("Creation of executable command " + commandName.getCommandName() + " finished");
        return command;
    }

    @Override
    public Command createCommand(Command.CommandType commandName, CommandDto commandDto) {
        throw new IllegalArgumentException("Unsupported type of arguments");
    }

    private void addCommandsArguments(Command command) {
        log.debug("Adding arguments to command " + command.getName());
        Validator commandValidator = commandValidatorFactory.createCommandValidator(command.getName());
        command.getAttributes().entrySet().forEach(commandAttributePair -> {
            List<String> errors = commandValidator.getValidationErrors().get(commandAttributePair.getKey());
            do {
                errors.clear();
                String attributeValue =
                        commandLineParser.enterStringByAttribute(commandAttributePair.getKey().getAttributeName());
                commandAttributePair.setValue(attributeValue);
                commandValidator.validateAttribute(commandAttributePair);
                commandValidator.showValidationErrors(errors);
            }
            while (!commandValidator.getValidationErrors().get(commandAttributePair.getKey()).isEmpty());
        });
        log.debug("Adding arguments to command " + command.getName() + " finished");
    }
}