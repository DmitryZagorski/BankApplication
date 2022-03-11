package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.dao.ActionDAO;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(ActionService.class);
    private ActionDAO actionDAO = new ActionDAO();
    private BankAccountService bankAccountService = new BankAccountService();

    public Action createActionMoneyTransfer(
            String bankAccountId, String amountOfMoney, String actionType, Operation operation) {
        log.debug("Method addAction started");
        Action action = new Action();
        BankAccount bankAccountById = bankAccountService.findBankAccountById(Integer.valueOf(bankAccountId));
        action.setBankAccount(bankAccountById);
        action.setAmountOfMoney(Double.valueOf(amountOfMoney));
        action.setActionType(actionType);
        actionDAO.createAction(action);
        log.debug("Method addAction finished");
        return action;
    }

    public boolean checkIfActionTypeExist(String operationTypeName) {
        return AddMoneyTransferCommand.ActionType.ADDITION.getOperationTypeName().equals(operationTypeName) ||
                AddMoneyTransferCommand.ActionType.WITHDRAW.getOperationTypeName().equals(operationTypeName);
    }

    public void addAction(String bankAccountId, String amountOfMoney, String actionType, Operation operation) {
        log.debug("Method addAction started");
        Action action = new Action();
        BankAccount bankAccountById = bankAccountService.findBankAccountById(Integer.valueOf(bankAccountId));
        action.setBankAccount(bankAccountById);
        action.setAmountOfMoney(Double.valueOf(amountOfMoney));
        action.setActionType(actionType);
        action.setOperation(operation);
        actionDAO.createAction(action);
        log.debug("Method addAction finished");
    }

}