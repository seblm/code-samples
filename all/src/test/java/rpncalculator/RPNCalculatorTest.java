package rpncalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RPNCalculatorTest {

    private RPNCalculator rpnCalculator;

    @BeforeEach
    public void initializeRpnCalculator() {
        rpnCalculator = new RPNCalculator();
    }

    @Test
    public void should_return_digit() {
        assertThat(rpnCalculator.compute("1")).isEqualTo("1");
    }

    @Test
    public void should_return_digits() {
        assertThat(rpnCalculator.compute("123")).isEqualTo("123");
    }
    
    @Test
    public void should_return_computed_addition() {
        assertThat(rpnCalculator.compute("1 2 +")).isEqualTo("3");
    }

    @Test
    public void should_return_computed_division() {
        assertThat(rpnCalculator.compute("20 5 /")).isEqualTo("4");
    }

    @Test
    public void should_return_computed_multiplication() {
        assertThat(rpnCalculator.compute("5 8 *")).isEqualTo("40");
    }

    @Test
    public void should_return_computed_soustraction() {
        assertThat(rpnCalculator.compute("6 3 -")).isEqualTo("3");
    }

    @Test
    public void should_return_priotized_operations() {
        assertThat(rpnCalculator.compute("4 2 + 3 -")).isEqualTo("3");
        assertThat(rpnCalculator.compute("3 5 8 * 7 + *")).isEqualTo("141");
    }

    @Test
    public void should_display_digits_with_reduced_operation() {
        assertThat(rpnCalculator.compute("7 2 - 3 4")).isEqualTo("5 3 4");
    }
}