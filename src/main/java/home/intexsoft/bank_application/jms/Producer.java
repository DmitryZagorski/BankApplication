package home.intexsoft.bank_application.jms;

import org.apache.activemq.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.jms.*;

@Stateless
//@LocalBean
@Component
public class Producer {

    @Resource(name = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    @Resource(name = "java:/queue/test")
    private Destination destination;

    @PostConstruct
    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void produceMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            session.createQueue("customerQueue");
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("Hello world");
            messageProducer.send(message);
            System.out.println("___________________________");
            connection.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }













//    @PostConstruct
//    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
//    public void produceMessage() {
//        try {
//            Connection connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer messageProducer = session.createProducer(destination);
//            TextMessage message = session.createTextMessage("Hello world");
//            messageProducer.send(message);
//            System.out.println("___________________________");
//            connection.close();
//            session.close();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
}