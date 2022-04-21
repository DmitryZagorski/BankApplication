package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.service.ClientService;
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
public class FindClientsOfBankCommand extends Command {

    public static final String BANK_NAME_ATTRIBUTE_TITLE = "bank name";

    private static final Logger log = LoggerFactory.getLogger(FindClientsOfBankCommand.class);

    @Autowired
    private ClientService clientService;

    public enum Attribute implements CommandAttribute {

        BANK_NAME(BANK_NAME_ATTRIBUTE_TITLE);

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
        setName(CommandType.FIND_CLIENTS_OF_BANK);
        getAttributes().put(FindClientsOfBankCommand.Attribute.BANK_NAME, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto =
                clientService.findClientsOfBank(this.getAttributes().get(FindClientsOfBankCommand.Attribute.BANK_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}