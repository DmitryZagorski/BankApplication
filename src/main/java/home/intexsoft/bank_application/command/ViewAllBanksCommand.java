package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewAllBanksCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(ViewAllBanksCommand.class);

    {
        setName(CommandType.VIEW_ALL_BANKS);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        BankService bankService = new BankService();
        bankService.viewAllBanks();
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}