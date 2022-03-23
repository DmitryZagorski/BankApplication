package home.intexsoft.bank_application.dao;

import home.intexsoft.bank_application.models.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActionDAO extends DAO<Action> {

    private static final Logger log = LoggerFactory.getLogger(ActionDAO.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Action> findActionsByBankAccountId(Integer bankAccountId) {
        log.debug("DAO method of finding actions by bank account id '" + bankAccountId + "' started");
        final Session session = sessionFactory.openSession();
        List<Action> actions = new ArrayList<>();
        try {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Action> query = criteriaBuilder.createQuery(Action.class);
            Root<Action> root = query.from(Action.class);
            query.where(criteriaBuilder.equal(root.get("bankAccount"), bankAccountId));
            Query<Action> newQuery = session.createQuery(query);
            actions = newQuery.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during updating action");
            session.getTransaction().rollback();
            session.close();
        }
        log.debug("DAO method of finding actions by bank account id '" + bankAccountId + "' started");
        return actions;
    }
}