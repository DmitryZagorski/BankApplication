package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.DeleteBankCommand;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.BankService;
import home.intexsoft.bank_application.service.ClientService;
import home.intexsoft.bank_application.service.CurrencyService;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteBankCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(DeleteBankCommandValidator.class);

    {
        validationErrors.put(DeleteBankCommand.Attribute.BANK_NAME, new ArrayList<>());

        attributeRules.put(DeleteBankCommand.Attribute.BANK_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }

    public DeleteBankCommandValidator(ClientService clientService, BankService bankService, CurrencyService currencyService, BankAccountService bankAccountService) {
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
        log.debug("Validating of '" + Command.CommandType.DELETE_BANK.getCommandName() + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (DeleteBankCommand.Attribute.BANK_NAME.equals(commandAttributePair.getKey())) {
            if (!bankService.checkIfBankNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "bank doesn't exists");
        }
        log.debug("Validating of '" + Command.CommandType.DELETE_BANK.getCommandName() + "' attribute finished");
    }
}