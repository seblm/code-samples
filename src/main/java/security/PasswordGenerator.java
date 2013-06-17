package security;

public class PasswordGenerator {

    public String generate(Integer size) {
        final String chars = "" +
                "0123456789" +
                "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "_-";

        final StringBuilder password = new StringBuilder(16);

        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.substring(index, index + 1));
        }

        return password.toString();
    }

    public static void main(String... args) {
        Integer length = 16;
        if (args.length == 1) {
            try {
                length = new Integer(args[0]);
                if (length < 1 || length > 100) {
                    length = 16;
                }
            } catch (NumberFormatException e) {
                System.err.println("usage: java " + PasswordGenerator.class.getName() + " [size]");
            }
        }

        System.out.println(new PasswordGenerator().generate(length));
    }

}
