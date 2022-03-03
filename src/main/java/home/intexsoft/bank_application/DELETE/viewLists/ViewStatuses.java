package home.intexsoft.bank_application.DELETE.viewLists;

import home.intexsoft.bank_application.DELETE.commandRepresentation.ClientRepresentation;
import home.intexsoft.bank_application.models.ClientStatus;

import java.util.List;

public class ViewStatuses {

    public void execute() {
        List<ClientStatus> allStatuses = new ClientRepresentation().findAllStatuses();
        ClientStatus clientStatus;
        for (ClientStatus allStatus : allStatuses) {
            clientStatus = allStatus;
            System.out.println(clientStatus.getId() + " " + clientStatus.getName());
        }
    }
}
