package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.AddMoneyTransferCommand;
import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();
    private BankAccountService bankAccountService = new BankAccountService();

    public void createDefaultOperation(Operation operation, String commandName) {
        log.debug("Method addOperation started");

        operation.setStatus(Command.OperationStatus.CREATED.getOperationStatusName());
        operation.setName(commandName);
        operationDAO.create(operation);


        operation.getActions().forEach(action -> bankAccountService.updateBankAccountWithMoney(action));

//        BankAccount senderBankAccount = bankAccountService.findBankAccountById(operation.getActions().get(0).getId());
//        BankAccount recipientBankAccount = bankAccountService.findBankAccountById(operation.getActions().get(1).getId());

//        bankAccountService.updateBankAccountWithMoney(senderBankAccount);

        log.debug("Method addOperation finished");
    }

    void updateOperation(Operation operation) {
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
