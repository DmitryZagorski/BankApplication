package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.CurrencyDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private CurrencyDAO currencyDAO = new CurrencyDAO();

    public boolean checkICurrencyNameExist(String currencyName) {
        return currencyDAO.findByName(currencyName) != null;
    }

    public boolean checkIfCurrencyIdExist(Integer currencyId) {
        return currencyDAO.findById(currencyId) != null;
    }

}
