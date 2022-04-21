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
public class FindBankAccountsOfClientCommand extends Command {

    public static final String CLIENT_NAME_ATTRIBUTE_TITLE = "client name";

    private static final Logger log = LoggerFactory.getLogger(FindBankAccountsOfClientCommand.class);

    @Autowired
    private BankAccountService bankAccountService;

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME(CLIENT_NAME_ATTRIBUTE_TITLE);

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
        setName(CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT);
        getAttributes().put(Attribute.CLIENT_NAME, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = bankAccountService.findBankAccountsOfClient(this.getAttributes()
                .get(Attribute.CLIENT_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}