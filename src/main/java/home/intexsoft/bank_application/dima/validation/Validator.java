package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
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

    public List<String> validate(Command command){

        return null;
    }



}
