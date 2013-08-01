package security;

import static java.lang.Math.random;

public class PasswordGenerator {

    public static final Integer DEFAULT_SIZE = 16;

    public String generate(Integer size) {
        final String chars = "" +
                "0123456789" +
                "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "_-";

        final StringBuilder password = new StringBuilder(size);

        for (Integer i = 0; i < size; i++) {
            password.append(chars.charAt(new Double(random() * chars.length()).intValue()));
        }

        return password.toString();
    }

    public static void main(String... args) {
        Integer length = DEFAULT_SIZE;
        if (args.length == 1) {
            try {
                length = new Integer(args[0]);
                if (length < 1 || length > 100) {
                    length = DEFAULT_SIZE;
                }
            } catch (NumberFormatException e) {
                System.err.println("usage: java " + PasswordGenerator.class.getName() + " [size]");
            }
        }

        System.out.println(new PasswordGenerator().generate(length));
    }

}
