package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.controller.bankController.dto.CurrencyDto;
import home.intexsoft.bank_application.controller.clientController.dto.BankAccountDto;
import home.intexsoft.bank_application.controller.clientController.dto.ClientDto;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.service.ClientService;
import home.intexsoft.bank_application.service.CurrencyService;
import org.mapstruct.*;

@Mapper
public interface BankAccountMapper {

    @Mapping(target = "clientName", ignore = true)
    @Mapping(target = "clientSurname", ignore = true)
    @Mapping(target = "currencyName", ignore = true)
    BankAccountDto fromBankAccount(BankAccount bankAccount);

    @AfterMapping
    default void mapBankAccountDto(BankAccount bankAccount,
                                   @MappingTarget BankAccountDto bankAccountDto,
                                   @Context ClientService clientService,
                                   @Context CurrencyService currencyService) {

        ClientDto client = clientService.findById(bankAccount.getClient().getId());
        bankAccountDto.setClientName(client.getClientName());
        bankAccountDto.setClientSurname(client.getClientSurname());

        CurrencyDto currency = currencyService.findById(bankAccount.getCurrency().getId());
        bankAccountDto.setCurrencyName(currency.getCurrencyName());

    }

    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    BankAccount fromBankAccountDto(BankAccountDto bankAccountDto);

}