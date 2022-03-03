package home.intexsoft.bank_application.attributeDescriptor;

public enum AttributeType {

    STRING ("String"),
    DOUBLE ("Double"),
    INTEGER ("Integer");

    private String attributedName;

    AttributeType(String attributedName) {
        this.attributedName = attributedName;
    }

    public String getAttributedName() {
        return attributedName;
    }
}
