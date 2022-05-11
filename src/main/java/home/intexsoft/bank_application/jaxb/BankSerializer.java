package home.intexsoft.bank_application.jaxb;

import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankSerializer {

    private final JaxbWriter jaxbWriter;
    private final BankService bankService;

    @PostConstruct
    public void saveAllBanksToXmlFile() {
        List<Bank> bankList = bankService.findAllBanksForJaxb();
        jaxbWriter.saveBanksToXml(bankList);
    }
}