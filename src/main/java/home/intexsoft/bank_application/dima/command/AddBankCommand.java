package home.intexsoft.bank_application.dima.command;

import home.intexsoft.bank_application.dima.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddBankCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(AddBankCommand.class);

    public enum Attribute implements CommandAttribute {

        BANK_NAME("bank name"),
        COMMISSION_FOR_INDIVIDUAL("commission for individual"),
        COMMISSION_FOR_ENTITY("commission for entity");

        private String attributeName;

        Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

    }

    {
        setName(CommandType.ADD_BANK);

        getAttributes().put(Attribute.BANK_NAME, null);
        getAttributes().put(Attribute.COMMISSION_FOR_INDIVIDUAL, null);
        getAttributes().put(Attribute.COMMISSION_FOR_ENTITY, null);
    }

    @Override
    public void execute(Command command) {
        log.info("Executing started");
        BankService bankService = new BankService();
        bankService.addBank(command.getAttributes().get(Attribute.BANK_NAME),
                command.getAttributes().get(Attribute.COMMISSION_FOR_INDIVIDUAL),
                command.getAttributes().get(Attribute.COMMISSION_FOR_ENTITY));


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
