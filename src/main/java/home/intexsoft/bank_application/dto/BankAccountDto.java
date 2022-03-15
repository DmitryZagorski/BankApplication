package home.intexsoft.bank_application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountDto {

    private Integer id;
    private Integer currencyId;
    private Double amountOfMoney;
    private Integer clientId;

}
