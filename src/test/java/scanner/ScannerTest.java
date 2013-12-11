package scanner;

import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class ScannerTest {

    @Test
    public void should_read_6_lines_and_match_pattern() {
        Scanner scanner = new Scanner("2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 N can't update at Position{x=5, y=6}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 E can't update at Position{x=6, y=5}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 E can't update at Position{x=6, y=5}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 S can't update at Position{x=0, y=-1}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 W can't update at Position{x=-1, y=0}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 W can't update at Position{x=-1, y=0}\n" +
                "2013-01-29 21:57:21,979 INFO com.github.vspiewak.mowitnow.mower.app.App - 5 5 E\n" +
                "0 0 W\n" +
                "\n").useDelimiter("\n");
        Pattern mowerPosition = Pattern.compile("com\\.github\\.vspiewak\\.mowitnow\\.mower\\.app\\.App - (.+)");
        for (Integer i = 0; i < 6; i++) {
            System.out.println(scanner.nextLine());
        }

        scanner.findInLine(mowerPosition);

        assertThat(scanner.match().group(1)).isEqualTo("5 5 E");
    }

}
