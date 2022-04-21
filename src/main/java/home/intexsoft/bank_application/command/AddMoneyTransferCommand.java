package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.dto.action.ActionDto;
import home.intexsoft.bank_application.controller.operationController.dto.OperationDto;
import home.intexsoft.bank_application.service.OperationService;
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
public class AddMoneyTransferCommand extends Command {

    public static final String SENDER_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE = "sender bank account id";
    public static final String RECIPIENT_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE = "recipient bank account id";
    public static final String AMOUNT_OF_MONEY_ATTRIBUTE_TITLE = "amount of money";

    private static final Logger log = LoggerFactory.getLogger(AddMoneyTransferCommand.class);

    @Autowired
    private OperationService operationService;

    public enum Attribute implements CommandAttribute {

        SENDER_BANK_ACCOUNT_ID(SENDER_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE),
        RECIPIENT_BANK_ACCOUNT_ID(RECIPIENT_BANK_ACCOUNT_ID_ATTRIBUTE_TITLE),
        AMOUNT_OF_MONEY(AMOUNT_OF_MONEY_ATTRIBUTE_TITLE);

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
        setName(Command.CommandType.ADD_MONEY_TRANSFER);
        getAttributes().put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public List<ModelDto> execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        OperationDto operationDto = getNewOperationDto();
        List<ModelDto> modelsDto = operationService.createOperation(operationDto);
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
        return modelsDto;
    }

    private OperationDto getNewOperationDto() {

        OperationDto operationDto = new OperationDto();
        operationDto.setType(this.getName().getCommandName());
        operationDto.setStatus(OperationStatus.CREATED);

        ActionDto senderActionDto = createActionDto(ActionType.WITHDRAW,
                this.getAttributes().get(Attribute.SENDER_BANK_ACCOUNT_ID), 2);
        ActionDto recipientActionDto = createActionDto(ActionType.ADDITION,
                this.getAttributes().get(Attribute.RECIPIENT_BANK_ACCOUNT_ID), 1);

        operationDto.getActionsDto().add(senderActionDto);
        operationDto.getActionsDto().add(recipientActionDto);

        return operationDto;
    }

    private ActionDto createActionDto(ActionType actionType, String bankAccountId, Integer priority) {
        log.debug("Method 'createAndSetAction' started");
        ActionDto actionDto = new ActionDto();
        actionDto.setActionType(actionType);
        actionDto.setAmountOfMoney(Double.valueOf(this.getAttributes().get(Attribute.AMOUNT_OF_MONEY)));
        actionDto.setBankAccountId(Integer.parseInt(bankAccountId));
        actionDto.setPriority(priority);
        log.debug("Method 'createAndSetAction' finished");
        return actionDto;
    }
}