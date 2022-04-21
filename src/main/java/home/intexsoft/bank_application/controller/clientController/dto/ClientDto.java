package home.intexsoft.bank_application.controller.clientController.dto;

import home.intexsoft.bank_application.controller.ModelDto;
import lombok.Data;

@Data
public class ClientDto implements ModelDto {

    private String clientName;
    private String clientSurname;
    private String clientStatus;

}