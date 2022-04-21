package home.intexsoft.bank_application.controller.bankController;

import home.intexsoft.bank_application.command.Command;
import home.intexsoft.bank_application.command.CommandCreator;
import home.intexsoft.bank_application.controller.bankController.dto.AddBankRequestDto;
import home.intexsoft.bank_application.controller.bankController.dto.DeleteBankRequestDto;
import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.mapper.BankMapper;
import home.intexsoft.bank_application.models.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banks")
public class BankController {

    //private final ApplicationContext applicationContext;
    private final CommandCreator commandCreator;
    private final BankMapper bankMapper;

//    @GetMapping("/context")
//    public String[] getContext() {
//        return applicationContext.getBeanDefinitionNames();
//    }

    @PostMapping(value = "/add-bank", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> addBank(@RequestBody AddBankRequestDto addBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.ADD_BANK, addBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/view-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> viewAll() {
        Command command = commandCreator.createCommand(Command.CommandType.VIEW_ALL_BANKS);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-bank", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> deleteBank(@RequestBody DeleteBankRequestDto deleteBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.DELETE_BANK, deleteBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/add-currency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> addCurrency(@RequestBody DeleteBankRequestDto deleteBankRequestDto) {
        Command command = commandCreator.createCommand(Command.CommandType.DELETE_BANK, deleteBankRequestDto);
        List<ModelDto> modelsDto = command.execute();
        return new ResponseEntity<>(modelsDto, HttpStatus.OK);
    }


    //    @PostMapping(value = "/addBankPar/{bankId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BankDto> saveBank(@PathVariable String bankId) {
//        //BankDto createdBank = bankService.addBank(bankName, commissionForIndividual, commissionForEntity);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }

//    @PostMapping(value = "/addBankParam", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BankDto> saveBank(@RequestParam String bankName,
//                                            @RequestParam String commissionForIndividual,
//                                            @RequestParam String commissionForEntity) {
//        BankDto createdBank = bankService.addBank(bankName, commissionForIndividual, commissionForEntity);
//        return new ResponseEntity<>(createdBank, HttpStatus.OK);
//    }


//    @PostMapping(value = "/add-bank", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BankDto> addBank(@RequestBody BankDto bankDto) {
//        BankDto createdBank = bankService.addBank(bankDto);
//        return new ResponseEntity<>(createdBank, HttpStatus.OK);
//    }

}