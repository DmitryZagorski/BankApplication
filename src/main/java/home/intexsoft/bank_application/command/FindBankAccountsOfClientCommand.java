package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindBankAccountsOfClientCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(FindBankAccountsOfClientCommand.class);

    @Autowired
    private BankAccountService bankAccountService;

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
        setName(CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT);
        getAttributes().put(Attribute.CLIENT_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        bankAccountService.findBankAccountsOfClient(this.getAttributes()
                .get(FindBankAccountsOfClientCommand.Attribute.CLIENT_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}