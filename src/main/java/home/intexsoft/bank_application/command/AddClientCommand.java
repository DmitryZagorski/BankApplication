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
public class AddClientCommand extends Command {

    public static final String CLIENT_NAME_ATTRIBUTE_TITLE = "client name";
    public static final String CLIENT_SURNAME_ATTRIBUTE_TITLE = "client surname";
    public static final String CLIENT_STATUS_ATTRIBUTE_TITLE = "client status";
    public static final String BANK_NAME_ATTRIBUTE_TITLE = "bank name";

    private static final Logger log = LoggerFactory.getLogger(AddClientCommand.class);

    @Autowired
    private ClientService clientService;

    public enum Attribute implements CommandAttribute {

        CLIENT_NAME(CLIENT_NAME_ATTRIBUTE_TITLE),
        CLIENT_SURNAME(CLIENT_SURNAME_ATTRIBUTE_TITLE),
        CLIENT_STATUS(CLIENT_STATUS_ATTRIBUTE_TITLE),
        BANK_NAME(BANK_NAME_ATTRIBUTE_TITLE);

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        @Override
        public AddClientCommand.Attribute getConstant(String attributeName) {
            return Arrays.stream(values())
                    .filter(attribute -> attribute.getAttributeName().equals(attributeName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Attribute name %s not exists", attributeName)));
        }
    }

    {
        setName(CommandType.ADD_CLIENT);

        getAttributes().put(AddClientCommand.Attribute.CLIENT_NAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_SURNAME, null);
        getAttributes().put(AddClientCommand.Attribute.CLIENT_STATUS, null);
        getAttributes().put(Attribute.BANK_NAME, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = clientService.addClient(this.getAttributes().get(Attribute.CLIENT_NAME),
                this.getAttributes().get(Attribute.CLIENT_SURNAME),
                this.getAttributes().get(Attribute.CLIENT_STATUS),
                this.getAttributes().get(Attribute.BANK_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}