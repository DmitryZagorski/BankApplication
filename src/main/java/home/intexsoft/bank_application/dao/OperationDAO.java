package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OperationDAO extends DAO<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationDAO.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public OperationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createOperation(@NotNull final Operation operation) {
        log.debug("DAO method of creation new operation started");
        final Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during creation new operation" + e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        log.debug("DAO method of creation new operation finished");
    }

    public void update(Operation operation) {
        log.debug("DAO method of updating operation started");
        final Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(operation);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            log.error("Error during updating operation" + e);
            throw e;
        }
        log.debug("DAO method of updating operation finished");
    }
}