package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OperationDAO extends DAO<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationDAO.class);

    @Override // ready
    public void create(@NotNull final Operation operation) {
        log.debug("DAO method of creation new operation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                log.error("Error during saving transaction");
                session.getTransaction().rollback();
            } catch (Exception ex) {
                log.error("Error during rollback");
            }
        }
        log.debug("DAO method of creation new operation finished");
    }

    @Override
    Operation findById(Integer value) {
        return null;
    }

    @Override
    List<Operation> findAll() {
        return null;
    }

    @Override
    public void update(Operation operation) {
        log.debug("DAO method of updating operation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(operation);
            session.getTransaction().commit();
        } catch (Exception e) {
            try {
                log.error("Error during saving transaction");
                session.getTransaction().rollback();
            } catch (Exception ex) {
                log.error("Error during rollback");
            }
        }
        log.debug("DAO method of updating operation finished");
    }

    @Override
    void deleteByName(String entityName) {
    }

    @Override
    Operation findByName(String value) {
        return null;
    }
}
