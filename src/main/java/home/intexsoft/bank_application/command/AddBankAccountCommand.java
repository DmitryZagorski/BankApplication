package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.service.BankAccountService;
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
public class AddBankAccountCommand extends Command {

    public static final String BANK_NAME_ATTRIBUTE_TITLE = "bank name";
    public static final String CLIENT_NAME_ATTRIBUTE_TITLE = "client name";
    public static final String CLIENT_SURNAME_ATTRIBUTE_TITLE = "client surname";
    public static final String CLIENT_STATUS_ATTRIBUTE_TITLE = "client status";
    public static final String CURRENCY_NAME_ATTRIBUTE_TITLE = "currency name";
    public static final String AMOUNT_OF_MONEY_ATTRIBUTE_TITLE = "amount of money";

    private static final Logger log = LoggerFactory.getLogger(AddBankAccountCommand.class);

    @Autowired
    private BankAccountService bankAccountService;

    public enum Attribute implements CommandAttribute {

        BANK_NAME(BANK_NAME_ATTRIBUTE_TITLE),
        CLIENT_NAME(CLIENT_NAME_ATTRIBUTE_TITLE),
        CLIENT_SURNAME(CLIENT_SURNAME_ATTRIBUTE_TITLE),
        CLIENT_STATUS(CLIENT_STATUS_ATTRIBUTE_TITLE),
        CURRENCY_NAME(CURRENCY_NAME_ATTRIBUTE_TITLE),
        AMOUNT_OF_MONEY(AMOUNT_OF_MONEY_ATTRIBUTE_TITLE);

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        @Override
        public AddBankAccountCommand.Attribute getConstant(String attributeName) {
            return Arrays.stream(values())
                    .filter(attribute -> attribute.getAttributeName().equals(attributeName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Attribute name %s not exists", attributeName)));
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
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = bankAccountService.addBankAccount(this.getAttributes().get(Attribute.BANK_NAME),
                this.getAttributes().get(Attribute.CLIENT_NAME),
                this.getAttributes().get(Attribute.CLIENT_SURNAME),
                this.getAttributes().get(Attribute.CLIENT_STATUS),
                this.getAttributes().get(Attribute.CURRENCY_NAME),
                this.getAttributes().get(Attribute.AMOUNT_OF_MONEY));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}