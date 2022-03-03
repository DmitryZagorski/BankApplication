package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BankDAO implements DAO<Bank> {

    private final SessionFactory sessionFactory;

    public BankDAO(@NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(@NotNull final Bank bank) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bank);
            session.getTransaction().commit();
        }
    }

    @Override
    public Bank read(@NotNull final Integer id) {
        try (final Session session = sessionFactory.openSession()) {
            final Bank result = session.get(Bank.class, id);
            return result != null ? result : new Bank();
        }
    }

    @Override
    public Query<Bank> readAll(@NotNull final Bank bank) {
        Query<Bank> banks;
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> rootEntry = query.from(Bank.class);
            CriteriaQuery<Bank> values = query.select(rootEntry);

            banks = session.createQuery(values);
        }
        return banks;
    }

    @Override
    public void update(@NotNull final Bank bank) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(bank);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Bank engine) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(engine);
            session.getTransaction().commit();
        }
    }

    @Override
    public Bank findByName(@NotNull final String name) {
        Bank bank = null;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> root = query.from(Bank.class);
            query.where(criteriaBuilder.equal(root.get("name"), name));
            Query<Bank> newQuery = session.createQuery(query);
            List<Bank> resultList = newQuery.getResultList();
            if (!resultList.isEmpty()){
                bank = resultList.get(0);
            }
            session.getTransaction().commit();
        }
        return bank;
    }
}
