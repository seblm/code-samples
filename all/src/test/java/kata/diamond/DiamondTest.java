package kata.diamond;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiamondTest {
    @Test
    public void should_print_A() {
        assertThat(new Diamond('A').toString()).isEqualTo("A");
    }

    @Test
    public void should_print_B() {
        assertThat(new Diamond('B').toString()).isEqualTo("""
                 A\s
                B B
                 A\s""");
    }

    @Test
    public void should_print_C() {
        assertThat(new Diamond('C').toString()).isEqualTo("""
                  A \s
                 B B\s
                C   C
                 B B\s
                  A \s""");
    }
}