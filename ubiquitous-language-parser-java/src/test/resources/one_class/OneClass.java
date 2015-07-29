package one_class;

public class OneClass {

    private final SomeImportantType someImportantType;

    public OneClass(SomeImportantType someImportantType) {
        this.someImportantType = someImportantType;
    }
    
    public String businessRule(String argument) {
        return argument.toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(new OneClass(new SomeImportantType("value").businessRule("argument"));
    }
    
    private static class SomeImportantType {
        private final String businessData;

        public SomeImportantType(String businessData) {
            this.businessData = businessData;
        }
    }
}