package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddOperationCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddOperationCommand.class);

    public enum Attribute implements CommandAttribute {

        BANK_ACCOUNT_ID("bank account id"),
        AMOUNT_OF_MONEY("amount of money");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    public enum OperationType{

        ADDITION("addition"),
        SUBTRACTION("subtraction");

        private String operationTypeName;

        OperationType(String operationTypeName) {
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
        setName(CommandType.ADD_OPERATION);
        getAttributes().put(AddOperationCommand.Attribute.BANK_ACCOUNT_ID, null);
        getAttributes().put(AddOperationCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding transaction started");
        OperationService operationService = new OperationService();
        operationService.addOperation(this.getAttributes().get(Attribute.BANK_ACCOUNT_ID),
                this.getAttributes().get(AddOperationCommand.Attribute.AMOUNT_OF_MONEY));
        log.debug("Executing of adding transaction finished");
    }

}
