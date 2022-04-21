package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.controller.clientController.dto.ClientDto;
import home.intexsoft.bank_application.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    @Mapping(target = "clientName", source = "name")
    @Mapping(target = "clientSurname", source = "surname")
    @Mapping(target = "clientStatus", source = "status")
    ClientDto fromClient(Client client);

}