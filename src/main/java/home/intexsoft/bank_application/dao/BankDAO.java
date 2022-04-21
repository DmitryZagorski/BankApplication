package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BankDAO {

    private static final Logger log = LoggerFactory.getLogger(BankDAO.class);

    private final SessionFactory sessionFactory;

    public Bank createBank(@NotNull Bank bank) {
        log.debug("DAO method of creation new bank started");
        final Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(bank);
            session.getTransaction().commit();
            session.close();
            log.debug("DAO method of creation new bank successfully finished");
            return bank;
        } catch (Exception e) {
            log.error("Error during saving transaction" + e);
            session.getTransaction().rollback();
            session.close();
            log.debug("DAO method of creation new bank finished with error!!!");
            return bank;
        }
    }

    public Bank findById(@NotNull final Integer id) {
        log.debug("DAO method of finding bank by ID = '" + id + "' started");
        try (final Session session = sessionFactory.openSession()) {
            final Bank result = session.get(Bank.class, id);
            return result != null ? result : new Bank();
        }
    }

    public Bank findByName(@NotNull final String name) {
        log.debug("DAO method of finding bank by name '" + name + "' started");
        Bank bank = null;
        try (final Session session = sessionFactory.openSession()) {
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

    public List<Bank> findAll() {
        log.debug("DAO method of finding all banks started");
        List<Bank> bankList;
        try (final Session session = sessionFactory.openSession()) {
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

    public void update(@NotNull final Bank bank) {
        log.debug("DAO method of updating bank '" + bank.getName() + "' started");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(bank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of updating bank '" + bank.getName() + "' finished");
    }

    public Bank deleteByName(@NotNull final String bankName) {
        log.debug("DAO method of deleting bank by name '" + bankName + "'started");
        Bank foundBank = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> root = query.from(Bank.class);
            query.where(criteriaBuilder.equal(root.get("name"), bankName));
            Query<Bank> newQuery = session.createQuery(query);
            foundBank = newQuery.getSingleResult();
            session.delete(foundBank);
            session.getTransaction().commit();
        }
        log.debug("DAO method of deleting bank by name '" + bankName + "'started");
        return foundBank;
    }
}