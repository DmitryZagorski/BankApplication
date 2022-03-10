package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientDAO extends DAO<Client> {

    private static final Logger log = LoggerFactory.getLogger(ClientDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public ClientDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

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

    public void findClientsOfBank(@NotNull final Integer bankId) {
        log.debug("DAO method of finding clients of bank started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String findClientsOfBankByBankName = ("select bank_clients.id, clients.name, clients.surname from " +
                    "bank_clients inner join clients on bank_clients.client_id = clients.id where bank_id = ")
                    .concat(String.valueOf(bankId));
            Query query = session.createQuery(findClientsOfBankByBankName);
            List list = query.list();
            for (Object o : list) {
                System.out.println(o);
            }
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding clients of bank finished");
    }

    public String findLastClientId(){
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);

//            query.where(criteriaBuilder.equal(root.get("name"), bankName));
//            Query<Bank> newQuery = session.createQuery(query);
//            Bank foundBank = newQuery.getSingleResult();
//            session.delete(foundBank);
//            session.getTransaction().commit();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        log.debug("DAO method of finding all banks started");
        List<Client> clientList = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //clientList = session.createQuery("from Client").list();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding all banks finished");
        return clientList;
    }

    @Override
    Client findById(Integer clientId) {
        return super.findById(clientId);
    }

    @Override
    public Client findByName(String clientName) {
        return super.findByName(clientName);
    }
}
