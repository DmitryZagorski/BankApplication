package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.dto.ActionDto;
import home.intexsoft.bank_application.dto.OperationDto;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSalaryPaymentCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddSalaryPaymentCommand.class);

    private OperationService operationService;
    private BankAccountService bankAccountService;

    @Autowired
    public AddSalaryPaymentCommand(OperationService operationService, BankAccountService bankAccountService) {
        this.operationService = operationService;
        this.bankAccountService = bankAccountService;
    }

    public enum Attribute implements CommandAttribute {

        EMPLOYEE_BANK_ACCOUNT_ID("employee bank account id"),
        EMPLOYER_BANK_ACCOUNT_ID("employer bank account id"),
        DUES_RECIPIENT_BANK_ACCOUNT_ID("dues recipient bank account id"),
        AMOUNT_OF_MONEY("amount of money");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_SALARY_PAYMENT);

        getAttributes().put(Attribute.EMPLOYEE_BANK_ACCOUNT_ID, null);
        getAttributes().put(Attribute.EMPLOYER_BANK_ACCOUNT_ID, null);
        getAttributes().put(Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");

        OperationDto operationDto = new OperationDto();

        ActionDto employerAction = createActionDto(
                this.getAttributes().get(Attribute.EMPLOYER_BANK_ACCOUNT_ID),
                AddSalaryPaymentCommand.ActionType.WITHDRAW,
                Double.parseDouble(this.getAttributes()
                        .get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)), 1);

        ActionDto employeeAction = createActionDto(
                this.getAttributes().get(Attribute.EMPLOYEE_BANK_ACCOUNT_ID),
                AddSalaryPaymentCommand.ActionType.ADDITION,
                Double.parseDouble(this.getAttributes()
                        .get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)) * 0.8, 2);

        ActionDto duesRecipientAction = createActionDto(
                this.getAttributes().get(Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID),
                AddSalaryPaymentCommand.ActionType.ADDITION,
                Double.parseDouble(this.getAttributes()
                        .get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)) * 0.2, 3);

        operationDto.setType(this.getName().getCommandName());
        operationDto.setStatus(Command.OperationStatus.CREATED);
        operationDto.getActionsDto().add(employerAction);
        operationDto.getActionsDto().add(employeeAction);
        operationDto.getActionsDto().add(duesRecipientAction);
        operationService.createOperation(operationDto);
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }

    private ActionDto createActionDto(String bankAccountId, ActionType actionType,
                                      Double amountOfMoney, Integer priority) {
        log.debug("Method 'createActionDto' started");
        ActionDto actionDto = new ActionDto();
        actionDto.setAmountOfMoney(amountOfMoney);
        actionDto.setBankAccountId(Integer.parseInt(bankAccountId));
        actionDto.setActionType(actionType);
        actionDto.setPriority(priority);
        log.debug("Method 'createActionDto' finished");
        return actionDto;
    }
}