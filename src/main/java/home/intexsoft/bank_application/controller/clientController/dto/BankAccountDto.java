package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class BankAccountDto implements ModelDto {

    private Integer id;
    private Double amountOfMoney;
    private String clientName;
    private String clientSurname;
    private String currencyName;

}