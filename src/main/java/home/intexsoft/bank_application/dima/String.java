package home.intexsoft.bank_application.dima;

public enum String {

    BANK_NAME ("bank name"),
    COMMISSION_FOR_INDIVIDUAL ("commission for entity"),
    COMMISSION_FOR_ENTITY ("commission for entity");

    private java.lang.String attributedName;

    String(java.lang.String attributedName) {
        this.attributedName = attributedName;
    }

    public java.lang.String getAttributedName() {
        return attributedName;
    }

    public void setAttributedName(java.lang.String attributedName) {
        this.attributedName = attributedName;
    }
}
