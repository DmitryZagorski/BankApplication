package home.intexsoft.bank_application.controller.clientController;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandCreator;
import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.controller.bankController.dto.AddBankRequestDto;
import home.intexsoft.bank_application.controller.bankController.dto.DeleteBankRequestDto;
import home.intexsoft.bank_application.controller.clientController.dto.AddBankAccountRequestDto;
import home.intexsoft.bank_application.controller.clientController.dto.FindBankAccountsOfClientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final CommandCreator commandCreator;

    @PostMapping(value = "/add-client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> addBank(@RequestBody AddBankRequestDto addBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.ADD_CLIENT, addBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> deleteBank(@RequestBody DeleteBankRequestDto deleteBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.ADD_CLIENT, deleteBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-bank-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> findBankAccountOfClient(
            @RequestBody FindBankAccountsOfClientRequestDto findBankAccountsOfClientRequestDto) {
        Command command = commandCreator
                .createCommand(Command.CommandType.FIND_BANK_ACCOUNTS_OF_CLIENT, findBankAccountsOfClientRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/add_bank_account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> addBankAccount(@RequestBody AddBankAccountRequestDto addBankAccountRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.ADD_BANK_ACCOUNT, addBankAccountRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }
}