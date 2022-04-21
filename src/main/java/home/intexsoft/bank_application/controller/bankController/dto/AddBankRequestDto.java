package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.command.AddBankCommand;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.controller.CommandDto;
import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class AddBankRequestDto extends CommandDto implements ModelDto {

    private String name;
    private Double commissionForIndividual;
    private Double commissionForEntity;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case AddBankCommand.BANK_NAME_ATTRIBUTE_TITLE:
                return this.name;
            case AddBankCommand.COMMISSION_FOR_INDIVIDUAL_ATTRIBUTE_TITLE:
                return this.commissionForIndividual.toString();
            case AddBankCommand.COMMISSION_FOR_ENTITY_ATTRIBUTE_TITLE:
                return this.commissionForEntity.toString();
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}