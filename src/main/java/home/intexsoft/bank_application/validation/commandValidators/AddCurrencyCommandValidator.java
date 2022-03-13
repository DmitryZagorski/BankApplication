package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddCurrencyCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddCurrencyCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommandValidator.class);

    {
        validationErrors.put(AddCurrencyCommand.Attribute.CURRENCY_NAME, new ArrayList<>());
        validationErrors.put(AddCurrencyCommand.Attribute.RATE, new ArrayList<>());

        attributeRules.put(AddCurrencyCommand.Attribute.CURRENCY_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "5"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddCurrencyCommand.Attribute.RATE, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "40"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));
    }

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }

    @Override
    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor,
            Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validating of command attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (AddCurrencyCommand.Attribute.CURRENCY_NAME.equals(commandAttributePair.getKey())) {
            if (currencyService.checkIfCurrencyNameExist(commandAttributePair.getValue())) {
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "currency already exists");
            }
        }
        log.debug("Validating of command attribute finished");
    }

}
