package home.intexsoft.bank_application.dima.attributeDescriptor;

public class AttributeDescriptor {

    protected DescriptorParameter kind;
    protected String value;

    public AttributeDescriptor(DescriptorParameter kind, String value) {
        this.kind = kind;
        this.value = value;
    }

    public DescriptorParameter getKind() {
        return kind;
    }

    public void setKind(DescriptorParameter kind) {
        this.kind = kind;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    //    private Map<DescriptorParameter, String> description = new HashMap<>();
//
//    public Map<DescriptorParameter, String> getDescription() {
//        return description;
//    }

    public enum DescriptorParameter {
        MAX_VALUE,
        MIN_VALUE,
        STRING_MAX_LENGTH,
        STRING_MIN_LENGTH,
        TYPE;
    }
}




