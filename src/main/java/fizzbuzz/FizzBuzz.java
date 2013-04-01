package fizzbuzz;

import static java.lang.String.format;

class FizzBuzz {

    private final Integer number;

    FizzBuzz(Integer number) {
        if (number == null) {
            throw new NullPointerException("FizzBuzz number can't be null");
        }
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
        StringBuilder result = new StringBuilder();
        if (isMultipleOf(3)) {
            result.append("Fizz");
        }
        if (isMultipleOf(5)) {
            result.append("Buzz");
        }
        if (result.length() > 0) {
            return result.toString();
        }
        return number.toString();
    }

    private boolean isMultipleOf(Integer multiple) {
        return number % multiple == 0;
    }
}
