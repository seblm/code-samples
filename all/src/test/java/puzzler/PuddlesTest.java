package puzzler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.logging.Level.OFF;
import static java.util.logging.Logger.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

public class PuddlesTest {
    @BeforeAll
    public static void shutdownLogger() {
        getLogger(Puddles.class.getName()).setLevel(OFF);
    }

    @Test
    public void acceptance_test() {
        assertThat(new Puddles(2, 5, 1, 2, 3, 4, 7, 7, 6).rain()).isEqualTo(10);
    }

    @Test
    public void another_acceptance_test() {
        assertThat(new Puddles(2, 5, 1, 3, 1, 2, 1, 7, 7, 6).rain()).isEqualTo(17);
    }

    @Test
    public void one_puddle() {
        assertThat(new Puddles(1, 0, 1).rain()).isEqualTo(1);
    }

    @Test
    public void one_puddle_more_larger() {
        assertThat(new Puddles(1, 0, 0, 1).rain()).isEqualTo(2);
    }

    @Test
    public void no_puddle() {
        assertThat(new Puddles(0, 0, 1).rain()).isEqualTo(0);
    }

}
