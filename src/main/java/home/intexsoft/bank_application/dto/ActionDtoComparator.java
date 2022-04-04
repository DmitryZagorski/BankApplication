package home.intexsoft.bank_application.dto;

import home.intexsoft.bank_application.dto.ActionDto;

import java.util.Comparator;

public class ActionDtoComparator implements Comparator<ActionDto> {

    @Override
    public int compare(ActionDto o1, ActionDto o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
