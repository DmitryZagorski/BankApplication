package home.intexsoft.bank_application.controller.dto;

import home.intexsoft.bank_application.models.Bank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankDto fromBank(Bank bank);

}
