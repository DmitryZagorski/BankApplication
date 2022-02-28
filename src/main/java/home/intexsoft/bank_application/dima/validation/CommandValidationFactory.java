package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.validation.commandValidators.AddBankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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



    public Validator createAttributeValidator(AttributeDescriptor.AttributeType attributeType) {
        Validator validator = null;
        switch (attributeType) {
            case STRING:
                validator = new StringAttributeValidator();
            case INTEGER:
                validator = new IntegerAttributeValidator();
            case DOUBLE:
                validator = new DoubleAttributeValidator();
        }
        return validator;
    }
}
