package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OperationDAO extends DAO<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationDAO.class);


    public void createOperation(@NotNull final Operation operation) throws Exception {
        log.debug("DAO method of creation new operation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during creation new operation" + e);
            throw e;
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

    public void update(Operation operation) {
        log.debug("DAO method of updating operation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
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

    public List<Operation> findOperationsOfClient(Action action){
        log.debug("DAO method of finding operations of client started");
        List<Operation> operations;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Operation> query = criteriaBuilder.createQuery(Operation.class);
            Root<Operation> root = query.from(Operation.class);
            query.where(criteriaBuilder.equal(root.get("action"), action));
            Query<Operation> newQuery = session.createQuery(query);
            operations = newQuery.getResultList();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding operations of client finished");
        return operations;
    }

    @Override
    void deleteByName(String entityName) {
    }

    @Override
    Operation findByName(String value) {
        return null;
    }
}
