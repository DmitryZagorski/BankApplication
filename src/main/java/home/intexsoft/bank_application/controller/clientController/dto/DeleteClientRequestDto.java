package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.DeleteClientCommand;
import home.intexsoft.bank_application.controller.CommandDto;
import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class DeleteClientRequestDto extends CommandDto implements ModelDto {

    private String clientName;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case DeleteClientCommand.CLIENT_NAME_ATTRIBUTE_TITLE:
                return this.clientName;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}