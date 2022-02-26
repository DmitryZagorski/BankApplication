package home.intexsoft.bank_application.dima.attributeDescriptor;

public class LongStringDescriptor extends StringDescriptor {

    @Override
    public int getMaxLength() {
        return super.getMaxLength();
    }

    @Override
    public void setMaxLength(int maxLength) {
        super.setMaxLength(100);
    }

    @Override
    public int getMinLength() {
        return super.getMinLength();
    }

    @Override
    public void setMinLength(int minLength) {
        super.setMinLength(5);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType("String");
    }
}
