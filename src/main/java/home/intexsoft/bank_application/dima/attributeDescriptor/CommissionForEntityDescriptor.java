package home.intexsoft.bank_application.dima.attributeDescriptor;

public class CommissionForEntityDescriptor extends AttributeDescriptor {

    {
        getDescription().put(DescriptorParameter.TYPE, AttributeType.DOUBLE.name());
        getDescription().put(DescriptorParameter.MAX_VALUE, "20");
        getDescription().put(DescriptorParameter.MIN_VALUE, "5");
    }
}
