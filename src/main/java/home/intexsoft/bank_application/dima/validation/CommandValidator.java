package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.commandRepresentation.BankRepresentation;
import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.models.Bank;

import java.util.List;

public abstract class CommandValidator {

    public abstract boolean validate(Command command);

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





}
