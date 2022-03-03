package home.intexsoft.bank_application.dima.validation.commandValidators;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.command.AddBankCommand;
import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import home.intexsoft.bank_application.dima.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddBankValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddBankValidator.class);

    {
        validationErrors.put(AddBankCommand.Attribute.BANK_NAME, new ArrayList<>());
        validationErrors.put(AddBankCommand.Attribute.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(AddBankCommand.Attribute.COMMISSION_FOR_ENTITY, new ArrayList<>());

        attributeRules.put(AddBankCommand.Attribute.BANK_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddBankCommand.Attribute.COMMISSION_FOR_INDIVIDUAL, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "10"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankCommand.Attribute.COMMISSION_FOR_ENTITY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }

    @Override
    public boolean validate(Command command, CommandAttribute commandAttribute) {
        log.info("Validation of commandAttribute '"+ commandAttribute.getAttributeName() + "' started");
        for (AttributeDescriptor attributeDescriptor : getAttributeRules().get(commandAttribute)) {
            chooseDescriptorParameterValidation(command, attributeDescriptor, commandAttribute);
        }
        return validationErrors.get(commandAttribute).isEmpty();
    }
}
