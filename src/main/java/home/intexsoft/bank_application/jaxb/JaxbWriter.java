package home.intexsoft.bank_application.jaxb;

import home.intexsoft.bank_application.models.Bank;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class JaxbWriter {

    public void saveBanksToXml(List<Bank> banks) {
        try {
            Banks banks1 = new Banks();
            banks1.setBankList(banks);
            JAXBContext jaxbContext = JAXBContext.newInstance("home.intexsoft.bank_application.jaxb");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(banks1, new FileWriter("D:\\java\\IntexSoft\\Projects\\BankApplication\\src\\main\\resources\\banks.xml"));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}