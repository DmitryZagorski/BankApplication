package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddOperationCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddOperationCommand.class);

    public enum Attribute implements CommandAttribute {

        STATUS("status");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_OPERATION);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding operation started");
        OperationService operationService = new OperationService();
        operationService.addDefaultOperation();
        log.debug("Executing of adding operation finished");
    }


}
