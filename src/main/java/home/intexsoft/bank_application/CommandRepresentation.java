package home.intexsoft.bank_application;

public class CommandRepresentation {

    // Выполняет команду и валидирует её  (данный класс работает в свзке с CommandDescriptor)
    // Этот класс знает, что именно делать, а CommandDescriptor знает, с чем именно это делать
    // Возможно просто создать метод Execute и метод Validate
    // Вызываем Validate и передаем в него Descriptor
    // Данный класс проверяет наличие данных для выполнения команды

    public boolean validateDataDuringAddingBank(String[] attributes) {
        boolean isValidated = false;
        try {
            String bankName = attributes[0];
            Double commissionForIndividual = Double.parseDouble(attributes[1]);
            Double commissionForEntity = Double.parseDouble(attributes[2]);

            if (verifyIfNameOfBankAlreadyExist(bankName) &
                    verifyIfIDoubleDigitAboveZero(commissionForIndividual) &
                    verifyIfIDoubleDigitAboveZero(commissionForEntity)) {
                isValidated = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong data were entered.");
        }
        return isValidated;
    }

    public boolean validateDataDuringFindBankAccountOfClient(String[] attributes) {
        boolean isValidated = false;
        try {
            Integer clientId = Integer.parseInt(attributes[0]);
            if (verifyIfClientExist(clientId) &
                    verifyIfIntegerDigitAboveZero(clientId)) {
                isValidated = true;
            } else {
                System.out.println("Client with such Id doesn't exist");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong data were entered");
        }
        return isValidated;
    }

    public Boolean validateDataDuringRemovingBank(String[] attributes) {
        Boolean isValidated = false;
        try {
            Integer bankId = Integer.parseInt(attributes[0]);
            if (verifyIfBankExist(bankId) &
                    verifyIfIntegerDigitAboveZero(bankId)) {
                isValidated = true;
            } else {
                System.out.println("Bank with such Id doesn't exist");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong data were entered");
        }
        return isValidated;
    }

    public Boolean validateDataDuringAddingClient(String[] attributes){
        Boolean isValidated = false;
        try{
        String name = attributes[0];
        String surname = attributes[1];
        String status = attributes[2];
        String currency = attributes[3];
        Double amountOfMoney = Double.parseDouble(attributes[4]);


        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Wrong data were entered");
        }
        return isValidated;
    }

    private Boolean verifyIfIntegerDigitAboveZero(Integer clientId) {
        boolean isAboveZero = true;
        if (clientId <= 0) {
            isAboveZero = false;
        }
        return isAboveZero;
    }

    private Boolean verifyIfIDoubleDigitAboveZero(Double digit) {
        boolean isAboveZero = true;
        if (digit <= 0) {
            isAboveZero = false;
        }
        return isAboveZero;
    }

    private Boolean verifyIfBankExist(Integer bankId) {


        return null;
    }

    private Boolean verifyIfClientExist(Integer clientId) {


        return null;
    }

    private Boolean verifyIfNameOfBankAlreadyExist(String bankName) {
        //List<Bank> banks = BankRepository.getInstance().findAll();
        boolean isExist = false;
//        for (Bank bank : banks) {
//            if (bankName.equalsIgnoreCase(bank.getName())) {
//                isExist = true;
//            }
//        }
        return isExist;
    }




}
