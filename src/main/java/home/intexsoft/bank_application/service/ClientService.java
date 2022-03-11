package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.dao.ClientDAO;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientService extends ModelService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private ClientDAO clientDAO = new ClientDAO();
    private BankService bankService = new BankService();

    public void addClient(String clientName, String clientSurname, String clientStatus, String bankName) {
        log.debug("Method AddClient started");
        Client client = createClientAndSetValuesOfAttributes(clientName, clientSurname, clientStatus, bankName);
        clientDAO.create(client);
        log.debug("Method AddClient finished");
    }

    private Client createClientAndSetValuesOfAttributes(String clientName, String clientSurname, String clientStatus, String bankName) {
        log.debug("Creating client with setting its arguments started");
        final Client client = new Client();
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setStatus(clientStatus);
        Bank bank = bankService.findBankByName(bankName);
        client.setBank(bank);
        log.debug("Creating client with setting its arguments finished");
        return client;
    }

    public void deleteAllClientsOfBank(String bankName) {
        log.debug("Method deleteAllClientsOfBank started");
        Bank bankByName = bankService.findBankByName(bankName);
        Integer bankId = bankByName.getId();
        List<Client> clientList = clientDAO.findClientsOfBank(bankId);
        for (Client client : clientList) {
            clientDAO.delete(client);
        }
        log.debug("Method deleteAllClientsOfBank finished");
    }



    public void deleteClientByName(String clientName) {
        log.debug("Method deleteClientByName started");
        clientDAO.deleteByName(clientName);
        log.debug("Method deleteClientByName finished");
    }

    public void findClientsOfBank(String bankName) {
        log.debug("Method findClientsOfBank started");
        Bank bankByName = bankService.findBankByName(bankName);
        Integer bankId = bankByName.getId();
        List<Client> clientList = clientDAO.findClientsOfBank(bankId);
        clientList.forEach(System.out::println);
        log.debug("Method findClientsOfBank finished");
    }

    public void findTransactionsOfClient(String clientName) {

    }

    public void viewAllClients() {

    }

    public Client findByName(String clientName){
        return clientDAO.findByName(clientName);
    }

    public boolean checkIfClientNameExist(String clientName) {
        return clientDAO.findByName(clientName) != null;
    }

    public boolean checkIfClientStatusExist(String clientStatus) {
        return clientStatus.equals(Command.ClientStatusType.ENTITY.getClientStatusName()) ||
                clientStatus.equals(Command.ClientStatusType.INDIVIDUAL.getClientStatusName());
    }



}
