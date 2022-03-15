package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddSalaryPaymentCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddSalaryPaymentCommand.class);

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

        OperationService operationService = new OperationService();
        BankAccountService bankAccountService = new BankAccountService();
        Operation operation = new Operation();

        BankAccount bankAccount = bankAccountService.findBankAccountById(Integer.valueOf(
                this.getAttributes().get(Attribute.EMPLOYER_BANK_ACCOUNT_ID)));
        Action employerAction = createAndSetAction(bankAccount, AddSalaryPaymentCommand.ActionType.WITHDRAW, operation,
                Double.parseDouble(this.getAttributes().get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)));

        bankAccount = bankAccountService.findBankAccountById(Integer.valueOf(
                this.getAttributes().get(Attribute.EMPLOYEE_BANK_ACCOUNT_ID)));
        Action employeeAction = createAndSetAction(bankAccount, AddSalaryPaymentCommand.ActionType.ADDITION, operation,
                Double.parseDouble(
                        this.getAttributes().get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)) * 0.8);

        bankAccount = bankAccountService.findBankAccountById(Integer.valueOf(
                this.getAttributes().get(Attribute.DUES_RECIPIENT_BANK_ACCOUNT_ID)));
        Action duesRecipientAction = createAndSetAction(bankAccount, AddSalaryPaymentCommand.ActionType.ADDITION, operation,
                Double.parseDouble(
                        this.getAttributes().get(AddSalaryPaymentCommand.Attribute.AMOUNT_OF_MONEY)) * 0.2);

        operation.setType(this.getName().getCommandName());
        operation.setStatus(Command.OperationStatus.CREATED);
        operation.getActions().add(employerAction);
        operation.getActions().add(employeeAction);
        operation.getActions().add(duesRecipientAction);
    //    operationService.createOperationDto(operation);
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }

    private Action createAndSetAction(BankAccount bankAccount, AddSalaryPaymentCommand.ActionType actionType,
                                      Operation operation, Double amountOfMoney) {
        log.debug("Method 'createAndSetAction' started");
        Action action = new Action();
        action.setOperation(operation);
        action.setAmountOfMoney(amountOfMoney);
        action.setBankAccount(bankAccount);
        action.setActionType(actionType);
        log.debug("Method 'createAndSetAction' finished");
        return action;
    }
}
