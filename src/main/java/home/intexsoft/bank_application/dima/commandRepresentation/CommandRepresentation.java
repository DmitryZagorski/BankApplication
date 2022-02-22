package home.intexsoft.bank_application.dima.commandRepresentation;

import home.intexsoft.bank_application.dima.Command;

public abstract class CommandRepresentation {

    boolean validateData(Command command){
        return true;
    };

    void execute(Command command){

    };

}
