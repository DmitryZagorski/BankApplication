package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddBankCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddBankCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddBankCommandValidator.class);

    {
        validationErrors.put(AddBankCommand.Attribute.BANK_NAME, new ArrayList<>());
        validationErrors.put(AddBankCommand.Attribute.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(AddBankCommand.Attribute.COMMISSION_FOR_ENTITY, new ArrayList<>());

        attributeRules.put(AddBankCommand.Attribute.BANK_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddBankCommand.Attribute.COMMISSION_FOR_INDIVIDUAL, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "10"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankCommand.Attribute.COMMISSION_FOR_ENTITY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }

    //    @Override
//    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
//        log.debug("Validation of commandAttribute '" + commandAttributePair.getKey().getAttributeName() + "' started");
//        this.attributeRules.get(commandAttributePair.getKey())
//                .forEach(attributeDescriptor -> validateAttributeAccordingAttributeDescriptor
//                        (attributeDescriptor, commandAttributePair));
//        log.debug("Validation of commandAttribute '" + commandAttributePair.getKey().getAttributeName() + "' finished");
//    }

    @Override
    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor,
            Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validating of command attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (AddBankCommand.Attribute.BANK_NAME.equals(commandAttributePair.getKey())) {
            if (bankService.checkIfBankNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank already exists");
        }
        log.debug("Validating of command attribute finished");
    }
}
