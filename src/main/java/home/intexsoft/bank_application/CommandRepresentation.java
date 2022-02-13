package home.intexsoft.bank_application;

import home.intexsoft.bank_application.connection.ConnectionPoolProvider;
import home.intexsoft.bank_application.exceptions.EntityRemoveException;
import home.intexsoft.bank_application.exceptions.EntityRetrievalException;
import home.intexsoft.bank_application.exceptions.EntitySavingException;
import home.intexsoft.bank_application.models.Bank;
import home.intexsoft.bank_application.models.BankAccount;
import home.intexsoft.bank_application.models.BankClient;
import home.intexsoft.bank_application.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandRepresentation {

    // Выполняет команду и валидирует её  (данный класс работает в свзке с CommandDescriptor)
    // Этот класс знает, что именно делать, а CommandDescriptor знает, с чем именно это делать
    // Возможно просто создать метод Execute и метод Validate
    // Вызываем Validate и передаем в него Descriptor
    // Данный класс проверяет наличие данных для выполнения команды

    private static final Logger Log = LoggerFactory.getLogger(CommandRepresentation.class);




























}
