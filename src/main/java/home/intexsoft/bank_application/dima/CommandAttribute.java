package home.intexsoft.bank_application.dima;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.dima.attributeDescriptor.DoubleAttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.StringDescriptor;

import java.util.HashMap;
import java.util.Map;

public class CommandAttribute {

    private Map<String, AttributeDescriptor> attributeRules = new HashMap<>();

    public Map<String, AttributeDescriptor> getAttributeRules() {
        return attributeRules;
    }

    public void setAttributeRules(Map<String, AttributeDescriptor> attributeRules) {
        this.attributeRules = attributeRules;
    }

    {
        attributeRules.put(String.BANK_NAME, new StringDescriptor(AttributeType.STRING, true, true));
        attributeRules.put(String.COMMISSION_FOR_INDIVIDUAL, new DoubleAttributeDescriptor(AttributeType.DOUBLE, true, true));
        attributeRules.put(String.COMMISSION_FOR_ENTITY, new DoubleAttributeDescriptor(AttributeType.DOUBLE, true, true));
    }

}
