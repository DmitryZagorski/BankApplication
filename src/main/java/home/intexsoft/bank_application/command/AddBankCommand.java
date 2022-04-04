package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddBankCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddBankCommand.class);

    public enum Attribute implements CommandAttribute {

        BANK_NAME("bank name"),
        COMMISSION_FOR_INDIVIDUAL("commission for individual"),
        COMMISSION_FOR_ENTITY("commission for entity");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_BANK);

        getAttributes().put(Attribute.BANK_NAME, null);
        getAttributes().put(Attribute.COMMISSION_FOR_INDIVIDUAL, null);
        getAttributes().put(Attribute.COMMISSION_FOR_ENTITY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        BankService bankService = new BankService();
        bankService.addBank(this.getAttributes().get(Attribute.BANK_NAME),
                this.getAttributes().get(Attribute.COMMISSION_FOR_INDIVIDUAL),
                this.getAttributes().get(Attribute.COMMISSION_FOR_ENTITY));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}