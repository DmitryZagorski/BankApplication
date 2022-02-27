package home.intexsoft.bank_application.dima.validation;

import home.intexsoft.bank_application.dima.Command;
import home.intexsoft.bank_application.dima.String;
import home.intexsoft.bank_application.dima.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.dima.attributeDescriptor.StringDescriptor;

import java.util.List;

public class StringAttributeValidator extends Validator {

    @Override
    public boolean validate(AttributeDescriptor attributeDescriptor, List<String> errors) {




        return false;
    }









    private boolean validateByMaxLength(String attributesParameter) {
        boolean isVerified = false;
//        if (maxLength <= attributesParameter.length()){
//            isVerified = true;
//        }
//        else System.out.println();
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



}
