package home.intexsoft.bank_application.command;

public interface CommandCreator {

    Command createCommand(Command.CommandType commandName);

}
