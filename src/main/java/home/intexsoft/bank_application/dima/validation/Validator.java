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

public class Validator {

    private static final Logger Log = LoggerFactory.getLogger(Validator.class);

    protected Map<CommandAttribute, List<String>> validationErrors = new HashMap<>();
    protected Map<CommandAttribute, List<AttributeDescriptor>> attributeRules = new HashMap<>();

    public Map<CommandAttribute, List<String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<CommandAttribute, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<CommandAttribute, List<AttributeDescriptor>> getAttributeRules() {
        return attributeRules;
    }

    public void setAttributeRules(Map<CommandAttribute, List<AttributeDescriptor>> attributeRules) {
        this.attributeRules = attributeRules;
    }

    public List<String> validate(Command command, CommandAttribute commandAttribute) {

        return null;
    }

    public void validateType(String value, CommandAttribute commandAttribute, String checkingString) {
        if (value.equals(AttributeType.DOUBLE.getAttributedName())) {
            validationErrors.get(commandAttribute).add(verifyIfValueIsDouble(checkingString));
        }
        if (value.equals(AttributeType.INTEGER.getAttributedName())) {
            validationErrors.get(commandAttribute).add(verifyIfValueIsInteger(checkingString));
        }
    }

    public void validateMaxValue(String value, CommandAttribute commandAttribute, String checkingString) {
        String error = null;
        if (Double.parseDouble(checkingString)>Double.parseDouble(value)){
            error = "The number must be less " + value;
        }
        validationErrors.get(commandAttribute).add(error);
    }

    public void validateMinValue(String value, CommandAttribute commandAttribute, String checkingString) {
        String error = null;
        if (Double.parseDouble(checkingString)<Double.parseDouble(value)){
            error = "The number must be less " + value;
        }
        validationErrors.get(commandAttribute).add(error);
    }

    public void validateStringMaxLength(String value, CommandAttribute commandAttribute, String checkingString) {

    }

    public void validateStringMinValue(String value, CommandAttribute commandAttribute, String checkingString) {

    }

    private String verifyIfValueIsDouble(String checkingString) {
        String error = null;
        try {
            Double.parseDouble(checkingString);
        } catch (NumberFormatException e) {
            error = "Your data is not Double";
        }
        return error;
    }

    private String verifyIfValueIsInteger(String checkingString) {
        String error = null;
        try {
            Integer.parseInt(checkingString);
        } catch (NumberFormatException e) {
            error = "Your data is not Integer";
        }
        return error;
    }
}
