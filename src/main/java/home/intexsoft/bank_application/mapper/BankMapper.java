package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.dto.BankDto;
import home.intexsoft.bank_application.models.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankMapper {

    BankDto fromBank(Bank bank);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    Bank fromBankDto(BankDto bankDto);

}
