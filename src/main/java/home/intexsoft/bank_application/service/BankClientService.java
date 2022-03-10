package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.BankClientDAO;
import home.intexsoft.bank_application.models.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankClientService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(BankClientService.class);
    private BankClientDAO bankClientDAO = new BankClientDAO();

    public void addBankClient(String bankId, String clientId) {
        log.debug("Method addBankClient started");
        BankClient bankClient = createBankAndSetValuesOfAttributes(bankId, clientId);
        bankClientDAO.create(bankClient);
        log.debug("Method addBankClient finished");
    }

    private BankClient createBankAndSetValuesOfAttributes(String bankId, String clientId) {
        log.debug("Creating BankClient with setting its arguments started");
        final BankClient bankClient = new BankClient();
        bankClient.setBankId(Integer.valueOf(bankId));
        bankClient.setClientId(Integer.valueOf(clientId));
        log.debug("Creating BankClient with setting its arguments finished");
        return bankClient;
    }

}
