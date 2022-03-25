package home.intexsoft.bank_application.service;

import home.intexsoft.bank_application.controller.dto.BankDto;
import home.intexsoft.bank_application.controller.dto.BankMapperImpl;
import home.intexsoft.bank_application.dao.BankDAO;
import home.intexsoft.bank_application.models.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    private BankDAO bankDAO;

    @Autowired
    public BankService(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    public Bank addBank(String bankName, String commissionForIndividual, String commissionForEntity) {
        log.debug("Method AddBank (name = '" + bankName + "' started");
        Bank bank = createBankAndSetValuesOfAttributes(bankName, commissionForIndividual, commissionForEntity);
        Bank createdBank = bankDAO.createBank(bank);
        log.debug("Method AddBank (name = '" + bankName + "' finished");
        return createdBank;
    }

    public void deleteBankByName(String bankName) {
        log.debug("Method DeleteBank '" + bankName + "'started");
        bankDAO.deleteByName(bankName);
        log.debug("Method DeleteBank '" + bankName + "'finished");
    }

//    public List<Bank> viewAllBanks() {
//        log.debug("Method ViewAllBanks started");
//        List<Bank> bankList = bankDAO.findAll();
//        //bankList.forEach(System.out::println);    // temporary commented by adding bank controller
//        return bankList;
//       // log.debug("Method ViewAllBanks finished");
//    }

    public List<BankDto> viewAllBanks() {
        log.debug("Method ViewAllBanks started");
        BankMapperImpl bankMapper = new BankMapperImpl();
        List<Bank> bankList = bankDAO.findAll();
        List<BankDto> bankDtoList = bankList.stream().map(bankMapper::fromBank).collect(Collectors.toList());
        log.debug("Method ViewAllBanks finished");
        return bankDtoList;
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

    public boolean checkIfBankNameExist(String bankName) {
        return bankDAO.findByName(bankName) != null;
    }


}