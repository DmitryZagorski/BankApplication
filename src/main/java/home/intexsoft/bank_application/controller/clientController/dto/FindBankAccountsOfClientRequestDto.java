package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.FindBankAccountsOfClientCommand;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class FindBankAccountsOfClientRequestDto extends CommandDto {

    private String clientName;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case FindBankAccountsOfClientCommand.CLIENT_NAME_ATTRIBUTE_TITLE:
                return this.clientName;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}