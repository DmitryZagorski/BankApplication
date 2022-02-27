package home.intexsoft.bank_application.dima.command;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddBankCommand extends Command {

    {
        this.getAttributes().put(String.BANK_NAME, null);
        this.getAttributes().put(String.COMMISSION_FOR_INDIVIDUAL, null);
        this.getAttributes().put(String.COMMISSION_FOR_ENTITY, null);
    }

    private static final Logger Log = LoggerFactory.getLogger(AddBankCommand.class);

    @Override
    protected void execute() {
//        Log.info("Adding new bank");
//       // String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values ('".concat(getAttributes().get("bank name").concat("',").concat(getAttributes().get("commission for individual").concat(",".concat(getAttributes().get("commission for entity".concat(")"))))));
//        String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values ('".concat(getAttributes().get("bank name").concat("',").concat(getAttributes().get("commission for individual").concat(",".concat(getAttributes().get("commission for entity").concat(")")))));
//        PreparedStatement prStatement = null;
//        Connection connection = null;
//        try {
//            connection = ConnectionPoolProvider.getConnection();
//            connection.setSavepoint();
//            connection.setAutoCommit(false);
//
//            prStatement = connection.prepareStatement(insertBankSQL);
//            int result = prStatement.executeUpdate();
//            if (result != 1) {
//                throw new EntitySavingException("Bank was not added!");
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                Log.info("Error during rollback");
//            }
//            Log.error("Something wrong during adding bank", e);
//            throw new EntitySavingException(e);
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            if (prStatement != null) {
//                try {
//                    prStatement.close();
//                } catch (SQLException e) {
//                    throw new EntitySavingException(e);
//                }
//            }
//        }

        System.out.println("EXECUTING !!!!!!!!!!!!!!!");
    }
}
