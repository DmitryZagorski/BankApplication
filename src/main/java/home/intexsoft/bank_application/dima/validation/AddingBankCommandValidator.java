package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.BankAppRunner;
import home.intexsoft.bank_application.dima.Command;

public class AddingBankCommandValidator extends BankCommandValidator {

    @Override
    public boolean validate(Command command) {
        boolean isValidated = false;
        try {
            String bankName = command.getAttributes().get("bank name");
            Double commissionForIndividual = Double.parseDouble(command.getAttributes().get("commission for individual"));
            Double commissionForEntity = Double.parseDouble(command.getAttributes().get("commission for entity"));
            if (!verifyIfNameOfBankExist(bankName) &
                    verifyIfIDoubleDigitAboveZero(commissionForIndividual) &
                    verifyIfIDoubleDigitAboveZero(commissionForEntity)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
            new BankAppRunner().runMenu();
        }
        return isValidated;
    }
}
