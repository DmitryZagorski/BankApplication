package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.Operation;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMoneyTransferCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddMoneyTransferCommand.class);

    public enum Attribute implements CommandAttribute {

        SENDER_BANK_ACCOUNT_ID("sender bank account id"),
        RECIPIENT_BANK_ACCOUNT_ID("recipient bank account id"),
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
        setName(Command.CommandType.ADD_MONEY_TRANSFER);
        getAttributes().put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");

        OperationService operationService = new OperationService();
        BankAccountService bankAccountService = new BankAccountService();
        Operation operation = new Operation();       // new method !!!!!!!!!!!!!!!!!

        BankAccount bankAccount = bankAccountService.findBankAccountById(Integer.valueOf(
                this.getAttributes().get(Attribute.SENDER_BANK_ACCOUNT_ID)));
        Action senderAction = createAndSetAction(bankAccount, ActionType.WITHDRAW, operation);
        bankAccount = bankAccountService.findBankAccountById(Integer.valueOf(
                this.getAttributes().get(Attribute.RECIPIENT_BANK_ACCOUNT_ID)));
        Action recipientAction = createAndSetAction(bankAccount, ActionType.ADDITION, operation);

        operation.setName(this.getName().getCommandName());
        operation.setStatus(Command.OperationStatus.CREATED.getOperationStatusName());
        operation.getActions().add(senderAction);
        operation.getActions().add(recipientAction);
        operationService.createOperation(operation);

        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }

    private Action createAndSetAction(BankAccount bankAccount, ActionType actionType, Operation operation) {
        log.debug("Method 'createAndSetAction' started");
        Action action = new Action();
        action.setOperation(operation);
        action.setAmountOfMoney(Double.valueOf(this.getAttributes().get(Attribute.AMOUNT_OF_MONEY)));
        action.setBankAccount(bankAccount);
        action.setActionType(actionType.getOperationTypeName());
        log.debug("Method 'createAndSetAction' finished");
        return action;
    }
}