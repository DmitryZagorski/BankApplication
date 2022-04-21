package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.command.AddBankAccountCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class AddBankAccountRequestDto extends CommandDto {

    private String bankName;
    private String clientName;
    private String clientSurname;
    private String clientStatus;
    private String currencyName;
    private String amountOfMoney;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case AddBankAccountCommand.BANK_NAME_ATTRIBUTE_TITLE:
                return this.bankName;
            case AddBankAccountCommand.CLIENT_NAME_ATTRIBUTE_TITLE:
                return this.clientName;
            case AddBankAccountCommand.CLIENT_SURNAME_ATTRIBUTE_TITLE:
                return this.clientSurname;
            case AddBankAccountCommand.CLIENT_STATUS_ATTRIBUTE_TITLE:
                return this.clientStatus;
            case AddBankAccountCommand.CURRENCY_NAME_ATTRIBUTE_TITLE:
                return this.currencyName;
            case AddBankAccountCommand.AMOUNT_OF_MONEY_ATTRIBUTE_TITLE:
                return this.amountOfMoney;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}