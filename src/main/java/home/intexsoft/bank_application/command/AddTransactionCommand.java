package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddTransactionCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddTransactionCommand.class);

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME("client name"),
        CLIENT_SURNAME("client surname"),
        SENDER_BANK_ACCOUNT_ID("sender bank account id"),
        RECIPIENT_BANK_ACCOUNT_ID("recipient bank account id"),
        CURRENCY_NAME("currency name"),
        AMOUNT_OF_MONEY("amount of money"),
        CREATION_DATE("creation date");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_TRANSACTION);

        getAttributes().put(AddTransactionCommand.Attribute.CLIENT_NAME, null);
        getAttributes().put(AddTransactionCommand.Attribute.CLIENT_SURNAME, null);
        getAttributes().put(AddTransactionCommand.Attribute.SENDER_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddTransactionCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddTransactionCommand.Attribute.CURRENCY_NAME, null);
        getAttributes().put(AddTransactionCommand.Attribute.AMOUNT_OF_MONEY, null);
        getAttributes().put(AddTransactionCommand.Attribute.CREATION_DATE, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of adding transaction started");
        TransactionService transactionService = new TransactionService();
        transactionService.addTransaction(this.getAttributes().get(AddTransactionCommand.Attribute.CLIENT_NAME),
                this.getAttributes().get(AddTransactionCommand.Attribute.CLIENT_SURNAME),
                this.getAttributes().get(AddTransactionCommand.Attribute.SENDER_BANK_ACCOUNT_ID),
                this.getAttributes().get(AddTransactionCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID),
                this.getAttributes().get(AddTransactionCommand.Attribute.CURRENCY_NAME),
                this.getAttributes().get(AddTransactionCommand.Attribute.AMOUNT_OF_MONEY),
                this.getAttributes().get(AddTransactionCommand.Attribute.CREATION_DATE));
        log.debug("Executing of adding transaction finished");
    }

}
