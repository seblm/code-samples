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
        this.values = new ArrayList<>(values);
    }
}
