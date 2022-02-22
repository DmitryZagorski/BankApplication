package home.intexsoft.bank_application.dima.userInputData;

import home.intexsoft.bank_application.commandRepresentation.ClientRepresentation;
import home.intexsoft.bank_application.models.Currency;

import java.util.List;

public class ViewCurrency {

    public void execute() {
        List<Currency> allCurrency = new ClientRepresentation().findAllCurrency();
        Currency currency;
        for (Currency value : allCurrency) {
            currency = value;
            System.out.println(currency.getId() + " " + currency.getName());
        }
    }
}
