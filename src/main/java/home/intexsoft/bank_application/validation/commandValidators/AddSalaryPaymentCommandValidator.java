package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddSalaryPaymentCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddSalaryPaymentCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddSalaryPaymentCommandValidator.class);

    {
        validationErrors.put(AddSalaryPaymentCommand.Attribute.EMPLOYER_BANK_ACCOUNT_ID, new ArrayList<>());
        validationErrors.put(AddSalaryPaymentCommand.Attribute.EMPLOYEE_BANK_ACCOUNT_ID, new ArrayList<>());
        validationErrors.put(AddSalaryPaymentCommand.Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID, new ArrayList<>());
        validationErrors.put(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY, new ArrayList<>());

        attributeRules.put(AddSalaryPaymentCommand.Attribute.EMPLOYER_BANK_ACCOUNT_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddSalaryPaymentCommand.Attribute.EMPLOYEE_BANK_ACCOUNT_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddSalaryPaymentCommand.Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "10000"),
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
        if (AddSalaryPaymentCommand.Attribute.EMPLOYER_BANK_ACCOUNT_ID.equals(commandAttributePair.getKey())) {
            if (!bankAccountService.checkIfBankAccountExist(commandAttributePair.getValue()))
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "bank account doesn't exists");
        }
        if (AddSalaryPaymentCommand.Attribute.EMPLOYEE_BANK_ACCOUNT_ID.equals(commandAttributePair.getKey())) {
            if (!bankAccountService.checkIfBankAccountExist(commandAttributePair.getValue()))
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "bank account doesn't exists");
        }
        if (AddSalaryPaymentCommand.Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID.equals(commandAttributePair.getKey())) {
            if (!bankAccountService.checkIfBankAccountExist(commandAttributePair.getValue()))
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "bank account doesn't exists");
        }
        log.debug("Validating of command attribute finished");
    }

}