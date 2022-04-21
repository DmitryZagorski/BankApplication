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
public class DeleteClientCommand extends Command {

    public static final String CLIENT_NAME_ATTRIBUTE_TITLE = "client name";

    private static final Logger log = LoggerFactory.getLogger(DeleteClientCommand.class);

    @Autowired
    private ClientService clientService;

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
        setName(CommandType.DELETE_CLIENT);
        getAttributes().put(DeleteClientCommand.Attribute.CLIENT_NAME, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = clientService.deleteClientByName(this.getAttributes().get(Attribute.CLIENT_NAME));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}