package home.intexsoft.bank_application.dima.attributeDescriptor;

public class AttributeDescriptor {

    private DescriptorParameter kind;
    private String value;

    public AttributeDescriptor(DescriptorParameter kind, String value) {
        this.kind = kind;
        this.value = value;
    }

    public DescriptorParameter getKind() {
        return kind;
    }

    public String getValue() {
        return value;
    }

    public enum DescriptorParameter {
        MAX_VALUE,
        MIN_VALUE,
        STRING_MAX_LENGTH,
        STRING_MIN_LENGTH,
        TYPE;

        public void setDescriptorParameterName(String descriptorParameterName) {
        }
    }

    static {
        DescriptorParameter.MAX_VALUE.setDescriptorParameterName("max value");
        DescriptorParameter.MIN_VALUE.setDescriptorParameterName("min value");
        DescriptorParameter.STRING_MAX_LENGTH.setDescriptorParameterName("max length of value");
        DescriptorParameter.STRING_MIN_LENGTH.setDescriptorParameterName("min length of value");
        DescriptorParameter.TYPE.setDescriptorParameterName("type");
    }
}




