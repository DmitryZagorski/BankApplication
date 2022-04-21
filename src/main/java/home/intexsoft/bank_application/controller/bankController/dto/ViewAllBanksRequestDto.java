package home.intexsoft.bank_application.controller.bankController.dto;

import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.command.ViewAllBanksCommand;
import home.intexsoft.bank_application.controller.CommandDto;
import lombok.Data;

@Data
public class ViewAllBanksRequestDto extends CommandDto {

    private String name;
    private Double commissionForIndividual;
    private Double commissionForEntity;

    @Override
    public String getByAttributeName(CommandAttribute commandAttribute) {
        switch (commandAttribute.getAttributeName()) {
            case ViewAllBanksCommand.BANK_NAME_ATTRIBUTE_TITLE:
                return this.name;
            case ViewAllBanksCommand.COMMISSION_FOR_INDIVIDUAL_ATTRIBUTE_TITLE:
                return this.commissionForIndividual.toString();
            case ViewAllBanksCommand.COMMISSION_FOR_ENTITY_ATTRIBUTE_TITLE:
                return this.commissionForEntity.toString();
            default:
                throw new IllegalArgumentException(String.format("Unsupported command attribute %s", commandAttribute));
        }
    }
}