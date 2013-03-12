package scanner;

import org.junit.Test;

import java.util.Scanner;

import static org.fest.assertions.Assertions.assertThat;

public class ScannerTest {

    @Test
    public void test() {
        Scanner scanner = new Scanner("2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 N can't update at Position{x=5, y=6}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 E can't update at Position{x=6, y=5}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 5 5 E can't update at Position{x=6, y=5}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 S can't update at Position{x=0, y=-1}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 W can't update at Position{x=-1, y=0}\n" +
                "2013-01-29 21:57:21,979 WARN com.github.vspiewak.mowitnow.mower.domain.XEngine - scene constraint: 0 0 W can't update at Position{x=-1, y=0}\n" +
                "2013-01-29 21:57:21,979 INFO com.github.vspiewak.mowitnow.mower.app.App - 5 5 E\n" +
                "0 0 W\n" +
                "\n").useDelimiter("\n");

        assertThat(scanner.findInLine("com\\.github\\.vspiewak\\.mowitnow\\.mower\\.app\\.App - (.+)"))
                .isNotNull()
                .isEqualTo("com\\.github\\.vspiewak\\.mowitnow\\.mower\\.app\\.App - (.+)");
    }

}
