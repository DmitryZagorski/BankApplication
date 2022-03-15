package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.ActionDAO;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.dto.*;
import home.intexsoft.bank_application.mapper.ActionMapperImpl;
import home.intexsoft.bank_application.mapper.OperationMapperImpl;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private OperationDAO operationDAO = new OperationDAO();
    //    private ClientDAO clientDAO = new ClientDAO();
    private BankAccountService bankAccountService = new BankAccountService();
    private ActionDAO actionDAO = new ActionDAO();

//    public void createOperation(Operation operation) {
//        log.debug("Method createOperation started");
//        try {
//            operationDAO.createOperation(operation);
//            operation.setStatus(Command.OperationStatus.IN_PROCESS);
//            operationDAO.update(operation);
//            bankAccountService.executeActionList(operation.getActions());
//            operation.setStatus(Command.OperationStatus.SUCCESS);
//            operationDAO.update(operation);
//        } catch (Exception e) {
//            operation.setStatus(Command.OperationStatus.FAILED);
//            operationDAO.update(operation);
//        }
//        log.debug("Method createOperation finished");
//    }

    public void createOperationDto(OperationDto operationDto) {
        log.debug("Method createOperation started");
        Operation operation = mapToOperation(operationDto);
        try {
            operationDAO.createOperation(operation);
            operation.setStatus(Command.OperationStatus.IN_PROCESS);
            operationDAO.update(operation);
            bankAccountService.executeActionList(operation.getActions());
            operation.setStatus(Command.OperationStatus.SUCCESS);
            operationDAO.update(operation);
        } catch (Exception e) {
            operation.setStatus(Command.OperationStatus.FAILED);
            operationDAO.update(operation);
        }
        log.debug("Method createOperation finished");
    }

    private Operation mapToOperation(OperationDto operationDto) {
        OperationMapperImpl operationMapper = new OperationMapperImpl();
        Operation operation = operationMapper.fromOperationDto(operationDto);

        ActionMapperImpl actionMapper = new ActionMapperImpl();

        List<Action> actionList = operationDto.getActionsDto()
                .stream()
                .map(actionMapper::fromActionDto)
                .peek(action -> action.setOperation(operation))
                .collect(Collectors.toList());
        operation.setActions(actionList);
        return operation;
    }


    public void findOperationsOfClient(String clientName) {
        log.debug("Method findOperationsOfClient started");
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
