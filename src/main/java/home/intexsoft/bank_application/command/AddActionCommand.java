package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.models.Operation;
import home.intexsoft.bank_application.service.ActionService;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddActionCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddActionCommand.class);

    public enum Attribute implements CommandAttribute {

        BANK_ACCOUNT_ID("bank account id"),
        AMOUNT_OF_MONEY("amount of money"),
        ACTION_TYPE("action type");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    public enum ActionType {

        WITHDRAW("withdraw"),
        ADDITION("addition");

        private String operationTypeName;

        ActionType(String operationTypeName) {
            this.operationTypeName = operationTypeName;
        }

        public String getOperationTypeName() {
            return operationTypeName;
        }

        public void setOperationTypeName(String operationTypeName) {
            this.operationTypeName = operationTypeName;
        }
    }

    {
        setName(CommandType.ADD_ACTION);
        getAttributes().put(AddActionCommand.Attribute.BANK_ACCOUNT_ID, null);
        getAttributes().put(AddActionCommand.Attribute.AMOUNT_OF_MONEY, null);
        getAttributes().put(AddActionCommand.Attribute.ACTION_TYPE, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding action started");
        OperationService operationService = new OperationService();
        Operation operation = operationService.addDefaultOperation();
        ActionService actionService = new ActionService();
        actionService.addAction(this.getAttributes().get(AddActionCommand.Attribute.BANK_ACCOUNT_ID),
                this.getAttributes().get(AddActionCommand.Attribute.AMOUNT_OF_MONEY),
                this.getAttributes().get(AddActionCommand.Attribute.ACTION_TYPE),
                operation);
        log.debug("Executing of adding action finished");
    }
}
