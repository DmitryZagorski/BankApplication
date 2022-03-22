package home.intexsoft.bank_application.validation;

import home.intexsoft.bank_application.attributeDescriptor.AttributeDescriptor;
import home.intexsoft.bank_application.attributeDescriptor.AttributeType;
import home.intexsoft.bank_application.command.CommandAttribute;
import home.intexsoft.bank_application.service.BankAccountService;
import home.intexsoft.bank_application.service.BankService;
import home.intexsoft.bank_application.service.ClientService;
import home.intexsoft.bank_application.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    private static final String VALIDATION_FAILURE_PATTERN =
            "Validation has failed for field %s, processed value is '%s'\n";
    private static final String VALIDATION_PROBLEM_PATTERN =
            "Problem: %s\n";

    protected Map<CommandAttribute, List<String>> validationErrors = new HashMap<>();
    protected Map<CommandAttribute, List<AttributeDescriptor>> attributeRules = new HashMap<>();

    protected ClientService clientService;
    protected BankService bankService;
    protected CurrencyService currencyService;
    protected BankAccountService bankAccountService;

    @Autowired
    public Validator(ClientService clientService, BankService bankService, CurrencyService currencyService,
                     BankAccountService bankAccountService) {
        this.clientService = clientService;
        this.bankService = bankService;
        this.currencyService = currencyService;
        this.bankAccountService = bankAccountService;
    }

    public Map<CommandAttribute, List<String>> getValidationErrors() {
        return validationErrors;
    }

    public void validateAttribute(Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Validation of commandAttribute '" + commandAttributePair.getKey().getAttributeName() + "' started");
        this.attributeRules.get(commandAttributePair.getKey())
                .forEach(attributeDescriptor -> this.validateAttributeAccordingAttributeDescriptor
                        (attributeDescriptor, commandAttributePair));
        log.debug("Validation of commandAttribute '" + commandAttributePair.getKey().getAttributeName() + "' finished");
    }

    protected void validateAttributeAccordingAttributeDescriptor(
            AttributeDescriptor attributeDescriptor, Map.Entry<CommandAttribute, String> commandAttributePair) {
        log.debug("Choosing validation descriptor of attribute '" + attributeDescriptor.getValue() + " started");
        String checkingString = commandAttributePair.getValue();
        switch (attributeDescriptor.getKind()) {
            case TYPE:
                validateType(attributeDescriptor.getValue(), commandAttributePair.getKey(), checkingString);
                break;
            case MAX_VALUE:
            case MIN_VALUE:
                validateMaxAndMinValue(attributeDescriptor.getValue(),
                        commandAttributePair.getKey(),
                        attributeDescriptor.getKind(),
                        checkingString);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported attribute description type " + attributeDescriptor.getKind().name());
        }
        log.debug("Choosing validation descriptor of attribute '" + attributeDescriptor.getValue() + " finished");
    }

    private void validateType(String value, CommandAttribute commandAttribute, String checkingString) {
        if (AttributeType.DOUBLE.getAttributedName().equals(value)) {
            checkDoubleValue(checkingString, commandAttribute);
        }
        if (AttributeType.INTEGER.getAttributedName().equals(value)) {
            checkIntegerValue(checkingString, commandAttribute);
        }
    }

    private void validateMaxAndMinValue(String value, CommandAttribute commandAttribute,
                                        AttributeDescriptor.DescriptorParameter descriptorParameter,
                                        String checkingString) {
        log.debug("Validation by value " + value + " started");
        double parameterValue;
        double checkingValue;
        try {
            if (attributeRules.get(commandAttribute).get(0).getValue().equals(AttributeType.STRING.getAttributedName())) {
                checkingValue = checkingString.length();
            } else {
                checkingValue = Double.parseDouble(checkingString);
            }
            parameterValue = Double.parseDouble(value);
            switch (descriptorParameter) {
                case MIN_VALUE:
                    if (checkingValue < parameterValue) {
                        String problem = "Input value should be min " + parameterValue;
                        addErrorToErrorList(commandAttribute, checkingString, problem);
                    }
                    break;
                case MAX_VALUE:
                    if (checkingValue > parameterValue) {
                        String problem = "Input value should be max " + parameterValue;
                        addErrorToErrorList(commandAttribute, checkingString, problem);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("That type of descriptor doesn't exist " + descriptorParameter.name());
            }
        } catch (NumberFormatException e) {
            addErrorToErrorList(commandAttribute, checkingString, e.getClass().getName());
        }
        log.debug("Validation by value " + value + " finished");
    }

    protected void addErrorToErrorList(CommandAttribute commandAttribute, String checkingString, String problem) {
        log.debug("Adding errors of '" + commandAttribute.getAttributeName() + "'to error list started");
        validationErrors.get(commandAttribute).add(String.format(VALIDATION_FAILURE_PATTERN,
                commandAttribute.getAttributeName(),
                checkingString) + String.format(VALIDATION_PROBLEM_PATTERN, problem));
        log.debug("Adding errors of '" + commandAttribute.getAttributeName() + "'to error list finished");
    }

    private void checkDoubleValue(String checkingString, CommandAttribute commandAttribute) {
        log.debug("Checking double value of '" + checkingString + "'started");
        try {
            Double.parseDouble(checkingString);
        } catch (NumberFormatException e) {
            addErrorToErrorList(commandAttribute, checkingString, e.getClass().getName());
        }
        log.debug("Checking double value of '" + checkingString + "'finished");
    }

    private void checkIntegerValue(String checkingString, CommandAttribute commandAttribute) {
        log.debug("Checking integer value of '" + checkingString + "'started");
        try {
            Integer.parseInt(checkingString);
        } catch (NumberFormatException e) {
            addErrorToErrorList(commandAttribute, checkingString, e.getClass().getName());
        }
        log.debug("Checking integer value of '" + checkingString + "'finished");
    }

    public void showValidationErrors(List<String> errors) {
        log.debug("Showing error list with filtering started");
        errors.stream().distinct().forEach(System.out::println);
        log.debug("Showing error list with filtering finished");
    }
}