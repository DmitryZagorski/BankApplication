package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Action;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionDAO extends DAO<Action> {

    private static final Logger log = LoggerFactory.getLogger(ActionDAO.class);

    public Action createAction(@NotNull final Action action) {
        log.debug("DAO method of creation new action started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(action);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation new action finished");
        return action;
    }
}