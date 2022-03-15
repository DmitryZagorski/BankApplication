package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.dto.ActionDto;
import home.intexsoft.bank_application.dto.OperationDto;
import home.intexsoft.bank_application.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMoneyTransferCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddMoneyTransferCommand.class);

    public enum Attribute implements CommandAttribute {

        SENDER_BANK_ACCOUNT_ID("sender bank account id"),
        RECIPIENT_BANK_ACCOUNT_ID("recipient bank account id"),
        AMOUNT_OF_MONEY("amount of money");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    {
        setName(Command.CommandType.ADD_MONEY_TRANSFER);
        getAttributes().put(AddMoneyTransferCommand.Attribute.SENDER_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.RECIPIENT_BANK_ACCOUNT_ID, null);
        getAttributes().put(AddMoneyTransferCommand.Attribute.AMOUNT_OF_MONEY, null);
    }

    @Override
    public void execute() {
        log.debug("Executing of '" + this.getName().getCommandName() + "' started");
        OperationService operationService = new OperationService();
        OperationDto operationDto = getNewOperationDto();
        operationService.createOperationDto(operationDto);
        log.debug("Executing of '" + this.getName().getCommandName() + "' finished");
    }

    private OperationDto getNewOperationDto() {

        OperationDto operationDto = new OperationDto();
        operationDto.setType(this.getName().getCommandName());
        operationDto.setStatus(OperationStatus.CREATED);

        ActionDto senderActionDto = createAndSetAction(ActionType.WITHDRAW,
                this.getAttributes().get(Attribute.SENDER_BANK_ACCOUNT_ID));
        ActionDto recipientActionDto = createAndSetAction(ActionType.ADDITION,
                this.getAttributes().get(Attribute.RECIPIENT_BANK_ACCOUNT_ID));

        operationDto.getActionsDto().add(senderActionDto);
        operationDto.getActionsDto().add(recipientActionDto);

        return operationDto;
    }

    private ActionDto createAndSetAction(ActionType actionType, String bankAccountId) {
        log.debug("Method 'createAndSetAction' started");
        ActionDto actionDto = new ActionDto();
        actionDto.setActionType(actionType);
        actionDto.setAmountOfMoney(Double.valueOf(this.getAttributes().get(Attribute.AMOUNT_OF_MONEY)));
        actionDto.setBankAccountId(Integer.parseInt(bankAccountId));
        log.debug("Method 'createAndSetAction' finished");
        return actionDto;
    }
}