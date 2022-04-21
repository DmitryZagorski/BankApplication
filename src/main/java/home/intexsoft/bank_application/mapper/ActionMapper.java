package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.dto.action.ActionDto;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.service.BankAccountService;
import org.mapstruct.*;

@Mapper
public interface ActionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccount", ignore = true)
    @Mapping(target = "operation", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    Action fromActionDto(ActionDto actionDTO);

    @AfterMapping
    default void mapBankAccount(ActionDto actionDto, @MappingTarget Action action, @Context BankAccountService bankAccountService) {
        BankAccount bankAccount = bankAccountService.findBankAccountById(actionDto.getBankAccountId());
        action.setBankAccount(bankAccount);
    }
}