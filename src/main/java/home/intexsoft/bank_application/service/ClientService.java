package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.dao.ClientDAO;
import home.intexsoft.bank_application.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(String clientName, String clientSurname, String clientStatusId, String currencyId, String bankId, String amountOfMoney) {
        log.debug("Method AddClient started");
        BankAccountService bankAccountService = new BankAccountService();
        BankClientService bankClientService = new BankClientService();
        Client client = createClientAndSetValuesOfAttributes(clientName, clientSurname, clientStatusId);
        clientDAO.create(client);
        String clientId = null;    //////////////////////////////////
        bankAccountService.addBankAccount(currencyId, amountOfMoney, bankId, clientId);
        bankClientService.addBankClient(bankId, clientId);
        log.debug("Method AddClient finished");
    }

    private Client createClientAndSetValuesOfAttributes(String clientName, String clientSurname, String clientStatusId) {
        log.debug("Creating client with setting its arguments started");
        final Client client = new Client();
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setStatusId(Integer.valueOf(clientStatusId));
        log.debug("Creating client with setting its arguments finished");
        return client;
    }

    public void deleteAllClients() {

    }

    public void deleteClient(String clientName) {

    }

    public void findClientsOfBank(String bankName) {
        log.debug("Method findClientsOfBank started");
        int bankId = clientDAO.findByName(bankName).getId();
        clientDAO.findClientsOfBank(bankId);
        log.debug("Method findClientsOfBank finished");
    }

    public void findBankAccountsOfClient(String clientName) {

    }

    public void findTransactionsOfClient(String clientName) {

    }

    public void viewAllClients() {

    }



}
