package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddBankAccountCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddBankAccountCommand.class);

    public enum Attribute implements CommandAttribute {

        BANK_NAME("bank name"),
        CLIENT_NAME("client name"),
        CLIENT_SURNAME("client surname"),
        CLIENT_STATUS("client status"),
        CURRENCY_NAME("currency name"),
        AMOUNT_OF_MONEY("amount of money");


        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_BANK_ACCOUNT);

        getAttributes().put(AddBankAccountCommand.Attribute.BANK_NAME, null);
        getAttributes().put(AddBankAccountCommand.Attribute.CLIENT_NAME, null);
        getAttributes().put(AddBankAccountCommand.Attribute.CLIENT_SURNAME, null);
        getAttributes().put(AddBankAccountCommand.Attribute.CLIENT_STATUS, null);
        getAttributes().put(AddBankAccountCommand.Attribute.CURRENCY_NAME, null);
        getAttributes().put(AddBankAccountCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding bankAccount started");
        BankAccountService bankAccountService = new BankAccountService();
        bankAccountService.addBankAccount(this.getAttributes().get(AddBankAccountCommand.Attribute.BANK_NAME),
                this.getAttributes().get(AddBankAccountCommand.Attribute.CLIENT_NAME),
                this.getAttributes().get(AddBankAccountCommand.Attribute.CLIENT_SURNAME),
                this.getAttributes().get(AddBankAccountCommand.Attribute.CLIENT_STATUS),
                this.getAttributes().get(AddBankAccountCommand.Attribute.CURRENCY_NAME),
                this.getAttributes().get(AddBankAccountCommand.Attribute.AMOUNT_OF_MONEY));
        log.debug("Executing of adding bankAccount finished");
    }
}
