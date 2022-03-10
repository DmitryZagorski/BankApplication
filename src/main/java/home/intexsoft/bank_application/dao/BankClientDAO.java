package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.BankClient;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankClientDAO extends DAO<BankClient> {

    private static final Logger log = LoggerFactory.getLogger(BankClientDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public BankClientDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public void create(@NotNull final BankClient bankClient) {
        log.debug("DAO method of creation new bankClient started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(bankClient);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new bankClient finished");
    }

}
