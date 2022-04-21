//package home.intexsoft.bank_application.service;
//
//import home.intexsoft.bank_application.command.Command;
//import home.intexsoft.bank_application.dao.BankAccountDAO;
//import home.intexsoft.bank_application.dto.actionDto.ActionDto;
//import home.intexsoft.bank_application.dto.operationDto.OperationDto;
//import home.intexsoft.bank_application.models.BankAccount;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//class OperationServiceTest {
//
//    @Test
//    void should_add_operation_and_actions_and_bank_accounts() {
//        // given
//
//
//        ActionDto senderActionDto = new ActionDto();
//        senderActionDto.setPriority(1);
//        senderActionDto.setActionType(Command.ActionType.WITHDRAW);
//        senderActionDto.setAmountOfMoney(15.0);
//        senderActionDto.setBankAccountId(1);
//
//        ActionDto recipientActionDto = new ActionDto();
//        recipientActionDto.setPriority(2);
//        recipientActionDto.setActionType(Command.ActionType.ADDITION);
//        recipientActionDto.setAmountOfMoney(15.0);
//        recipientActionDto.setBankAccountId(2);
//
//        OperationDto operationDto = new OperationDto();
//        operationDto.setStatus(Command.OperationStatus.CREATED);
//        operationDto.setType("money transfer");
//        operationDto.getActionsDto().add(senderActionDto);
//        operationDto.getActionsDto().add(recipientActionDto);
//
//        OperationService operationService = new OperationService();
//
//        BankAccountDAO bankAccountDAO = new BankAccountDAO();
//        BankAccount firstBankAccount = bankAccountDAO.findById(1);
//        BankAccount secondBankAccount = bankAccountDAO.findById(2);
//
//        Double firstBankAccountMoney = firstBankAccount.getAmountOfMoney();
//        Double secondBankAccountMoney = secondBankAccount.getAmountOfMoney();
//
//        // when
//        operationService.createOperation(operationDto);
//        BankAccount firstBankAccountAfterChange = bankAccountDAO.findById(1);
//        BankAccount secondBankAccountAfterChange = bankAccountDAO.findById(2);
//
//        Double firstBankAccountMoneyAfterChange = firstBankAccountAfterChange.getAmountOfMoney();
//        Double secondBankAccountMoneyAfterChange = secondBankAccountAfterChange.getAmountOfMoney();
//
//        // then
//        Assertions.assertEquals(firstBankAccountMoneyAfterChange, firstBankAccountMoney - 15);
//        Assertions.assertEquals(secondBankAccountMoneyAfterChange, secondBankAccountMoney + 15);
//    }
//}