package home.intexsoft.bank_application.dao;

import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OperationDAO extends DAO<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationDAO.class);

    public Operation createOperation(Operation operation) {
        log.debug("DAO method of creation new operation started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new operation finished");
        return operation;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(operation);
            session.getTransaction().commit();
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
