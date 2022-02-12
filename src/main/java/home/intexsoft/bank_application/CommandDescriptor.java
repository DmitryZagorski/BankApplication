package home.intexsoft.bank_application;

public class CommandDescriptor {

    // Описывает команду, которую мы хотим выполнить
    // Данный класс должнен донести все необходимые сведения для выполнения определенной команды до CommandRepresentation
    // К примеру, сюда приходит описание команды CreateBank, в FactoryRepresentation мы получаем её класс и инстанциируем её, а в
    // CommandRepresentation проверяем, есть ли у нас всё, чтобы выполнить нужную команду

    private String command;
    private String[] attributes;

    public CommandDescriptor(String command, String[] attributes) {
        this.command = command;
        this.attributes = attributes;
    }

    public String getCommand() {
        return command;
    }

    public String[] getAttributes() {
        return attributes;
    }

}
