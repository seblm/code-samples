package selma.model;

import java.util.ArrayList;
import java.util.List;

public class DefensiveCopy {
    private List<String> values;

    public DefensiveCopy() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        List<String> newValues = new ArrayList<>();
        newValues.addAll(values);
        this.values = newValues;
    }
}
