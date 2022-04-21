package home.intexsoft.bank_application.controller;

import home.intexsoft.bank_application.command.CommandAttribute;

public abstract class CommandDto {

    public abstract String getByAttributeName(CommandAttribute commandAttribute);
}