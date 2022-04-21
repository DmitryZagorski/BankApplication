package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class CurrencyDto implements ModelDto {

    private String currencyName;
    private String rate;
}
