package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteBankCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteBankCommand.class);

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
        setName(CommandType.DELETE_BANK);
        getAttributes().put(DeleteBankCommand.Attribute.BANK_NAME, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of deleting bank started");
        BankService bankService = new BankService();
        bankService.deleteBankByName(this.getAttributes().get(DeleteBankCommand.Attribute.BANK_NAME));
        log.debug("Executing of deleting bank finished");
    }
}
