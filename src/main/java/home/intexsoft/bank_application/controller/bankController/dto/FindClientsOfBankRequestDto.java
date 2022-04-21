package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.FindClientsOfBankCommand;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class FindClientsOfBankRequestDto extends CommandDto {

    private String bankName;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case FindClientsOfBankCommand.BANK_NAME_ATTRIBUTE_TITLE:
                return this.bankName;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}