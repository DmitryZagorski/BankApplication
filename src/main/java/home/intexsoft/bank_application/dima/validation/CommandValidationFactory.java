package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;

public class CommandValidationFactory {

    public Validator createCommandValidator(AttributeType attributeType) {
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
