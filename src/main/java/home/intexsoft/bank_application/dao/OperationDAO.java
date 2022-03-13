package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends DAO<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationDAO.class);


    public void create(@NotNull final Operation operation, Session session) throws SQLException {
        log.debug("DAO method of creation new operation started");
        try {
            session.save(operation);
        } catch (Exception e) {
            throw new SQLException("Error during saving operation");
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

    public void update(Operation operation, Session session) {
        log.debug("DAO method of updating operation started");
        try {
            session.update(operation);
        } catch (Exception e) {
            log.error("Error during saving transaction");
            session.getTransaction().rollback();
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
