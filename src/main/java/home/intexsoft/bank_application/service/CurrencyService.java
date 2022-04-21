package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.controller.bankController.dto.CurrencyDto;
import home.intexsoft.bank_application.dao.CurrencyDAO;
import home.intexsoft.bank_application.mapper.CurrencyMapper;
import home.intexsoft.bank_application.models.Currency;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private final CurrencyDAO currencyDAO;
    private final CurrencyMapper currencyMapper;

    public List<ModelDto> addCurrency(String currencyName, String rate) {
        log.debug("Method addCurrency started");
        Currency currency = new Currency();
        currency.setName(currencyName);
        currency.setRate(Double.valueOf(rate));
        currencyDAO.create(currency);
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(currencyMapper.fromCurrency(currency));
        log.debug("Method addCurrency finished");
        return modelsDto;
    }

    Currency findByName(String currencyName) {
        return currencyDAO.findByName(currencyName);
    }

    public CurrencyDto findById(Integer id) {
        Currency currency = currencyDAO.findById(id);
        return currencyMapper.fromCurrency(currency);
    }

    public boolean checkIfCurrencyNameExist(String currencyName) {
        return currencyDAO.findByName(currencyName) != null;
    }
}