package fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void should_generate_fizz() throws Exception {
        assertThat(new FizzBuzz(3).toString()).isEqualTo("Fizz");
    }

    @Test
    public void should_generate_buzz() throws Exception {
        assertThat(new FizzBuzz(5).toString()).isEqualTo("Buzz");
    }

    @Test
    public void should_generate_number_if_not_multiple_of_3_or_5() throws Exception {
        assertThat(new FizzBuzz(1).toString()).isEqualTo("1");
    }

    @Test
    public void should_generate_fizzbuzz() throws Exception {
        assertThat(new FizzBuzz(15).toString()).isEqualTo("FizzBuzz");
    }

}
