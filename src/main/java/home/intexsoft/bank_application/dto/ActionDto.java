package home.intexsoft.bank_application.dto;

import home.intexsoft.bank_application.command.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActionDto {

    private Command.ActionType actionType;
    private Double amountOfMoney;
    private Integer bankAccountId;

}