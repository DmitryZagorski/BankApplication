package home.intexsoft.bank_application.dima.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BankDAO implements DAO<Bank, String>{

    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public BankDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Create new engine in engines table.
     *
     * @param bank for add.
     */
    @Override
    public void create(@NotNull final Bank bank) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(bank);

            session.getTransaction().commit();
        }
    }

    /**
     * Get engine by model.
     *
     * @param model for select.
     * @return engine with param model.
     */
    @Override
    public Bank read(@NotNull final String model) {
        try (final Session session = factory.openSession()) {

            final Bank result = session.get(Bank.class, model);

            return result != null ? result : new Bank();
        }
    }

    /**
     * Update engine state.
     *
     * @param engine new state.
     */
    @Override
    public void update(@NotNull final Bank engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(engine);

            session.getTransaction().commit();
        }
    }

    /**
     * Delete engine.
     *
     * @param engine for delete.
     */
    @Override
    public void delete(@NotNull final Bank engine) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(engine);

            session.getTransaction().commit();
        }
    }

}
