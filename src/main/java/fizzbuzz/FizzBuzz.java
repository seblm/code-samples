package fizzbuzz;

import static java.lang.String.format;

class FizzBuzz {

    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final int FIZZ_NUMBER = 3;
    private static final int BUZZ_NUMBER = 5;
    private final Integer number;

    FizzBuzz(Integer number) {
        this.number = number;
    }

    public static void main(String... args) {
        for (Integer i = 1; i <= 100; i++) {
            System.out.print(format("%8s ", new FizzBuzz(i)));
            if (i % 10 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        if (isMultipleOf(FIZZ_NUMBER) && isMultipleOf(BUZZ_NUMBER)) {
            return FIZZ + BUZZ;
        }
        if (isMultipleOf(FIZZ_NUMBER)) {
            return FIZZ;
        }
        if (isMultipleOf(BUZZ_NUMBER)) {
            return BUZZ;
        }
        return number.toString();
    }

    private boolean isMultipleOf(Integer multiple) {
        return number % multiple == 0;
    }
}
