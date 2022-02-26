package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.CommandAttribute;
import home.intexsoft.bank_application.dima.CommandAttributeName;

import java.util.List;

public abstract class BankValidator extends Validator {

    @Override
    public abstract boolean validate(Command command, CommandAttribute commandAttribute, List<String> errors);
}
