package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewAllClientsCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(ViewAllClientsCommand.class);

    {
        setName(CommandType.VIEW_ALL_CLIENTS);
    }

    @Override
    public void execute() {
        log.debug("Executing of viewing all clients started");
        ClientService clientService = new ClientService();
        clientService.viewAllClients();
        log.debug("Executing of viewing all clients finished");
    }

}
