package parameters;

public class Parameters {
    
    static void updateValuesFromParameters(int int_, Integer integer, String string, MyContainer c) {
        int_ += 1;
        integer += 1;
        string += "_";
        c.setValue("okay");
    }

    public static class MyContainer {
        private String value;

        public MyContainer(String value) {
            this.value = value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
