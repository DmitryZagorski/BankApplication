package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ActionDAO extends DAO<Action> {

    private static final Logger log = LoggerFactory.getLogger(ActionDAO.class);

    public void createAction(@NotNull final Action action) {
        log.debug("DAO method of creation new action started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(action);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during saving action");
            session.getTransaction().rollback();
        }
        log.debug("DAO method of creation new action finished");
    }

    public List<Action> findActionsByBankAccountId(Integer bankAccountId) {
        log.debug("DAO method of finding actions by bank account started");
        List<Action> actions;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Action> query = criteriaBuilder.createQuery(Action.class);
            Root<Action> root = query.from(Action.class);
            query.where(criteriaBuilder.equal(root.get("bankAccount"), bankAccountId));
            Query<Action> newQuery = session.createQuery(query);
            actions = newQuery.getResultList();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding actions by bank account finished");
        return actions;
    }

}