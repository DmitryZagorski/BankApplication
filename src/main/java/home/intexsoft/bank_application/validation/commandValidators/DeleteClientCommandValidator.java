package home.intexsoft.bank_application.validation.commandValidators;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.DeleteClientCommand;
import home.intexsoft.bank_application.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteClientCommandValidator extends Validator {

    private static final Logger log = LoggerFactory.getLogger(DeleteClientCommandValidator.class);

    {
        validationErrors.put(DeleteClientCommand.Attribute.CLIENT_NAME, new ArrayList<>());

        attributeRules.put(DeleteClientCommand.Attribute.CLIENT_NAME, List.of(
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.TYPE,
                        AttributeType.STRING.getAttributedName()),
                new AttributeDescriptor(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "30"),
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
        log.debug("Validating of command attribute started");
        super.validateAttributeAccordingAttributeDescriptor(attributeDescriptor, commandAttributePair);
        if (DeleteClientCommand.Attribute.CLIENT_NAME.equals(commandAttributePair.getKey())) {
            if (!clientService.checkIfClientNameExist(commandAttributePair.getValue())) {
                addErrorToErrorList(commandAttributePair.getKey(), commandAttributePair.getValue(),
                        "client doesn't exists");
            }
        }
        log.debug("Validating of command attribute finished");
    }
}