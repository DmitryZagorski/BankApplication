package home.intexsoft.bank_application.dima.command;

import home.intexsoft.bank_application.connection.ConnectionPoolProvider;
import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.exceptions.EntitySavingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBank extends Command {

    {
        this.getAttributes().put("bank name", null);
        this.getAttributes().put("commission for individual", null);
        this.getAttributes().put("commission for entity", null);
    }

    private static final Logger Log = LoggerFactory.getLogger(AddBank.class);

    @Override
    protected void execute() {
        Log.info("Adding new bank");
       // String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values ('".concat(getAttributes().get("bank name").concat("',").concat(getAttributes().get("commission for individual").concat(",".concat(getAttributes().get("commission for entity".concat(")"))))));
        String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values ('".concat(getAttributes().get("bank name").concat("',").concat(getAttributes().get("commission for individual").concat(",".concat(getAttributes().get("commission for entity").concat(")")))));
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            prStatement = connection.prepareStatement(insertBankSQL);
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Bank was not added!");
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Log.info("Error during rollback");
            }
            Log.error("Something wrong during adding bank", e);
            throw new EntitySavingException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
    }
}
