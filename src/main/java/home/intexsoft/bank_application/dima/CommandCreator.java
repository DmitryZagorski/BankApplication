package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.CommandValidatorFactory;
import home.intexsoft.bank_application.dima.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandCreator {

    private static final Logger log = LoggerFactory.getLogger(CommandCreator.class);
    private CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
    private CommandFactory commandFactory = new CommandFactory();
    private CommandLineParser commandLineParser = new CommandLineParser();

    public Command createCommand(MenuItem activeItem) throws IllegalAccessException, InstantiationException {
        log.info("Creation executable command " + activeItem.getName().getCommandName());
        System.out.println("Chosen command is " + activeItem.getName().getCommandName());
        Command command = commandFactory.getCommandClasses().get(activeItem.getName()).newInstance();
        addCommandsArguments(command);
        return command;
    }

    public void addCommandsArguments(Command command) throws InstantiationException, IllegalAccessException {
        log.info("Adding arguments to command " + command.getName());
        Validator commandValidator = commandValidatorFactory.createCommandValidator(command);
        for (Map.Entry<CommandAttribute, String> commandAttribute : command.getAttributes().entrySet()) {  // !!!!!!!!! naming
            List<String> errors = commandValidator.getValidationErrors().get(commandAttribute.getKey());
            do {
                errors.clear();
                String attributeValue = commandLineParser.enterStringByAttribute(commandAttribute.getKey().getAttributeName());
                commandAttribute.setValue(attributeValue);
                commandValidator.validate(command, commandAttribute.getKey()); // --------command
                filterListFromRecurringStrings(errors);
                for (String s : errors) {
                    System.out.println(s);
                }
            }
            while (!commandValidator.getValidationErrors().get(commandAttribute.getKey()).isEmpty());
        }
    }

    private void filterListFromRecurringStrings(List<String> errors) {
        log.info("Filtering of recurring errors");
        Set<String> set = new HashSet<>(errors);
        errors.clear();
        errors.addAll(set);
    }

}
