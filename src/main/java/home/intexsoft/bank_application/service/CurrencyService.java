package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.CurrencyDAO;
import home.intexsoft.bank_application.models.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public void addCurrency(String currencyName, String rate) {
        log.debug("Method addCurrency started");
        Currency currency = new Currency();
        currency.setName(currencyName);
        currency.setRate(Double.valueOf(rate));
        currencyDAO.create(currency);
        log.debug("Method addCurrency finished");
    }

    Currency findByName(String currencyName) {
        return currencyDAO.findByName(currencyName);
    }

    public boolean checkIfCurrencyNameExist(String currencyName) {
        return currencyDAO.findByName(currencyName) != null;
    }
}