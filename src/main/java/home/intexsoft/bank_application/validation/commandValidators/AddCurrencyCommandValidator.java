package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.AddCurrencyCommand;
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
public class AddCurrencyCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommandValidator.class);

    @Autowired
    private CurrencyService currencyService;

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

//    public AddCurrencyCommandValidator(ClientService clientService, BankService bankService, CurrencyService currencyService, BankAccountService bankAccountService) {
//        super(clientService, bankService, currencyService, bankAccountService);
//    }

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }

    @Override
    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor,
            Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validating of '" + Command.CommandType.ADD_CURRENCY.getCommandName() + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (AddCurrencyCommand.Attribute.CURRENCY_NAME.equals(commandAttributePair.getKey())) {
            if (currencyService.checkIfCurrencyNameExist(commandAttributePair.getValue())) {
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "currency already exists");
            }
        }
        log.debug("Validating of '" + Command.CommandType.ADD_CURRENCY.getCommandName() + "' attribute finished");
    }
}