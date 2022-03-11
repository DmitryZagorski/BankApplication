package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.Operation;
import home.intexsoft.bank_application.service.ActionService;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMoneyTransferCommand extends AddActionCommand {

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
        setName(CommandType.ADD_MONEY_TRANSFER);
        getAttributes().put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of money transfer started");
        OperationService operationService = new OperationService();
        Operation operation = new Operation();
        ActionService actionService = new ActionService();
        Action actionMoneyTransferOfSender = actionService.createActionMoneyTransfer(
                this.getAttributes().get(Attribute.SENDER_BANK_ACCOUNT_ID),
                this.getAttributes().get(Attribute.AMOUNT_OF_MONEY),
                ActionType.WITHDRAW.getOperationTypeName(), operation);
        Action actionMoneyTransferOfRecipient = actionService.createActionMoneyTransfer(
                this.getAttributes().get(Attribute.RECIPIENT_BANK_ACCOUNT_ID),
                this.getAttributes().get(Attribute.AMOUNT_OF_MONEY),
                ActionType.ADDITION.getOperationTypeName(), operation);
        operation.getActions().add(actionMoneyTransferOfSender);
        operation.getActions().add(actionMoneyTransferOfRecipient);
        operationService.addOperation(operation);

        log.debug("Executing of adding money transfer finished");
    }
}