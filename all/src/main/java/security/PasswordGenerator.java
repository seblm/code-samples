package security;

import java.util.Random;

import static java.util.stream.IntStream.range;

public class PasswordGenerator {
    public static final Integer DEFAULT_SIZE = 16;

    public String generate(Integer size) {
        final String chars = "" +
                "0123456789" +
                "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                " .!:@#$%&*()_+=|<>?{}[]-/";

        final Random random = new Random();
        final StringBuilder password = new StringBuilder(size);

        range(0, size).forEach((i) -> password.append(chars.charAt(random.nextInt(chars.length()))));

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
