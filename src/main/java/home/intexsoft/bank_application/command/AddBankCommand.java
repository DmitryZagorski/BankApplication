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
    public void execute(Command command) {
        log.info("Executing of adding bank started");
        BankService bankService = new BankService();
        bankService.addBank(command.getAttributes().get(Attribute.BANK_NAME),
                command.getAttributes().get(Attribute.COMMISSION_FOR_INDIVIDUAL),
                command.getAttributes().get(Attribute.COMMISSION_FOR_ENTITY));
        System.out.println("Executing of adding bank finished");
    }
}
