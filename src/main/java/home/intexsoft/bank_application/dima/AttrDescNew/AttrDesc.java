package home.intexsoft.bank_application.dima.AttrDescNew;

import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;

public class AttrDesc {

    private AttributeDescriptor.DescriptorParameter kind;
    private String value;

    public AttrDesc(AttributeDescriptor.DescriptorParameter kind, String value) {
        this.kind = kind;
        this.value = value;
    }

    public AttributeDescriptor.DescriptorParameter getKind() {
        return kind;
    }

    public void setKind(AttributeDescriptor.DescriptorParameter kind) {
        this.kind = kind;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public enum DescriptorParameter {
        MAX_VALUE,
        MIN_VALUE,
        STRING_MAX_LENGTH,
        STRING_MIN_LENGTH,
        TYPE;
    }

}
