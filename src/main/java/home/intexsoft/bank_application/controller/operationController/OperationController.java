package home.intexsoft.bank_application.controller.operationController;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandCreator;
import home.intexsoft.bank_application.controller.bankController.dto.AddBankRequestDto;
import home.intexsoft.bank_application.controller.ModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {

    private final CommandCreator commandCreator;

    @PostMapping(value = "/add-money-transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> addMoneyTransfer(@RequestBody AddBankRequestDto addBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.ADD_MONEY_TRANSFER, addBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

}
