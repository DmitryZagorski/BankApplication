package home.intexsoft.bank_application.dima.attributeDescriptor;

public class BankNameDescriptor extends AttributeDescriptor {

    {
        getDescription().put(DescriptorParameter.TYPE, AttributeType.STRING.name());
        getDescription().put(DescriptorParameter.STRING_MIN_LENGTH, "1");
        getDescription().put(DescriptorParameter.STRING_MAX_LENGTH, "20");
    }

}
