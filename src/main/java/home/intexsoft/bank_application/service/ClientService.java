package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.controller.clientController.dto.ClientDto;
import home.intexsoft.bank_application.dao.ClientDAO;
import home.intexsoft.bank_application.mapper.ClientMapper;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.models.Client;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientDAO clientDAO;
    private final BankService bankService;
    private final ClientMapper clientMapper;

    public List<ModelDto> addClient(String clientName, String clientSurname, String clientStatus, String bankName) {
        log.debug("Method AddClient started");
        Client client = createClientAndSetValuesOfAttributes(clientName, clientSurname, clientStatus, bankName);
        clientDAO.create(client);
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(clientMapper.fromClient(client));
        log.debug("Method AddClient finished");
        return modelsDto;
    }

    private Client createClientAndSetValuesOfAttributes(String clientName, String clientSurname, String clientStatus, String bankName) {
        log.debug("Creating client with setting its arguments started");
        final Client client = new Client();
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setStatus(getClientStatusType(clientStatus));
        Bank bank = bankService.findBankByName(bankName);
        client.setBank(bank);
        log.debug("Creating client with setting its arguments finished");
        return client;
    }

    public List<ModelDto> deleteClientByName(String clientName) {
        log.debug("Method deleteClientByName started");
        Client deletedClient = clientDAO.deleteByName(clientName);
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(clientMapper.fromClient(deletedClient));
        log.debug("Method deleteClientByName finished");
        return modelsDto;
    }

    public List<ModelDto> findClientsOfBank(String bankName) {
        log.debug("Method findClientsOfBank started");
        Bank bankByName = bankService.findBankByName(bankName);
        Integer bankId = bankByName.getId();
        List<Client> clientList = clientDAO.findClientsOfBank(bankId);
        return clientList.stream()
                .map(clientMapper::fromClient)
                .collect(Collectors.toList());
    }

    Client findByName(String clientName) {
        return clientDAO.findByName(clientName);
    }

    public ClientDto findById(Integer id) {
        Client client = clientDAO.findById(id);
        return clientMapper.fromClient(client);
    }

    public boolean checkIfClientNameExist(String clientName) {
        return clientDAO.findByName(clientName) != null;
    }

    public boolean checkIfClientStatusExist(String clientStatus) {
        return clientStatus.equals(Command.ClientStatusType.ENTITY.getClientStatusName()) ||
                clientStatus.equals(Command.ClientStatusType.INDIVIDUAL.getClientStatusName());
    }

    private Command.ClientStatusType getClientStatusType(String statusName) {
        return Arrays
                .stream(Command.ClientStatusType.values())
                .filter(statusType -> statusType.getClientStatusName().equals(statusName)).findAny().orElse(null);
    }
}