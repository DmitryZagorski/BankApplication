package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAllClientsCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteAllClientsCommand.class);

    {
        setName(CommandType.DELETE_ALL_CLIENTS);
    }

    @Override
    public void execute() {
        log.debug("Executing of deleting all clients started");
        ClientService clientService = new ClientService();
        clientService.deleteAllClients();
        log.debug("Executing of deleting all clients finished");
    }

}
