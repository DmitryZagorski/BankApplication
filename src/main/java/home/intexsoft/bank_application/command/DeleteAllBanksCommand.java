package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAllBanksCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteAllBanksCommand.class);

    {
        setName(CommandType.DELETE_ALL_BANKS);
    }

    @Override
    public void execute() {
        log.debug("Executing of deleting all banks started");
        BankService bankService = new BankService();
        bankService.deleteAllBanks();
        log.debug("Executing of deleting all banks finished");
    }

}
