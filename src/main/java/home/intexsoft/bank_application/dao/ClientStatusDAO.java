package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Currency;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientStatusDAO extends DAO<Currency> {

    private static final Logger log = LoggerFactory.getLogger(ClientStatusDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public ClientStatusDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    Currency findById(Integer value) {
        return super.findById(value);
    }

    @Override
    Currency findByName(String value) {
        return super.findByName(value);
    }
}
