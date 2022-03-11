package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();

    public Operation addDefaultOperation() {
        log.debug("Method addOperation started");
        Operation operation = new Operation();
        operation.setStatus(Command.OperationStatus.CREATED.getOperationStatusName());
        Operation createdOperation = operationDAO.createOperation(operation);
        log.debug("Method addOperation finished");
        return createdOperation;
    }

    public boolean checkIfOperationTypeExist(String operationTypeName) {
        return AddMoneyTransferCommand.ActionType.ADDITION.getOperationTypeName().equals(operationTypeName) ||
                AddMoneyTransferCommand.ActionType.WITHDRAW.getOperationTypeName().equals(operationTypeName);
    }

    public boolean checkIfOperationStatusExist(String operationStatus) {
        final boolean[] isExist = {false};
        Arrays.stream(Command.OperationStatus.values()).forEach(status -> {
            if (status.getOperationStatusName().equals(operationStatus)){
                isExist[0] = true;
            }
        });
        return isExist[0];
    }

    public void addOperation(Operation operation) {
        log.debug("Method addOperation started");

        log.debug("Method addOperation finished");
    }
}
