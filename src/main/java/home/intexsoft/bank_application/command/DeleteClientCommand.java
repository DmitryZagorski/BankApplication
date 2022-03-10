package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteClientCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteClientCommand.class);

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
        setName(CommandType.DELETE_CLIENT);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding bank started");
        ClientService clientService = new ClientService();
        clientService.deleteClient(this.getAttributes().get(DeleteClientCommand.Attribute.CLIENT_NAME));
        log.debug("Executing of adding bank finished");
    }

}
