package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.HibernateUtil;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();
    private BankAccountService bankAccountService = new BankAccountService();

    public void createOperation(Operation operation) {
        log.debug("Method createOperation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            operationDAO.create(operation, session);
            operation.setStatus(Command.OperationStatus.IN_PROCESS.getOperationStatusName());
            operationDAO.update(operation, session);
            operation.getActions().forEach(action -> {
                try {
                    bankAccountService.updateBankAccountWithMoney(action, session);
                } catch (SQLException e) {
                    log.error("Error during updating bank account");
                }
            });
            operation.setStatus(Command.OperationStatus.SUCCESS.getOperationStatusName());
            operationDAO.update(operation, session);
            session.getTransaction().commit();
            session.close();
        } catch (SQLException e) {
            session.getTransaction().rollback();
            operation.setStatus(Command.OperationStatus.FAILED.getOperationStatusName());
            operationDAO.update(operation, session);
            session.close();
        }
        log.debug("Method createOperation finished");
    }

    public boolean checkIfOperationTypeExist(String operationTypeName) {
        return AddMoneyTransferCommand.ActionType.ADDITION.getOperationTypeName().equals(operationTypeName) ||
                AddMoneyTransferCommand.ActionType.WITHDRAW.getOperationTypeName().equals(operationTypeName);
    }

    public boolean checkIfOperationStatusExist(String operationStatus) {
        final boolean[] isExist = {false};
        Arrays.stream(Command.OperationStatus.values()).forEach(status -> {
            if (status.getOperationStatusName().equals(operationStatus)) {
                isExist[0] = true;
            }
        });
        return isExist[0];
    }
}
