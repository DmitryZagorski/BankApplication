package home.intexsoft.bank_application.controller.operationController.dto;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.dto.action.ActionDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OperationDto implements ModelDto {

    private Command.OperationStatus status;
    private String type;
    private List<ActionDto> actionsDto = new ArrayList<>();

}