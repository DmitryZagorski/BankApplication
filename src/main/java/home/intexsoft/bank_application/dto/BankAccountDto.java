package home.intexsoft.bank_application.dto;

import lombok.Data;

@Data
public class BankAccountDto {

    private Integer id;
    private Integer currencyId;
    private Double amountOfMoney;
    private Integer clientId;

}
