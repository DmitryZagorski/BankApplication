package home.intexsoft.bank_application.command;

public interface CommandAttribute {

    String getAttributeName();

    CommandAttribute getConstant(String attributeName);

}