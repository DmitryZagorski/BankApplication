package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddBankCommand;
import home.intexsoft.bank_application.command.AddClientCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddClientCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommandValidator.class);

    {
        validationErrors.put(AddClientCommand.Attribute.CLIENT_NAME, new ArrayList<>());
        validationErrors.put(AddClientCommand.Attribute.CLIENT_SURNAME, new ArrayList<>());
        validationErrors.put(AddClientCommand.Attribute.CLIENT_STATUS_ID, new ArrayList<>());
        validationErrors.put(AddClientCommand.Attribute.CURRENCY_ID, new ArrayList<>());
        validationErrors.put(AddClientCommand.Attribute.BANK_ID, new ArrayList<>());
        validationErrors.put(AddClientCommand.Attribute.AMOUNT_OF_MONEY, new ArrayList<>());

        attributeRules.put(AddClientCommand.Attribute.CLIENT_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "30"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddClientCommand.Attribute.CLIENT_SURNAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "40"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddClientCommand.Attribute.CLIENT_STATUS_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "2"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddClientCommand.Attribute.CURRENCY_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "3"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddClientCommand.Attribute.BANK_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddClientCommand.Attribute.AMOUNT_OF_MONEY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100000"),
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
        if (AddClientCommand.Attribute.CLIENT_NAME.equals(commandAttributePair.getKey())) {
            if (bankService.checkIfBankNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank already exists");
        }

        if (AddClientCommand.Attribute.CLIENT_STATUS_ID.equals(commandAttributePair.getKey())) {
            if (!clientStatusService.checkIfClientStatusIdExist(Integer.parseInt(commandAttributePair.getValue())))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "currency doesn't exists");
        }




        if (AddClientCommand.Attribute.CURRENCY_ID.equals(commandAttributePair.getKey())) {
            if (!currencyService.checkIfCurrencyIdExist(Integer.parseInt(commandAttributePair.getValue())))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "currency doesn't exists");
        }
        if (AddClientCommand.Attribute.BANK_ID.equals(commandAttributePair.getKey())) {
            if (!bankService.checkIfBankIdExist(Integer.parseInt(commandAttributePair.getValue())))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank doesn't exists");
        }






        if (AddBankCommand.Attribute.BANK_NAME.equals(commandAttributePair.getKey())) {
            if (bankService.checkIfBankNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank already exists");
        }

        log.debug("Validating of command attribute finished");
    }


}
