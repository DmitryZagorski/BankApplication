package home.intexsoft.bank_application.jaxb;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SchemaTest {

    public static void main(String[] args) {
        validateXsd();
    }

    public static void validateXsd() {
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        File fileXsd = new File("D:\\java\\IntexSoft\\Projects\\BankApplication\\src\\main\\resources\\banks.xsd");
        File fileXml = new File("D:\\java\\IntexSoft\\Projects\\BankApplication\\src\\main\\resources\\banks.xml");

        try {
            schema = schemaFactory.newSchema(fileXsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileXml));
        } catch (SAXException | IOException e) {
            System.out.println("Incorrect data!!!!!!!!!!");
            e.printStackTrace();
        }
    }
}
