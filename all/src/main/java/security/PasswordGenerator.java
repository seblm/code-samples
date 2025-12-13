package security;

import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class PasswordGenerator {
    public static final Integer DEFAULT_SIZE = 16;

    public String generate(Integer size) {
        final var chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .!:@#$%&*()_+=|<>?{}[]-/";
        final var random = new Random();

        return range(0, size)
                .mapToObj(_ -> chars.charAt(random.nextInt(chars.length())))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    static void main(String... args) {
        var length = DEFAULT_SIZE;
        if (args.length == 1) {
            try {
                length = Integer.parseInt(args[0]);
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
