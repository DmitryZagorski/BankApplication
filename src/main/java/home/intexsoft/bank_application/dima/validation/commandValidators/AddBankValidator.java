package home.intexsoft.bank_application.dima.validation.commandValidators;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.command.AddBank;
import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class AddBankValidator extends Validator {

    {
        validationErrors.put(AddBank.Attribute.BANK_NAME, new ArrayList<>());
        validationErrors.put(AddBank.Attribute.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(AddBank.Attribute.COMMISSION_FOR_ENTITY, new ArrayList<>());

        attributeRules.put(AddBank.Attribute.BANK_NAME, new ArrayList<>());
//        attributeRules.put(AddBank.Attribute.COMMISSION_FOR_INDIVIDUAL, new DoubleAttributeDescriptor(AttributeDescriptor.AttributeType.DOUBLE, true, true));
//        attributeRules.put(AddBank.Attribute.COMMISSION_FOR_ENTITY, new DoubleAttributeDescriptor(AttributeDescriptor.AttributeType.DOUBLE, true, true));
    }


    @Override
    public List<String> validate(Command command, List<String> errors) {
        CommandValidationFactory commandValidationFactory = new CommandValidationFactory();
        Validator validationCommand = commandValidationFactory.createAttributeValidator();
        //





//                AttributeDescriptor.AttributeType attrType = attributeDescriptor.getType();
//                Validator commandValidator = commandValidationFactory.createCommandValidator(attrType);
//                commandValidator.validate(attributesParameter, attributeDescriptor, commandErrors);


        return errors;
    }
}
