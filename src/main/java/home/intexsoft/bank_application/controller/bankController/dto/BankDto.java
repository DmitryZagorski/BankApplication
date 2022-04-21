package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class BankDto implements ModelDto {

    private String name;
    private Double commissionForIndividual;
    private Double commissionForEntity;

}