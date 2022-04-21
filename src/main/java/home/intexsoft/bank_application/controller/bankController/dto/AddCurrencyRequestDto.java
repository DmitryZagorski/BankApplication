package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.command.AddCurrencyCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class AddCurrencyRequestDto extends CommandDto {

    private String currencyName;
    private String rate;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case AddCurrencyCommand.CURRENCY_NAME_ATTRIBUTE_TITLE:
                return this.currencyName;
            case AddCurrencyCommand.RATE_ATTRIBUTE_TITLE:
                return this.rate;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}