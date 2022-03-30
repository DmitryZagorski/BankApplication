package home.intexsoft.bank_application.controller;

import home.intexsoft.bank_application.dto.BankDto;
import home.intexsoft.bank_application.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;
    private final ApplicationContext applicationContext;

    @Autowired
    public BankController(BankService bankService, ApplicationContext applicationContext) {
        this.bankService = bankService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/context")
    public String[] getContext() {
        return applicationContext.getBeanDefinitionNames();
    }

    @GetMapping(value = "/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankDto>> viewAll() {
        return new ResponseEntity<>(bankService.viewAllBanks(), HttpStatus.OK);
    }

    @PostMapping(value = "/addBankParam", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankDto> saveBank(@RequestParam String bankName,
                                            @RequestParam String commissionForIndividual,
                                            @RequestParam String commissionForEntity) {
        BankDto createdBank = bankService.addBank(bankName, commissionForIndividual, commissionForEntity);
        return new ResponseEntity<>(createdBank, HttpStatus.OK);
    }

    @PostMapping(value = "/addBank", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankDto> saveBank(@RequestBody BankDto bankDto) {
        BankDto createdBank = bankService.addBank(bankDto);
        return new ResponseEntity<>(createdBank, HttpStatus.OK);
    }

    @PostMapping(value = "/addBankPar/{bankId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankDto> saveBank(@PathVariable String bankId) {
        //BankDto createdBank = bankService.addBank(bankName, commissionForIndividual, commissionForEntity);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}