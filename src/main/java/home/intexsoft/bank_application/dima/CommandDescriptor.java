package home.intexsoft.bank_application.dima;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandDescriptor {

    private static final Logger Log = LoggerFactory.getLogger(CommandDescriptor.class);

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}