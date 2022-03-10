package home.intexsoft.bank_application.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Transaction;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionDAO extends DAO<Transaction> {

    private static final Logger log = LoggerFactory.getLogger(TransactionDAO.class);
//    private final SessionFactory sessionFactory;
//
//    public TransactionDAO(@NotNull final SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }


}
