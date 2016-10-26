import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SolutionTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Rule
    public final SystemOutRule out = new SystemOutRule().enableLog().muteForSuccessfulTests();

    private final String input;

    private final String output;

    public SolutionTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters(name = "{0} → {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"input", "output"},
        });
    }

    @Test
    public void verify() {
        systemInMock.provideLines(input);

        Solution.main(null);

        assertThat(out.getLog()).isEqualTo(output + '\n');
    }

}
