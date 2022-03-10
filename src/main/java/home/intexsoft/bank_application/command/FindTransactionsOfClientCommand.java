package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import home.intexsoft.bank_application.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindTransactionsOfClientCommand extends Command {


    private static final Logger log = LoggerFactory.getLogger(FindTransactionsOfClientCommand.class);

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
        setName(CommandType.FIND_TRANSACTIONS_OF_CLIENT);

        getAttributes().put(FindTransactionsOfClientCommand.Attribute.CLIENT_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of finding of clients transaction started");
        ClientService clientService = new ClientService();
        clientService.findTransactionsOfClient(this.getAttributes().get(FindBankAccountsOfClientCommand.Attribute.CLIENT_NAME));
        log.debug("Executing of finding of clients transaction finished");
    }

}
