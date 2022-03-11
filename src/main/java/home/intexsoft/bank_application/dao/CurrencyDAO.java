package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.models.Client;
import home.intexsoft.bank_application.models.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CurrencyDAO extends DAO<Currency> {

    private static final Logger log = LoggerFactory.getLogger(CurrencyDAO.class);

    public void create(@NotNull final Currency currency) {
        log.debug("DAO method of creation new bank started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(currency);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new bank finished");
    }

    @Override
    public Currency findById(Integer value) {
        return super.findById(value);
    }

    @Override
    public Currency findByName(@NotNull final String currencyName) {
        log.debug("DAO method of finding currency by name started");
        Currency currency = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Currency> query = criteriaBuilder.createQuery(Currency.class);
            Root<Currency> root = query.from(Currency.class);
            query.where(criteriaBuilder.equal(root.get("name"), currencyName));
            Query<Currency> newQuery = session.createQuery(query);
            List<Currency> resultList = newQuery.getResultList();
            if (!resultList.isEmpty()) {
                currency = resultList.get(0);
            }
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding currency by name finished");
        return currency;
    }
}
