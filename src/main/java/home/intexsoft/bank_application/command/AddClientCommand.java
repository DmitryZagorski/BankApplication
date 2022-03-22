package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddClientCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommand.class);

    private ClientService clientService;

    @Autowired
    public AddClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME("client name"),
        CLIENT_SURNAME("client surname"),
        CLIENT_STATUS("client status"),
        BANK_NAME("bank name");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_CLIENT);

        getAttributes().put(AddClientCommand.Attribute.CLIENT_NAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_SURNAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_STATUS, null);
        getAttributes().put(Attribute.BANK_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        clientService.addClient(this.getAttributes().get(AddClientCommand.Attribute.CLIENT_NAME),
                this.getAttributes().get(AddClientCommand.Attribute.CLIENT_SURNAME),
                this.getAttributes().get(AddClientCommand.Attribute.CLIENT_STATUS),
                this.getAttributes().get(Attribute.BANK_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}