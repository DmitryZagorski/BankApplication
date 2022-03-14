package home.intexsoft.bank_application.command;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private CommandType name;
    private Map<CommandAttribute, String> attributes = new HashMap<>();

    public CommandType getName() {
        return name;
    }

    public void setName(CommandType name) {
        this.name = name;
    }

    Map<CommandAttribute, String> getAttributes() {
        return attributes;
    }

    public abstract void execute();

    public enum CommandType {

        BANKS("banks"),
        CLIENTS("clients"),
        OPERATIONS("operations"),
        ADD_BANK("add bank"),
        DELETE_BANK("delete bank"),
        DELETE_ALL_BANKS("delete all banks"),
        FIND_CLIENTS_OF_BANK("find clients of bank"),
        VIEW_ALL_BANKS("view all banks"),
        ADD_CLIENT("add client"),
        DELETE_CLIENT("delete client"),
        DELETE_ALL_CLIENTS_OF_BANK("delete all clients of bank"),
        FIND_BANK_ACCOUNTS_OF_CLIENT("find bank accounts of client"),
        ADD_BANK_ACCOUNT("add bank account"),
        FIND_OPERATIONS_OF_CLIENT("find operations of client"),
        ADD_CURRENCY("add currency"),
        ADD_MONEY_TRANSFER("add money transfer"),
        ADD_SALARY_PAYMENT("add salary payment");

        private String commandName;

        CommandType(String commandName) {
            this.commandName = commandName;
        }

        public String getCommandName() {
            return commandName;
        }
    }

    public enum ClientStatusType {

        INDIVIDUAL("individual"),
        ENTITY("entity");

        private String clientStatusName;

        ClientStatusType(String clientStatusName) {
            this.clientStatusName = clientStatusName;
        }

        public String getClientStatusName() {
            return clientStatusName;
        }

        public void setClientStatusName(String clientStatusName) {
            this.clientStatusName = clientStatusName;
        }
    }

    public enum OperationStatus {

        CREATED("created"),
        IN_PROCESS("in process"),
        SUCCESS("success"),
        FAILED("failed");

        private String operationStatusName;

        OperationStatus(String operationStatusName) {
            this.operationStatusName = operationStatusName;
        }

        public String getOperationStatusName() {
            return operationStatusName;
        }

        public void setOperationStatusName(String operationStatusName) {
            this.operationStatusName = operationStatusName;
        }
    }

    public enum ActionType {

        WITHDRAW("withdraw"),
        ADDITION("addition");

        private String operationTypeName;

        ActionType(String operationTypeName) {
            this.operationTypeName = operationTypeName;
        }

        public String getOperationTypeName() {
            return operationTypeName;
        }

    }
}