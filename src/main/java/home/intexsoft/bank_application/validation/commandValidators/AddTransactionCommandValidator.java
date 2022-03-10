package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.validation.Validator;

import java.util.Map;

public class AddTransactionCommandValidator extends Validator {

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }


}
