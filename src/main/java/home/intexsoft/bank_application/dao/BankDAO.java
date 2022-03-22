package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BankDAO extends DAO<Bank> {

    private static final Logger log = LoggerFactory.getLogger(BankDAO.class);

    @Override
    public void create(@NotNull final Bank bank) {
        log.debug("DAO method of creation new bank started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(bank);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during saving transaction" + e);
            session.getTransaction().rollback();
            session.close();
        }
        log.debug("DAO method of creation new bank finished");
    }

    @Override
    public Bank findById(@NotNull final Integer id) {
        log.debug("DAO method of finding bank by ID = '" + id + "' started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Bank result = session.get(Bank.class, id);
            return result != null ? result : new Bank();
        }
    }

    @Override
    public Bank findByName(@NotNull final String name) {
        log.debug("DAO method of finding bank by name '" + name + "' started");
        Bank bank = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> root = query.from(Bank.class);
            query.where(criteriaBuilder.equal(root.get("name"), name));
            Query<Bank> newQuery = session.createQuery(query);
            List<Bank> resultList = newQuery.getResultList();
            if (!resultList.isEmpty()) {
                bank = resultList.get(0);
            }
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding bank by name '" + name + "' started");
        return bank;
    }

    @Override
    public List<Bank> findAll() {
        log.debug("DAO method of finding all banks started");
        List<Bank> bankList;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Bank> cq = cb.createQuery(Bank.class);
            Root<Bank> rootEntry = cq.from(Bank.class);
            CriteriaQuery<Bank> all = cq.select(rootEntry);
            TypedQuery<Bank> allQuery = session.createQuery(all);
            bankList = allQuery.getResultList();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding all banks finished");
        return bankList;
    }

    @Override
    public void update(@NotNull final Bank bank) {
        log.debug("DAO method of updating bank '" + bank.getName() + "' started");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(bank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of updating bank '" + bank.getName() + "' finished");
    }

    @Override
    public void deleteByName(@NotNull final String bankName) {
        log.debug("DAO method of deleting bank by name '" + bankName + "'started");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> root = query.from(Bank.class);
            query.where(criteriaBuilder.equal(root.get("name"), bankName));
            Query<Bank> newQuery = session.createQuery(query);
            Bank foundBank = newQuery.getSingleResult();
            session.delete(foundBank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of deleting bank by name '" + bankName + "'started");
    }
}