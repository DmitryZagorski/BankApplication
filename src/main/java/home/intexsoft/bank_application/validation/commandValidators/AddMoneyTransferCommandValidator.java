package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.BankService;
import home.intexsoft.bank_application.service.ClientService;
import home.intexsoft.bank_application.service.CurrencyService;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AddMoneyTransferCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddMoneyTransferCommandValidator.class);

    @Autowired
    private BankAccountService bankAccountService;

    {
        validationErrors.put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, new ArrayList<>());
        validationErrors.put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, new ArrayList<>());
        validationErrors.put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, new ArrayList<>());

        attributeRules.put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.INTEGER.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "100"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, List.of(
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
        log.debug("Validating of '" + Command.CommandType.ADD_MONEY_TRANSFER.getCommandName() + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID.equals(commandAttributePair.getKey())) {
            if (!bankAccountService.checkIfBankAccountExist(commandAttributePair.getValue()))
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "bank account doesn't exists");
        }
        if (AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID.equals(commandAttributePair.getKey())) {
            if (!bankAccountService.checkIfBankAccountExist(commandAttributePair.getValue()))
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "bank account doesn't exists");
        }
        log.debug("Validating of '" + Command.CommandType.ADD_MONEY_TRANSFER.getCommandName() + "' attribute finished");
    }
}