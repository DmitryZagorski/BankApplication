package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCurrencyCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddClientCommand.class);

    private CurrencyService currencyService;

    @Autowired
    public AddCurrencyCommand(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public enum Attribute implements CommandAttribute {

        CURRENCY_NAME("currency name"),
        RATE("rate");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(CommandType.ADD_CURRENCY);

        getAttributes().put(AddCurrencyCommand.Attribute.CURRENCY_NAME, null);
        getAttributes().put(AddCurrencyCommand.Attribute.RATE, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        currencyService.addCurrency(this.getAttributes().get(AddCurrencyCommand.Attribute.CURRENCY_NAME),
                this.getAttributes().get(AddCurrencyCommand.Attribute.RATE));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }
}