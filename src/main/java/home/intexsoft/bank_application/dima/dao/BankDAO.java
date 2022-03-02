package home.intexsoft.bank_application.dima.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BankDAO implements DAO<Bank, Integer>{

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
    public Bank readAll(@NotNull final Bank bank) {
//        List<Bank> banks = new ArrayList<>();
//
//        try (final Session session = factory.openSession()) {
//
//            final Bank result = session.get(Bank.class, id);
//
//            return result != null ? result : new Bank();
//        }
        return null;
    }

    @Override
    public void update(@NotNull final Bank engine) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(engine);
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
}
