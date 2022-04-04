package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.FindBankAccountsOfClientCommand;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindBankAccountsOfClientCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(FindBankAccountsOfClientCommandValidator.class);

    {
        validationErrors.put(FindBankAccountsOfClientCommand.Attribute.CLIENT_NAME, new ArrayList<>());

        attributeRules.put(FindBankAccountsOfClientCommand.Attribute.CLIENT_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20"),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "2")));
    }

    @Override
    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        super.validateAttribute(commandAttributePair);
    }

    @Override
    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor,
            Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validating of '" + Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT.getCommandName()
                + "' attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (FindBankAccountsOfClientCommand.Attribute.CLIENT_NAME.equals(commandAttributePair.getKey())) {
            if (!clientService.checkIfClientNameExist(commandAttributePair.getValue()))
                addErrorToErrorList(
                        commandAttributePair.getKey(), commandAttributePair.getValue(), "client doesn't exists");
        }
        log.debug("Validating of '" + Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT.getCommandName()
                + "' attribute finished");
    }
}
