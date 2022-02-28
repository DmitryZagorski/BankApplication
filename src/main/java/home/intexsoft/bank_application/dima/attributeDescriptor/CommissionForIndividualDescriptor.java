package home.intexsoft.bank_application.dima.attributeDescriptor;

public class CommissionForIndividualDescriptor extends AttributeDescriptor{

    {
        getDescription().put(AttributeDescriptor.DescriptorParameter.TYPE, AttributeType.DOUBLE.name());
        getDescription().put(AttributeDescriptor.DescriptorParameter.MAX_VALUE, "20");
        getDescription().put(AttributeDescriptor.DescriptorParameter.MIN_VALUE, "5");
    }

}
