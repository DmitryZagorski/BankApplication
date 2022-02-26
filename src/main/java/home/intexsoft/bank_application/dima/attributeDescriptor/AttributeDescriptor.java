package home.intexsoft.bank_application.dima.attributeDescriptor;

public abstract class AttributeDescriptor {

    private AttributeType type;
    private boolean notNull;

    public AttributeDescriptor(AttributeType type, boolean notNull) {
        this.type = type;
        this.notNull = notNull;
    }

    public AttributeType getType() {
        return type;
    }

    public boolean isNotNull() {
        return notNull;
    }
}
