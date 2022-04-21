package home.intexsoft.bank_application.command;

import home.intexsoft.bank_application.controller.CommandDto;

public interface CommandCreator {

    Command createCommand(Command.CommandType commandName);

    Command createCommand(Command.CommandType commandName, CommandDto commandDto);

}
