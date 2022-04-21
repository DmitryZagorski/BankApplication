package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.controller.ModelDto;
import home.intexsoft.bank_application.controller.bankController.dto.BankDto;
import home.intexsoft.bank_application.dao.BankDAO;
import home.intexsoft.bank_application.mapper.BankMapper;
import home.intexsoft.bank_application.models.Bank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankService {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    private final BankDAO bankDAO;
    private final BankMapper bankMapper;

    public List<ModelDto> addBank(String bankName, String commissionForIndividual, String commissionForEntity) {
        log.debug("Method AddBank (name = '" + bankName + "' started");
        Bank bank = createBankAndSetValuesOfAttributes(bankName, commissionForIndividual, commissionForEntity);
        Bank createdBank = bankDAO.createBank(bank);
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(bankMapper.fromBank(createdBank));
        log.debug("Method AddBank (name = '" + bankName + "' finished");
        return modelsDto;
    }

    public List<ModelDto> deleteBankByName(String bankName) {
        log.debug("Method DeleteBank '" + bankName + "'started");
        Bank deletedBank = bankDAO.deleteByName(bankName);
        List<ModelDto> modelsDto = new ArrayList<>();
        modelsDto.add(bankMapper.fromBank(deletedBank));
        log.debug("Method DeleteBank '" + bankName + "'finished");
        return modelsDto;
    }

//    public List<Bank> viewAllBanks() {
//        log.debug("Method ViewAllBanks started");
//        List<Bank> bankList = bankDAO.findAll();
//        //bankList.forEach(System.out::println);    // temporary commented by adding bank controller
//        return bankList;
//       // log.debug("Method ViewAllBanks finished");
//    }

    public List<ModelDto> findAllBanks() {
        log.debug("Method ViewAllBanks started");
        List<Bank> bankList = bankDAO.findAll();
        bankList.forEach(System.out::println);
        return bankList.stream()
                .map(bankMapper::fromBank)
                .collect(Collectors.toList());
    }

    private Bank createBankAndSetValuesOfAttributes(String bankName,
                                                    String commissionForIndividual,
                                                    String commissionForEntity) {
        log.debug("Creating bank '" + bankName + "'with setting its arguments started");
        Bank bank = new Bank();
        bank.setName(bankName);
        bank.setCommissionForIndividual(Double.valueOf(commissionForIndividual));
        bank.setCommissionForEntity(Double.valueOf(commissionForEntity));
        log.debug("Creating bank '" + bankName + "'with setting its arguments finished");
        return bank;
    }

    Bank findBankByName(String bankName) {
        return bankDAO.findByName(bankName);
    }

    public BankDto findById(Integer id) {
        Bank bank = bankDAO.findById(id);
        return bankMapper.fromBank(bank);
    }

    public boolean checkIfBankNameExist(String bankName) {
        return bankDAO.findByName(bankName) != null;
    }

}