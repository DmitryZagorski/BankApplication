package home.intexsoft.bank_application.userInputParsing;

import java.util.HashMap;
import java.util.Map;

public class CommandDescriptor {

    // Описывает команду, которую мы хотим выполнить
    // Данный класс должнен донести все необходимые сведения для выполнения определенной команды до CommandRepresentation
    // К примеру, сюда приходит описание команды CreateBank, в FactoryRepresentation мы получаем её класс и инстанциируем её, а в
    // CommandRepresentation проверяем, есть ли у нас всё, чтобы выполнить нужную команду

    
//    private String command;
    // change to Map & instantiate it in class, add method to set one attribute 
//    private String[] attributes;


//    public CommandDescriptor(String command, String[] attributes) {
//        this.command = command;
//        this.attributes = attributes;
//    }
//
//    public String getCommand() {
//        return command;
//    }
//
//    public String[] getAttributes() {
//        return attributes;
//    }

    private String command;
    private Map<String, String> attributes = new HashMap<>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setCommandAndAttributes(String command, Map<String, String> attributes){

    }

    public void addAttribute(String command, String commandValue){
        attributes.put(command, commandValue);
    }




//    CommandDescriptor setAttributesForCommandAddBank(String bankName, Double commissionForIndividual, Double commissionForEntity, Map<String, String> attributes) {
//        CommandDescriptor descriptor = new CommandDescriptor("addBank", attributes);
//        descriptor.getAttributes().put("1", bankName);
//        descriptor.getAttributes().put("2", commissionForIndividual.toString());
//        descriptor.getAttributes().put("3", commissionForEntity.toString());
//        return descriptor;
//    }
}
