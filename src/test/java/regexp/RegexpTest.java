package regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fest.assertions.Assertions.assertThat;

public class RegexpTest {

    @Test
    public void test() {
        Matcher multipleMatch = Pattern.compile("which of the following numbers is the largest: ((?:\\d+, ){3})(\\d+)")
                .matcher("which of the following numbers is the largest: 43, 83, 924, 825");
        assertThat(multipleMatch.groupCount()).isEqualTo(2);

        assertThat(multipleMatch.matches()).isTrue();

        assertThat(multipleMatch.group(1)).isEqualTo("43, 83, 924, ");
        assertThat(multipleMatch.group(2)).isEqualTo("825");
    }

    @Test
    public void testMower() {
        Pattern pattern = Pattern.compile("[0-9]+ [0-9]+?"); // +? = one or more times according to JavaDoc
        assertThat(pattern.matcher("50 43").matches()).isTrue();
    }

    @Test
    public void testMowerRead() {
        Pattern pattern = Pattern.compile("[0-9]+ [0-9]+ [NESW]?");
        assertThat(pattern.matcher("1 1 ").matches()).isTrue(); // this is bad
    }

    @Test
    public void group_count_always_return_number_of_groups_even_if_matcher_fail() {
        Pattern pattern = Pattern.compile("(a) (b) (c)");

        assertThat(pattern.matcher("a b c").groupCount()).isEqualTo(3);
        assertThat(pattern.matcher("another string").groupCount()).isEqualTo(3);
    }
}
