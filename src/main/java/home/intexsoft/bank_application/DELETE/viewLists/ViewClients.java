package home.intexsoft.bank_application.DELETE.viewLists;

import home.intexsoft.bank_application.DELETE.commandRepresentation.ClientRepresentation;
import home.intexsoft.bank_application.models.Client;

import java.util.List;

public class ViewClients {

    public void execute(){
        List<Client> allClients = new ClientRepresentation().findAllClients();
        Client client;
        for (Client allClient : allClients) {
            client = allClient;
            System.out.println(client.getId() + " " + client.getName());
        }
    }

}
