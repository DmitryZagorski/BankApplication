package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.command.Command;
import home.intexsoft.bank_application.dima.command.CommandAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    private static final String VALIDATION_FAILURE_PATTERN = "Validation has failed on %s, processed value is %s";
    private static final String VALIDATION_FAILURE_FULL_PATTERN = "Validation has failed on %s, processed value is %s, %s expected %s";
    private static final String VALIDATION_EXCEPTION_PATTERN = "Exception: %s";

    protected Map<CommandAttribute, List<String>> validationErrors = new HashMap<>();
    protected Map<CommandAttribute, List<AttributeDescriptor>> attributeRules = new HashMap<>();

    public Map<CommandAttribute, List<String>> getValidationErrors() {
        return validationErrors;
    }

    protected Map<CommandAttribute, List<AttributeDescriptor>> getAttributeRules() {
        return attributeRules;
    }

    public abstract boolean validate(Command command, CommandAttribute commandAttribute);

    protected void chooseDescriptorParameterValidation(Command command, AttributeDescriptor attributeDescriptor, CommandAttribute commandAttribute) {
        log.info("Choosing validation descriptor of attribute '" + attributeDescriptor.getValue());
        String checkingString = command.getAttributes().get(commandAttribute);
        if (attributeDescriptor.getKind() == AttributeDescriptor.DescriptorParameter.TYPE) {
            validateType(attributeDescriptor.getValue(), commandAttribute, checkingString);
        } else {
            validateMaxAndMinValue(attributeDescriptor.getValue(), commandAttribute, attributeDescriptor.getKind(), checkingString);
        }
    }

    private void validateType(String value, CommandAttribute commandAttribute, String checkingString) {
        if (value.equals(AttributeType.DOUBLE.getAttributedName())) {
            checkAndSetNewValueOfDouble(checkingString, commandAttribute);
        }
        if (value.equals(AttributeType.DOUBLE.getAttributedName())) {
            checkAndSetNewValueOfDouble(checkingString, commandAttribute);
        }
    }

    private void validateMaxAndMinValue(String value, CommandAttribute commandAttribute, AttributeDescriptor.DescriptorParameter descriptorParameter, String checkingString) {
        log.info("Validation by value " + value + " started");
        double parameterValue;
        double checkingValue;
        try {
            if (attributeRules.get(commandAttribute).get(0).getValue().equals(AttributeType.STRING.getAttributedName())) {
                checkingValue = checkingString.length();
            } else {
                checkingValue = Double.parseDouble(checkingString);
            }
            parameterValue = Double.parseDouble(value);
            switch (descriptorParameter) {
                case MIN_VALUE:
                    if (checkingValue < parameterValue) {
                        validationErrors.get(commandAttribute).add(String.format(VALIDATION_FAILURE_FULL_PATTERN, "min value matching", checkingString, descriptorParameter.name(), value));
                    }
                    break;
                case MAX_VALUE:
                    if (checkingValue > parameterValue) {
                        validationErrors.get(commandAttribute).add(String.format(VALIDATION_FAILURE_FULL_PATTERN, "max value matching", checkingString, descriptorParameter.name(), value));
                    }
                    break;
                default:
                    throw new IllegalArgumentException("That type of descriptor doesn't exist " + descriptorParameter.name());
            }
        } catch (NumberFormatException e) {
            validationErrors.get(commandAttribute).add(
                    String.format(VALIDATION_EXCEPTION_PATTERN, e.getClass().getName()) +
                            String.format(VALIDATION_FAILURE_PATTERN, "checking type of entered data", checkingString));
        }
    }

    private void checkAndSetNewValueOfDouble(String checkingString, CommandAttribute commandAttribute) {
        try {
            Double.parseDouble(checkingString);
        } catch (NumberFormatException e) {
            validationErrors.get(commandAttribute).add(String.format(VALIDATION_FAILURE_PATTERN, "checking type of entered data", checkingString));
        }
    }

    private void checkAndSetNewValueOfInteger(String checkingString, CommandAttribute commandAttribute) {
        try {
            Integer.parseInt(checkingString);
        } catch (NumberFormatException e) {
            validationErrors.get(commandAttribute).add(String.format(VALIDATION_FAILURE_PATTERN, "checking type of entered data", checkingString));
        }
    }
}
