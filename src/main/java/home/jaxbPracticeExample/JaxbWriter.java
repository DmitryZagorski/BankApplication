package home.jaxbPracticeExample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxbWriter {

    public static void main(String[] args) {

        Customers customers = new Customers();

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Max");
        customer.setAge(15);

        Customer customer1 = new Customer();
        customer1.setId(4);
        customer1.setName("Ilona");
        customer1.setAge(20);

        customers.getCustomers().add(customer);
        customers.getCustomers().add(customer1);



        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  // formatting to another string
            marshaller.marshal(customers, new File("D:\\java\\IntexSoft\\Projects\\BankApplication\\src\\main\\resources\\customers.xml"));


            marshaller.marshal(customers, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
