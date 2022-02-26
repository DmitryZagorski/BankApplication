package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.CommandAttribute;
import home.intexsoft.bank_application.dima.CommandAttributeName;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.models.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Validator {

    protected CommandValidationFactory commandValidationFactory = new CommandValidationFactory();
    //protected Map<CommandAttributeName, AttributeDescriptor> validationRules = new HashMap<>();
    protected Map<CommandAttributeName, List<String>> validationErrors = new HashMap<>();

    {
        validationErrors.put(CommandAttributeName.BANK_NAME, new ArrayList<>());
        validationErrors.put(CommandAttributeName.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(CommandAttributeName.COMMISSION_FOR_ENTITY, new ArrayList<>());
    }

    public Map<CommandAttributeName, List<String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<CommandAttributeName, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String validateIsStringEmpty(String string){

        return null;
    }



    public Boolean verifyIfNameOfBankExist(String bankName) {
        boolean isExist = false;
        List<Bank> banks = new BankRepresentation().findAllBanks();
        for (Bank bank : banks) {
            if (bankName.equalsIgnoreCase(bank.getName())) {
                isExist = true;
            }
        }
        return isExist;
    }

    public Boolean verifyIfIDoubleDigitAboveZero(Double digit) {
        boolean isAboveZero = true;
        if (digit <= 0) {
            isAboveZero = false;
        }
        return isAboveZero;
    }

    public abstract boolean validate(Command command, CommandAttribute commandAttribute, List<String> errors);

}
