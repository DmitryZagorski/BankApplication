package home.jaxbPracticeExample;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Customer {

    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private int age;

}
