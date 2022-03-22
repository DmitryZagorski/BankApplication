package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteClientCommand.class);

    private ClientService clientService;

    @Autowired
    public DeleteClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

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
        getAttributes().put(DeleteClientCommand.Attribute.CLIENT_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        clientService.deleteClientByName(this.getAttributes().get(DeleteClientCommand.Attribute.CLIENT_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}