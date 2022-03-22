package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddBankAccountCommand;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddBankAccountCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddBankAccountCommandValidator.class);

    {
        validationErrors.put(AddBankAccountCommand.Attribute.BANK_NAME, new ArrayList<>());
        validationErrors.put(AddBankAccountCommand.Attribute.CLIENT_NAME, new ArrayList<>());
        validationErrors.put(AddBankAccountCommand.Attribute.CLIENT_SURNAME, new ArrayList<>());
        validationErrors.put(AddBankAccountCommand.Attribute.CLIENT_STATUS, new ArrayList<>());
        validationErrors.put(AddBankAccountCommand.Attribute.CURRENCY_NAME, new ArrayList<>());
        validationErrors.put(AddBankAccountCommand.Attribute.AMOUNT_OF_MONEY, new ArrayList<>());

        attributeRules.put(AddBankAccountCommand.Attribute.BANK_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));

        attributeRules.put(AddBankAccountCommand.Attribute.CLIENT_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "30"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankAccountCommand.Attribute.CLIENT_SURNAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "30"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankAccountCommand.Attribute.CLIENT_STATUS, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "30"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankAccountCommand.Attribute.CURRENCY_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "5"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));

        attributeRules.put(AddBankAccountCommand.Attribute.AMOUNT_OF_MONEY, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.DOUBLE.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "10000"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "1")));
    }

    public AddBankAccountCommandValidator(ClientService clientService, BankService bankService,
                                          CurrencyService currencyService, BankAccountService bankAccountService) {
        super(clientService, bankService, currencyService, bankAccountService);
    }

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }

    @Override
    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor,
            Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validating of '" + Command.CommandType.ADD_BANK_ACCOUNT.getCommandName() + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (AddBankAccountCommand.Attribute.BANK_NAME.equals(commandAttributePair.getKey())) {
            if (!bankService.checkIfBankNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank doesn't exists");
        }
        if (AddBankAccountCommand.Attribute.CURRENCY_NAME.equals(commandAttributePair.getKey())) {
            if (!currencyService.checkIfCurrencyNameExist((commandAttributePair.getValue()))) {
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "currency doesn't exists");
            }
        }
        log.debug("Validating of '" + Command.CommandType.ADD_BANK_ACCOUNT.getCommandName() + "' attribute finished");
    }
}