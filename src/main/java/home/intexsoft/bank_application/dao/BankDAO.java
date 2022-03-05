package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BankDAO implements DAO<Bank> {

    private static final Logger log = LoggerFactory.getLogger(BankDAO.class);
    private final SessionFactory sessionFactory;

    public BankDAO(@NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(@NotNull final Bank bank) {
        log.debug("DAO method of creation new bank started");
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new bank finished");
    }

    @Override
    public Bank read(@NotNull final Integer id) {
        log.debug("DAO method of finding bank by ID started");
        try (final Session session = sessionFactory.openSession()) {
            final Bank result = session.get(Bank.class, id);
            return result != null ? result : new Bank();
        }
    }

    @Override
    public Query<Bank> readAll(@NotNull final Bank bank) {
        log.debug("DAO method of finding all banks started");
        Query<Bank> banks;
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> rootEntry = query.from(Bank.class);
            CriteriaQuery<Bank> values = query.select(rootEntry);

            banks = session.createQuery(values);
        }
        log.debug("DAO method of finding all banks finished");
        return banks;
    }

    @Override
    public void update(@NotNull final Bank bank) {
        log.debug("DAO method of updating bank started");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(bank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of updating bank finished");
    }

    @Override
    public void delete(@NotNull final Bank bank) {
        log.debug("DAO method of deleting bank started");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(bank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of deleting bank finished");
    }

    @Override
    public Bank findByName(@NotNull final String name) {
        log.debug("DAO method of finding bank by name started");
        Bank bank = null;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> root = query.from(Bank.class);
            query.where(criteriaBuilder.equal(root.get("name"), name));
            Query<Bank> newQuery = session.createQuery(query);
            List<Bank> resultList = newQuery.getResultList();
            if (!resultList.isEmpty()) {
                bank = resultList.get(0);
            }
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding bank by name finished");
        return bank;
    }
}
