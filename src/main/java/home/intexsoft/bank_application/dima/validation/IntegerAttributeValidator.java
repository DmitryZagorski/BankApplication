package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.Command;

public class IntegerAttributeValidator extends NumberAttributeValidator{


    @Override
    public boolean validate(Command command) {
        return false;
    }
}
