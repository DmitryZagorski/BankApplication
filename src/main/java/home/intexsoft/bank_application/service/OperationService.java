package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.ActionDAO;
import home.intexsoft.bank_application.dao.ClientDAO;
import home.intexsoft.bank_application.dao.HibernateUtil;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Client;
import home.intexsoft.bank_application.models.Operation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private BankAccountService bankAccountService = new BankAccountService();
    private ActionDAO actionDAO = new ActionDAO();

    public void createOperation(Operation operation) {
        log.debug("Method createOperation started");
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            operationDAO.create(operation, session);
            operation.setStatus(Command.OperationStatus.IN_PROCESS.getOperationStatusName());
            operationDAO.update(operation, session);
            operation.getActions().forEach(action -> {   // list in bank account
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

    public void findOperationsOfClient(String clientName) {
        log.debug("Method findOperationsOfClient started");
        Client clientByName = clientDAO.findByName(clientName);
        Integer clientId = clientByName.getId();
        List<BankAccount> bankAccountsOfClient = bankAccountService.findBankAccountsOfClient(clientName);
        bankAccountsOfClient.stream().distinct();
        List<List<Action>> allActions = new ArrayList<>();
        bankAccountsOfClient.forEach(bankAccount -> allActions.add(actionDAO.findActionsByBankAccountId(bankAccount.getId())));
        List<Action> actions = new ArrayList<>();
        allActions.forEach(actions::addAll);
        actions.stream().distinct();
        List<Operation> operations = new ArrayList<>();
        actions.forEach(action -> operations.add(action.getOperation()));
        operations.stream().distinct();
        operations.forEach(System.out::println);
        log.debug("Method findOperationsOfClient finished");
    }

}
