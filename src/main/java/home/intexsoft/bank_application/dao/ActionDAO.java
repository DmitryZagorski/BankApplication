package home.intexsoft.bank_application.dao;

import home.intexsoft.bank_application.models.*;
import lombok.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.criteria.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ActionDAO extends DAO<Action> {

    private static final Logger log = LoggerFactory.getLogger(ActionDAO.class);

    private final SessionFactory sessionFactory;

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