package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.MenuItem;
import home.intexsoft.bank_application.validation.CommandValidatorFactory;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommandCreator {

    private static final Logger log = LoggerFactory.getLogger(CommandCreator.class);
    private CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
    private CommandFactory commandFactory = new CommandFactory();
    private CommandLineParser commandLineParser = new CommandLineParser();

    public Command createCommand(MenuItem activeItem) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        log.info("Creation executable command " + activeItem.getName().getCommandName());
        System.out.println("Chosen command is " + activeItem.getName().getCommandName());
        Command command = commandFactory.createCommandByCommandName(activeItem.getName());
        addCommandsArguments(command);
        return command;
    }

    private void addCommandsArguments(Command command) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        log.info("Adding arguments to command " + command.getName());
        Validator commandValidator = commandValidatorFactory.createCommandValidator(command);
        command.getAttributes().entrySet().forEach(commandAttributePair -> {
            List<String> errors = commandValidator.getValidationErrors().get(commandAttributePair.getKey());
            do {
                errors.clear();
                String attributeValue = commandLineParser.enterStringByAttribute(commandAttributePair.getKey().getAttributeName());
                commandAttributePair.setValue(attributeValue);
                commandValidator.validate(commandAttributePair);
                commandValidator.showValidationErrors(errors);
            }
            while (!commandValidator.getValidationErrors().get(commandAttributePair.getKey()).isEmpty());
        });
        log.info("Adding arguments to command " + command.getName() + " finished");
    }
}
