package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.command.AddClientCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class AddClientRequestDto extends CommandDto {

    private String clientName;
    private String clientSurname;
    private String clientStatus;
    private String bankName;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case AddClientCommand.CLIENT_NAME_ATTRIBUTE_TITLE:
                return this.clientName;
            case AddClientCommand.CLIENT_SURNAME_ATTRIBUTE_TITLE:
                return this.clientSurname;
            case AddClientCommand.CLIENT_STATUS_ATTRIBUTE_TITLE:
                return this.clientStatus;
            case AddClientCommand.BANK_NAME_ATTRIBUTE_TITLE:
                return this.bankName;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}