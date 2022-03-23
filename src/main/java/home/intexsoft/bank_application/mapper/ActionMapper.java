package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.dao.BankAccountDAO;
import home.intexsoft.bank_application.dto.ActionDto;
import home.intexsoft.bank_application.models.Action;
import home.intexsoft.bank_application.models.BankAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = BankAccountDAO.class)
public interface ActionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccount", ignore = true)
    @Mapping(target = "operation", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    Action fromActionDto(ActionDto actionDTO, @Context BankAccountDAO bankAccountDAO);

    @AfterMapping
    default void mapBankAccount(ActionDto actionDto, @MappingTarget Action action, @Context BankAccountDAO bankAccountDAO){
        BankAccount bankAccount = bankAccountDAO.findById(actionDto.getBankAccountId());
        action.setBankAccount(bankAccount);
    }
}