//package home.jaxb;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.File;
//
//public class JaxbReader {
//
//    public static void main(String[] args) {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            Customer customer = (Customer) unmarshaller.unmarshal(new File("D:\\java\\IntexSoft\\Projects\\BankApplication\\src\\main\\resources\\banks.xml"));
//            System.out.println(customer.getId());
//            System.out.println(customer.getName());
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
