package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.attributeDescriptor.DoubleAttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.StringDescriptor;

import java.util.HashMap;
import java.util.Map;

public class CommandAttribute {

    private Map<CommandAttributeName, AttributeDescriptor> attributeRules = new HashMap<>();

    public Map<CommandAttributeName, AttributeDescriptor> getAttributeRules() {
        return attributeRules;
    }

    public void setAttributeRules(Map<CommandAttributeName, AttributeDescriptor> attributeRules) {
        this.attributeRules = attributeRules;
    }

    {
        attributeRules.put(CommandAttributeName.BANK_NAME, new StringDescriptor(AttributeType.STRING, true, true));
        attributeRules.put(CommandAttributeName.COMMISSION_FOR_INDIVIDUAL, new DoubleAttributeDescriptor(AttributeType.DOUBLE, true, true));
        attributeRules.put(CommandAttributeName.COMMISSION_FOR_ENTITY, new DoubleAttributeDescriptor(AttributeType.DOUBLE, true, true));
    }

}
