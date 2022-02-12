package home.intexsoft.bank_application;

public class FactoryRepresentation {

    // Получаем класс команды или команду, которая должна выполняться

    public void chooseMethod(CommandDescriptor commandDescriptor) {
        CommandRepresentation commandRepresentation = new CommandRepresentation();
        switch (commandDescriptor.getCommand()) {
            case "Banks":
                commandRepresentation.validateAttributes(commandDescriptor.getAttributes());
                commandRepresentation.executeCommandBank();
                break;

        }
    }


}
