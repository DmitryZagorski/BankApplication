package home.intexsoft.bank_application.dima.commandRepresentation;

import home.intexsoft.bank_application.dima.Command;

public class DeleteBank extends Command {

    {
        this.getAttributes().put("bank id", null);
    }

    @Override
    protected void execute() {

    }
}
