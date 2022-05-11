package home.intexsoft.bank_application.jms;

import org.springframework.stereotype.*;

import javax.annotation.*;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "FirstConsumer", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test")
})
@Component
public class FirstConsumer implements MessageListener {

    @PostConstruct
    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            System.out.println(text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}