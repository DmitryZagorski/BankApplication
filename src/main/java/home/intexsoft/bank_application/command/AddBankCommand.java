package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.service.BankService;
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
public class AddBankCommand extends Command {

    public static final String BANK_NAME_ATTRIBUTE_TITLE = "bank name";
    public static final String COMMISSION_FOR_INDIVIDUAL_ATTRIBUTE_TITLE = "commission for individual";
    public static final String COMMISSION_FOR_ENTITY_ATTRIBUTE_TITLE = "commission for entity";

    private static final Logger log = LoggerFactory.getLogger(AddBankCommand.class);

    @Autowired
    private BankService bankService;

    public enum Attribute implements CommandAttribute {

        BANK_NAME(BANK_NAME_ATTRIBUTE_TITLE),
        COMMISSION_FOR_INDIVIDUAL(COMMISSION_FOR_INDIVIDUAL_ATTRIBUTE_TITLE),
        COMMISSION_FOR_ENTITY(COMMISSION_FOR_ENTITY_ATTRIBUTE_TITLE);

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        @Override
        public AddBankCommand.Attribute getConstant(String attributeName) {
            return Arrays.stream(values())
                    .filter(attribute -> attribute.getAttributeName().equals(attributeName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Attribute name %s not exists", attributeName)));
        }
    }

    {
        setName(CommandType.ADD_BANK);

        getAttributes().put(Attribute.BANK_NAME, null);
        getAttributes().put(Attribute.COMMISSION_FOR_INDIVIDUAL, null);
        getAttributes().put(Attribute.COMMISSION_FOR_ENTITY, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        List<ModelDto> modelsDto = bankService.addBank(this.getAttributes().get(Attribute.BANK_NAME),
                this.getAttributes().get(Attribute.COMMISSION_FOR_INDIVIDUAL),
                this.getAttributes().get(Attribute.COMMISSION_FOR_ENTITY));
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }
}