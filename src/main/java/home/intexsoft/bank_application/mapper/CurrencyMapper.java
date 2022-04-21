package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.controller.bankController.dto.CurrencyDto;
import home.intexsoft.bank_application.models.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CurrencyMapper {

    @Mapping(target = "currencyName", source = "name")
    CurrencyDto fromCurrency(Currency currency);

}