package home.intexsoft.bank_application.dima.attributeDescriptor;

public class StringDescriptor extends AttributeDescriptor{

    private boolean shortLength;

    public StringDescriptor(AttributeType type, boolean notNull, boolean shortLength) {
        super(type, notNull);
        this.shortLength = shortLength;
    }

    public boolean isShortLength() {
        return shortLength;
    }

    @Override
    public AttributeType getType() {
        return super.getType();
    }

    @Override
    public boolean isNotNull() {
        return super.isNotNull();
    }
}
