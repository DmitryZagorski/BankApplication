package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();
    private BankAccountService bankAccountService = new BankAccountService();

    public void createDefaultOperation(Operation operation) throws SQLException {
        log.debug("Method addOperation started");
        operationDAO.create(operation);
        try {
            operation.setStatus(Command.OperationStatus.IN_PROCESS.getOperationStatusName());
            operationDAO.update(operation);
            operation.getActions().forEach(action -> {
                try {
                    bankAccountService.updateBankAccountWithMoney(action);
                } catch (SQLException e) {
                    log.error("Error during updating bank account");
                }
            });
        } catch (Exception e) {
            throw new SQLException("Error during creating operation, updating bank account for operation actions");
        }
        log.debug("Method addOperation finished");
    }

    public void updateOperation(Operation operation) {
        log.debug("Method updateOperation started");
        operationDAO.update(operation);
        log.debug("Method updateOperation finished");
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
