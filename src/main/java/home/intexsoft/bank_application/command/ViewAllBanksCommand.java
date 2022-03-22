package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewAllBanksCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(ViewAllBanksCommand.class);

    private BankService bankService;

    @Autowired
    public ViewAllBanksCommand(BankService bankService) {
        this.bankService = bankService;
    }

    {
        setName(CommandType.VIEW_ALL_BANKS);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        bankService.viewAllBanks();
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}