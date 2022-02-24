package home.intexsoft.bank_application.dima.command;

import home.intexsoft.bank_application.dima.Command;

public class AddClient extends Command {

    {
        this.getAttributes().put("name", null);
        this.getAttributes().put("surname", null);
        this.getAttributes().put("status id", null);
        this.getAttributes().put("currency id", null);
        this.getAttributes().put("bank id", null);
        this.getAttributes().put("amount of money", null);
    }

    @Override
    protected void execute() {

    }
}
