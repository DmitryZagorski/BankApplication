package home.intexsoft.bank_application;

public class CommandRepresentation {

    // Выполняет команду и валидирует её  (данный класс работает в свзке с CommandDescriptor)
    // Этот класс знает, что именно делать, а CommandDescriptor знает, с чем именно это делать
    // Возможно просто создать метод Execute и метод Validate
    // Вызываем Validate и передаем в него Descriptor
    // Данный класс проверяет наличие данных для выполнения команды

    public boolean validateAttributes(String[] attributes){

        return true;
    }

    public void executeCommandBank(){ 



    }

}
