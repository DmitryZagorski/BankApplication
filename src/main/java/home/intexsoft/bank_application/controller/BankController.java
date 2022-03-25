package home.intexsoft.bank_application.controller;

import home.intexsoft.bank_application.controller.dto.BankDto;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping(value = "/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankDto>> viewAll() {
        return new ResponseEntity<>(bankService.viewAllBanks(), HttpStatus.OK);
    }

    @PostMapping(value = "/addBank", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> saveBank(@RequestParam String bankName,
                                         @RequestParam String commissionForIndividual,
                                         @RequestParam String commissionForEntity) {
        Bank createdBank = bankService.addBank(bankName, commissionForIndividual, commissionForEntity);
        return new ResponseEntity<>(createdBank, HttpStatus.OK);
    }
}