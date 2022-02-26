package home.intexsoft.bank_application.dima.validation;


import home.intexsoft.bank_application.dima.*;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;

import java.util.List;
import java.util.Map;

public class AddBankValidator extends BankValidator {

    @Override
    public boolean validate(Command command, CommandAttribute commandAttribute, List<String> errors) {
        String commandName = command.getName();
        commandAttribute.getAttributeRules().get(commandName);

        return false;
    }

//    @Override
//    public boolean validate(Command command) {
//        boolean isValidated = false;
//        Object o = null;
//        Map<CommandAttributeName, List<String>> validationErrors = new AddBankValidator().getValidationErrors();
//        try {
//            for (Map.Entry<CommandAttributeName, String> commandAttributes : command.getAttributes().entrySet()) {
//                o = commandAttributes.getKey();
////                for (Map.Entry<CommandAttribute, AttributeDescriptor> commandAttributeAttributeDescriptorEntry : validationTypes.entrySet()) {
////                    commandAttributeAttributeDescriptorEntry.getKey();
////                    Double aDouble = castObjectToDouble(o);
////
////                }
//
//            }
//            Object bankName = command.getAttributes().get(CommandAttributeName.BANK_NAME);
//            Object commissionForIndividual = command.getAttributes().get(CommandAttributeName.COMMISSION_FOR_INDIVIDUAL);
//            Object commissionForEntity = command.getAttributes().get(CommandAttributeName.COMMISSION_FOR_ENTITY);
//
//
//        } catch (IllegalArgumentException e) {
//            System.out.println("Wrong data were entered.");
//            new BankAppRunner().runMenu();
//        }
//        return isValidated;
//    }
//
//    private Double castObjectToDouble(Object o) {
//        Double val = null;
//        if (o instanceof Number) {
//            try {
//                val = ((Number) o).doubleValue();
//            } catch (NumberFormatException e) {
//                System.out.println("Number " + val + " is not double");
//            }
//        }
//        return val;
//    }
}
