package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.BankAccount;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BankAccountDAO extends DAO<BankAccount> {

    private static final Logger log = LoggerFactory.getLogger(BankAccountDAO.class);

    @Override
    public void create(@NotNull final BankAccount bankAccount) {
        log.debug("DAO method of creation new bankAccount started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(bankAccount);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during creating bank account" + e);
            session.getTransaction().rollback();
            session.close();
        }
        log.debug("DAO method of creation new bankAccount finished");
    }

    public void updateBankAccount(BankAccount bankAccount) {
        log.debug("DAO method of bank account updating started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(bankAccount);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error("Error during updating bank account" + e);
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        log.debug("DAO method of bank account updating finished");
    }

    @Override
    public BankAccount findById(@NotNull final Integer id) {
        log.debug("DAO method of finding bankAccount by ID= '" + id + "' started");
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final BankAccount result = session.get(BankAccount.class, id);
            return result != null ? result : new BankAccount();
        }
    }

    public List<BankAccount> findBankAccountsOfClient(Integer clientId) {
        log.debug("DAO method of finding bankAccounts of client started");
        List<BankAccount> bankAccounts;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BankAccount> query = criteriaBuilder.createQuery(BankAccount.class);
            Root<BankAccount> root = query.from(BankAccount.class);
            query.where(criteriaBuilder.equal(root.get("client"), clientId));
            Query<BankAccount> newQuery = session.createQuery(query);
            bankAccounts = newQuery.getResultList();
            session.getTransaction().commit();
        }
        log.debug("DAO method of finding bankAccounts of client finished");
        return bankAccounts;
    }
}