package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddClientCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommand.class);

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME("client name"),
        CLIENT_SURNAME("client surname"),
        CLIENT_STATUS_ID("client status id"),
        CURRENCY_ID("currency id"),
        BANK_ID("bank id"),
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
        setName(CommandType.ADD_CLIENT);

        getAttributes().put(AddClientCommand.Attribute.CLIENT_NAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_SURNAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_STATUS_ID, null);
        getAttributes().put(AddClientCommand.Attribute.CURRENCY_ID, null);
        getAttributes().put(AddClientCommand.Attribute.BANK_ID, null);
        getAttributes().put(AddClientCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding client started");
        ClientService clientService = new ClientService();
        clientService.addClient(this.getAttributes().get(AddClientCommand.Attribute.CLIENT_NAME),
                this.getAttributes().get(AddClientCommand.Attribute.CLIENT_SURNAME),
                this.getAttributes().get(AddClientCommand.Attribute.CLIENT_STATUS_ID),
                this.getAttributes().get(AddClientCommand.Attribute.CURRENCY_ID),
                this.getAttributes().get(AddClientCommand.Attribute.BANK_ID),
                this.getAttributes().get(AddClientCommand.Attribute.AMOUNT_OF_MONEY));
        log.debug("Executing of adding client finished");
    }

}
