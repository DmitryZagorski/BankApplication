package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindOperationsOfClientCommand extends Command {


    private static final Logger log = LoggerFactory.getLogger(FindOperationsOfClientCommand.class);

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME("client name");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.FIND_OPERATIONS_OF_CLIENT);
        getAttributes().put(Attribute.CLIENT_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of finding of clients operations started");
        OperationService operationService = new OperationService();
        operationService.findOperationsOfClient(this.getAttributes().get(Attribute.CLIENT_NAME));
        log.debug("Executing of finding of clients operations finished");
    }

}
