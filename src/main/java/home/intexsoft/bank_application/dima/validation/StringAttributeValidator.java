package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.Command;

public class StringAttributeValidator extends Validator {

    private Integer maxLength = 50;

    private boolean validateByMaxLength(String attributesParameter) {
        boolean isVerified = false;
        if (maxLength <= attributesParameter.length()){
            isVerified = true;
        }
        else System.out.println();
        return true;
    }
//
//    private boolean validateByMaxLength(String attributesParameter) {
//        boolean isVerified = false;
//        if (maxLength <= attributesParameter.length()){
//            isVerified = true;
//        }
//        else System.out.println();
//        return true;
//    }


    @Override
    public boolean validate(Command command) {
        return false;
    }
}
