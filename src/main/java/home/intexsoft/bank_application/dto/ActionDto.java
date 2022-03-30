package home.intexsoft.bank_application.dto;

import home.intexsoft.bank_application.command.Command;
import lombok.Data;

@Data
public class ActionDto {

    private Command.ActionType actionType;
    private Double amountOfMoney;
    private Integer bankAccountId;
    private Integer priority;

}