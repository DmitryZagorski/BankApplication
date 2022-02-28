package home.intexsoft.bank_application.dima.attributeDescriptor;

import java.util.HashMap;
import java.util.Map;

public abstract class AttributeDescriptor {

    private DescriptorParameter kind;
    private String value;

    public DescriptorParameter getKind() {
        return kind;
    }

    public String getValue() {
        return value;
    }

    //    private Map<DescriptorParameter, String> description = new HashMap<>();
//
//    public Map<DescriptorParameter, String> getDescription() {
//        return description;
//    }

    enum DescriptorParameter {
        MAX_VALUE,
        MIN_VALUE,
        STRING_MAX_LENGTH,
        STRING_MIN_LENGTH,
        TYPE;
    }
}




//        MAX_VALUE ("max value"),
//        MIN_VALUE ("min value"),
//        PERCENTAGE ("percentage"),
//        STRING_MAX_LENGTH ("string max length"),
//        STRING_MIN_LENGTH ("string min length"),
//        TYPE ("type");
//
//        private String descriptorParameterValue;
//
//        DescriptorParameter(String descriptorParameterValue) {
//            this.descriptorParameterValue = descriptorParameterValue;
//        }
//
//        public String getDescriptorParameterValue() {
//            return descriptorParameterValue;
//        }
//
//        public void setDescriptorParameterValue(String descriptorParameterValue) {
//            this.descriptorParameterValue = descriptorParameterValue;
//        }
