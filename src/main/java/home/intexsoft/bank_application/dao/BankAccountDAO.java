package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.BankAccount;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccountDAO extends DAO<BankAccount> {

    private static final Logger log = LoggerFactory.getLogger(BankAccountDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public BankAccountDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public void create(@NotNull final BankAccount bankAccount) {
        log.debug("DAO method of creation new bankAccount started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(bankAccount);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new bankAccount finished");
    }

    @Override
    BankAccount findById(Integer value) {
        return super.findById(value);
    }
}
