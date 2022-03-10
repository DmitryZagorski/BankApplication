package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.CurrencyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientStatusService extends ModelService{

    private static final Logger log = LoggerFactory.getLogger(ClientStatusService.class);
    private CurrencyDAO currencyDAO = new CurrencyDAO();

    public boolean checkIClientStatusNameExist(String clientStatus) {
        return currencyDAO.findByName(clientStatus) != null;
    }

    public boolean checkIfClientStatusIdExist(Integer clientStatusId){
        return currencyDAO.findById(clientStatusId) != null;
    }
}
