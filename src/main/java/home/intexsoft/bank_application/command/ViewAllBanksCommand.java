package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ViewAllBanksCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(ViewAllBanksCommand.class);

    @Autowired
    private BankService bankService;

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