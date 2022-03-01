package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.commandValidators.AddBankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandValidationFactory {

    private static final Logger Log = LoggerFactory.getLogger(CommandValidationFactory.class);

    private Map<String, Class<? extends Validator>> factory = new HashMap<>();

    public Map<String, Class<? extends Validator>> getFactory() {
        return factory;
    }

    public void setFactory(Map<String, Class<? extends Validator>> factory) {
        this.factory = factory;
    }

    {
        factory.put(Command.Commands.ADD_BANK.getCommandName(), AddBankValidator.class);
    }

    public Validator createValidationCommand(Command command) {
        Log.info("Method of creationValidationCommand started");
        Validator validator = null;
        for (Map.Entry<String, Class<? extends Validator>> commandsClassEntry : getFactory().entrySet()) {
            if (command.getName().equals(commandsClassEntry.getKey())) {
                try {
                    validator = commandsClassEntry.getValue().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Can't create object of class " + e);
                }
            }
        }
        return validator;
    }

    public void chooseValidator(Command command, AttributeDescriptor attributeDescriptor, CommandAttribute commandAttribute) {
        Validator validator = new Validator();
        String checkingString = command.getAttributes().get(commandAttribute);
        switch (attributeDescriptor.getKind()) {
            case TYPE:
                validator.validateType(attributeDescriptor.getValue(), commandAttribute, checkingString);
            case MAX_VALUE:
                validator.validateMaxValue(attributeDescriptor.getValue(), commandAttribute, checkingString);
            case MIN_VALUE:
                validator.validateMinValue(attributeDescriptor.getValue(), commandAttribute, checkingString);
            case STRING_MAX_LENGTH:
                validator.validateStringMaxLength(attributeDescriptor.getValue(), commandAttribute, checkingString);
            case STRING_MIN_LENGTH:
                validator.validateMinValue(attributeDescriptor.getValue(), commandAttribute, checkingString);
        }
    }
}
