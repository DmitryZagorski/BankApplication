package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Currency;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyDAO extends DAO<Currency> {

    private static final Logger log = LoggerFactory.getLogger(CurrencyDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public CurrencyDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public Currency findById(Integer value) {
        return super.findById(value);
    }

    @Override
    public Currency findByName(String value) {
        return super.findByName(value);
    }
}
