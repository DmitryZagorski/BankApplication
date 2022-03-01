package home.intexsoft.bank_application.dima.validation.commandValidators;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.command.AddBank;
import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.CommandValidationFactory;
import home.intexsoft.bank_application.dima.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class AddBankValidator extends Validator {

    {
        validationErrors.put(AddBank.Attribute.BANK_NAME, new ArrayList<>());
        validationErrors.put(AddBank.Attribute.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(AddBank.Attribute.COMMISSION_FOR_ENTITY, new ArrayList<>());

        attributeRules.put(AddBank.Attribute.BANK_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.STRING_MAX_LENGTH, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.STRING_MAX_LENGTH, "2")));

        attributeRules.put(AddBank.Attribute.COMMISSION_FOR_INDIVIDUAL, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "10"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBank.Attribute.COMMISSION_FOR_ENTITY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }


    @Override
    public List<String> validate(Command command, CommandAttribute commandAttribute) {
        CommandValidationFactory commandValidationFactory = new CommandValidationFactory();
        for (AttributeDescriptor attributeDescriptor : getAttributeRules().get(commandAttribute)) {
            commandValidationFactory.chooseValidator(command, attributeDescriptor, commandAttribute);
        }
        //   Validator validationCommand = commandValidationFactory.createAttributeValidator();
        //


//                AttributeDescriptor.AttributeType attrType = attributeDescriptor.getType();
//                Validator commandValidator = commandValidationFactory.createCommandValidator(attrType);
//                commandValidator.validate(attributesParameter, attributeDescriptor, commandErrors);


        return null;
    }

}
