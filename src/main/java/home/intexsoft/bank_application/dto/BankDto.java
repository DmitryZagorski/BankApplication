package home.intexsoft.bank_application.dto;

import lombok.Data;

@Data
public class BankDto {

    private String name;
    private Double commissionForIndividual;
    private Double commissionForEntity;
}