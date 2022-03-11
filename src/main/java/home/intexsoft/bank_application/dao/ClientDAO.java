package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.models.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientDAO extends DAO<Client> {

    private static final Logger log = LoggerFactory.getLogger(ClientDAO.class);

    @Override
    public void create(@NotNull final Client client) {
        log.debug("DAO method of creation client started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
        log.debug("DAO method of creation client finished");
    }

//    @Override
//    public List<Client> findAll() {
//        log.debug("DAO method of finding all banks started");
//        List<Client> clientList = null;
//        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            //clientList = session.createQuery("from Client").list();
//            session.getTransaction().commit();
//        }
//        log.debug("DAO method of finding all banks finished");
//        return clientList;
//    }

    @Override
    Client findById(Integer clientId) {
        return super.findById(clientId);
    }

    @Override
    public Client findByName(@NotNull final String clientName) {
        log.debug("DAO method of finding client by name started");
        Client client = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.where(criteriaBuilder.equal(root.get("name"), clientName));
            Query<Client> newQuery = session.createQuery(query);
            List<Client> resultList = newQuery.getResultList();
            if (!resultList.isEmpty()) {
                client = resultList.get(0);
            }
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding client by name finished");
        return client;
    }

    public List<Client> findClientsOfBank(@NotNull final Integer bankId) {
        log.debug("DAO method of finding client by bankName started");
        List<Client> clients;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.where(criteriaBuilder.equal(root.get("bank"), bankId));
            Query<Client> newQuery = session.createQuery(query);
            clients = newQuery.getResultList();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding client by bankName finished");
        return clients;
    }

    @Override
    public void deleteByName(@NotNull final String clientName) {
        log.debug("DAO method of deleting client by name started");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.where(criteriaBuilder.equal(root.get("name"), clientName));
            Query<Client> newQuery = session.createQuery(query);
            Client foundClient = newQuery.getSingleResult();
            session.delete(foundClient);
            session.getTransaction().commit();
        }
        log.debug("DAO method of deleting client by name finished");
    }

    public void delete(@NotNull final Client client) {
        log.debug("DAO method of deleting client started");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }
        log.debug("DAO method of deleting client finished");
    }

}
