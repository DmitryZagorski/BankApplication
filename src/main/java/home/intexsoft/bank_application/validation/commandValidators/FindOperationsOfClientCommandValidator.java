package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.FindOperationsOfClientCommand;
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
public class FindOperationsOfClientCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(FindOperationsOfClientCommandValidator.class);

    @Autowired
    private ClientService clientService;

    {
        validationErrors.put(FindOperationsOfClientCommand.Attribute.CLIENT_NAME, new ArrayList<>());

        attributeRules.put(FindOperationsOfClientCommand.Attribute.CLIENT_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "40"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }

//    public FindOperationsOfClientCommandValidator(ClientService clientService, BankService bankService, CurrencyService currencyService, BankAccountService bankAccountService) {
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
        log.debug("Validating of '" + Command.CommandType.FIND_OPERATIONS_OF_CLIENT.getCommandName()
                + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (FindOperationsOfClientCommand.Attribute.CLIENT_NAME.equals(commandAttributePair.getKey())) {
            if (!clientService.checkIfClientNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "client doesn't exists");
        }
        log.debug("Validating of '" + Command.CommandType.FIND_OPERATIONS_OF_CLIENT.getCommandName()
                + "' attribute finished");
    }
}