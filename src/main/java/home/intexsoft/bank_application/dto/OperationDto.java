package home.intexsoft.bank_application.dto;

import home.intexsoft.bank_application.command.Command;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class OperationDto {

    private Command.OperationStatus status;
    private String type;
    private List<ActionDto> actionsDto = new ArrayList<>();

}
