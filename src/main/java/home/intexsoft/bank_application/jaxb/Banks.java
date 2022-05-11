package home.intexsoft.bank_application.jaxb;

import home.intexsoft.bank_application.models.Bank;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "banks")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Component
public class Banks {

    @XmlElement
    private List<Bank> bankList;

}