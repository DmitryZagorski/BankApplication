package home.intexsoft.bank_application.dima;

public enum CommandAttributeName {

    BANK_NAME ("bank name"),
    COMMISSION_FOR_INDIVIDUAL ("commission for entity"),
    COMMISSION_FOR_ENTITY ("commission for entity");

    private String attributedName;

    CommandAttributeName(String attributedName) {
        this.attributedName = attributedName;
    }

    public String getAttributedName() {
        return attributedName;
    }

    public void setAttributedName(String attributedName) {
        this.attributedName = attributedName;
    }
}
