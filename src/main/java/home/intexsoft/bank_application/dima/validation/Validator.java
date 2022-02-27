package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.CommandAttribute;
import home.intexsoft.bank_application.dima.String;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.models.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Validator {

    protected CommandValidationFactory commandValidationFactory = new CommandValidationFactory();
    protected Map<String, List<String>> validationErrors = new HashMap<>();

    {
        validationErrors.put(String.BANK_NAME, new ArrayList<>());
        validationErrors.put(String.COMMISSION_FOR_INDIVIDUAL, new ArrayList<>());
        validationErrors.put(String.COMMISSION_FOR_ENTITY, new ArrayList<>());
    }

    public Map<String, List<String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String validateIsStringEmpty(String string){

        return null;
    }

    public abstract boolean validate(AttributeDescriptor attributeDescriptor, List<String> errors);

    public Boolean verifyIfNameOfBankExist(String bankName) {
        boolean isExist = false;
        List<Bank> banks = new BankRepresentation().findAllBanks();
        for (Bank bank : banks) {
            if (bankName.equals(bank.getName())) {
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
}
