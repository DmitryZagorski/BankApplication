package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddCurrencyCommand extends Command {

    public static final String CURRENCY_NAME_ATTRIBUTE_TITLE = "currency name";
    public static final String RATE_ATTRIBUTE_TITLE = "rate";

    private static final Logger log = LoggerFactory.getLogger(AddClientCommand.class);

    private final CurrencyService currencyService;

    @Autowired
    public AddCurrencyCommand(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public enum Attribute implements CommandAttribute {

        CURRENCY_NAME(CURRENCY_NAME_ATTRIBUTE_TITLE),
        RATE(RATE_ATTRIBUTE_TITLE);

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        @Override
        public CommandAttribute getConstant(String attributeName) {
            return Arrays.stream(values())
                    .filter(attribute -> attribute.getAttributeName().equals(attributeName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Attribute name %s not exists", attributeName)));
        }
    }

    {
        setName(CommandType.ADD_CURRENCY);

        getAttributes().put(AddCurrencyCommand.Attribute.CURRENCY_NAME, null);
        getAttributes().put(AddCurrencyCommand.Attribute.RATE, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = currencyService.addCurrency(this.getAttributes().get(Attribute.CURRENCY_NAME),
                this.getAttributes().get(Attribute.RATE));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}