package home.intexsoft.bank_application.attributeDescriptor;

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
        TYPE;

        public void setDescriptorParameterName(String descriptorParameterName) {
        }
    }

    static {
        DescriptorParameter.MAX_VALUE.setDescriptorParameterName("max value");
        DescriptorParameter.MIN_VALUE.setDescriptorParameterName("min value");
        DescriptorParameter.TYPE.setDescriptorParameterName("type");
    }
}