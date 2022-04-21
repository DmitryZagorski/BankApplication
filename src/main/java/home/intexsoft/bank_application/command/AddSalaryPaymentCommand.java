package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.dto.action.ActionDto;
import home.intexsoft.bank_application.controller.operationController.dto.OperationDto;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddSalaryPaymentCommand extends Command {

    public static final String EMPLOYEE_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE = "employee bank account id";
    public static final String EMPLOYER_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE = "employer bank account id";
    public static final String DUES_RECIPIENT_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE = "dues recipient bank account id";
    public static final String AMOUNT_OF_MONEY_ATTRIBUTE_TITLE = "amount of money";

    private static final Logger log = LoggerFactory.getLogger(AddSalaryPaymentCommand.class);

    @Autowired
    private OperationService operationService;

    public enum Attribute implements CommandAttribute {

        EMPLOYEE_BANK_ACCOUNT_ID(EMPLOYEE_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE),
        EMPLOYER_BANK_ACCOUNT_ID(EMPLOYER_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE),
        DUES_RECIPIENT_BANK_ACCOUNT_ID(DUES_RECIPIENT_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE),
        AMOUNT_OF_MONEY(AMOUNT_OF_MONEY_ATTRIBUTE_TITLE);

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        @Override
        public CommandAttribute getConstant(String attributeName) {
            return Arrays.stream(values())
                    .filter(attribute -> attribute.getAttributeName().equals(attributeName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Attribute name %s not exists", attributeName)));
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
    public List<ModelDto> execute() {
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
        List<ModelDto> modelsDto = operationService.createOperation(operationDto);
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
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