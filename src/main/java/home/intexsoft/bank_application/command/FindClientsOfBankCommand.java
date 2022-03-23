package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindClientsOfBankCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(FindClientsOfBankCommand.class);

    @Autowired
    private ClientService clientService;

    public enum Attribute implements CommandAttribute {

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
        setName(CommandType.FIND_CLIENTS_OF_BANK);
        getAttributes().put(FindClientsOfBankCommand.Attribute.BANK_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        clientService.findClientsOfBank(this.getAttributes().get(FindClientsOfBankCommand.Attribute.BANK_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}