package home.intexsoft.bank_application.dto.action;

import java.util.Comparator;

public class ActionDtoComparator implements Comparator<ActionDto> {

    @Override
    public int compare(ActionDto o1, ActionDto o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
