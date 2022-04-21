package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.ActionDAO;
import home.intexsoft.bank_application.dao.OperationDAO;
import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.dto.action.ActionDtoComparator;
import home.intexsoft.bank_application.controller.operationController.dto.OperationDto;
import home.intexsoft.bank_application.mapper.ActionMapper;
import home.intexsoft.bank_application.mapper.BankAccountMapper;
import home.intexsoft.bank_application.mapper.OperationMapper;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    private static final Logger log = LoggerFactory.getLogger(OperationService.class);
    private final OperationDAO operationDAO;
    private final BankAccountService bankAccountService;
    private final ActionDAO actionDAO;
    private final OperationMapper operationMapper;
    private final ActionMapper actionMapper;
    private final BankAccountMapper bankAccountMapper;

    public List<ModelDto> createOperation(OperationDto operationDto) {
        log.debug("Method createOperation started");
        Operation operation = mapToOperation(operationDto);
        operationDAO.createOperation(operation);
        Operation addedOperation = null;
        try {
            operation.setStatus(Command.OperationStatus.IN_PROCESS);
            operationDAO.update(operation);
            bankAccountService.executeActionList(operation.getActions());
            operation.setStatus(Command.OperationStatus.SUCCESS);
            addedOperation = operationDAO.update(operation);
        } catch (Exception e) {
            operation.setStatus(Command.OperationStatus.FAILED);
            operationDAO.update(operation);
        }
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(operationMapper.fromOperation(addedOperation));
        log.debug("Method createOperation finished");
        return modelsDto;
    }

    private Operation mapToOperation(OperationDto operationDto) {
        Operation operation = operationMapper.fromOperationDto(operationDto);
        List<Action> actionList = operationDto.getActionsDto()
                .stream()
                .sorted(new ActionDtoComparator())
                .map(actionMapper::fromActionDto)
                .peek(action -> action.setOperation(operation))
                .collect(Collectors.toList());
        operation.setActions(actionList);
        return operation;
    }

    public List<ModelDto> printOperationsOfClient(String clientName) {
        log.debug("Method findOperationsOfClient started");
//        List<FindBankAccountsOfClientRequestDto> bankAccountsOfClient = bankAccountService.findBankAccountsOfClient(clientName);
//        List<BankAccount> bankAccounts = bankAccountsOfClient
//                .stream()
//                .map(bankAccountMapper::fromFindBankAccountsDto)
//                .collect(Collectors.toList());
//        List<List<Action>> allActions = new ArrayList<>();
//        bankAccounts.forEach(bankAccount -> allActions.add(actionDAO.findActionsByBankAccountId(bankAccount.getId())));
//        List<Action> actions = new ArrayList<>();
//        allActions.forEach(actions::addAll);
//        List<Operation> operations = new ArrayList<>();
//        actions.forEach(action -> operations.add(action.getOperation()));
//        return operations.stream()
//                .map(operationMapper::fromOperation)
//                .collect(Collectors.toList());
        return null;
    }
}