package home.intexsoft.bank_application.dima.attributeDescriptor;

public class DoubleAttributeDescriptor extends AttributeDescriptor {

    private boolean percentage;

    public DoubleAttributeDescriptor(AttributeType type, boolean notNull, boolean percentage) {
        super(type, notNull);
        this.percentage = percentage;
    }

    public boolean isPercentage() {
        return percentage;
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
