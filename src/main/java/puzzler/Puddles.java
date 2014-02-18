package puzzler;

import java.util.Comparator;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * Inspired from http://qandwhat.apps.runkite.com/i-failed-a-twitter-interview
 */
public class Puddles {
    private final Integer[] wallHeights;
    private final Integer maxWallHeights;

    public Puddles(Integer... wallHeights) {
        this.wallHeights = wallHeights;
        this.maxWallHeights = Stream.of(wallHeights).max(Comparator.<Integer>naturalOrder()).get();
    }

    public int rain() {
        return rain(0);
    }

    private int rain(int previousRain) {
        print();
        int rain = 0;
        for (int x = 0; x < wallHeights.length; x++) {
            if (isFlatOrUp(wallHeights[x], x, (i) -> i - 1)
                    && isFlatOrUp(wallHeights[x], x, (i) -> i + 1)) {
                rain++;
                wallHeights[x]++;
            }
        }
        if (rain == previousRain) {
            return 0;
        }
        return rain + rain();
    }

    private boolean isFlatOrUp(int level, int x, IntUnaryOperator incrementOrDecrement) {
        int nextX = incrementOrDecrement.applyAsInt(x);
        return isValidCoordinate(nextX)
                && (wallHeights[nextX] > level || wallHeights[nextX] == level && isFlatOrUp(level, nextX, incrementOrDecrement));
    }

    private boolean isValidCoordinate(int x) {
        return x >= 0 && x < wallHeights.length;
    }

    private void print() {
        for (int y = maxWallHeights; y >= 0; y--) {
            for (int x = 0; x < wallHeights.length; x++) {
                if (wallHeights[x] == y) {
                    System.out.print('_');
                } else {
                    System.out.print(' ');
                }
                if (x < wallHeights.length - 1) {
                    if (wallHeights[x].equals(wallHeights[x + 1]) && wallHeights[x] == y) {
                        System.out.print('_');
                    } else if (wallHeights[x] > y && y >= wallHeights[x + 1]) {
                        System.out.print('|');
                    } else if (wallHeights[x] <= y && y < wallHeights[x + 1]) {
                        System.out.print('|');
                    } else {
                        System.out.print(' ');
                    }
                }
            }
            System.out.println();
        }
        for (int wallHeight : wallHeights) {
            System.out.print(wallHeight + " ");
        }
        System.out.println();
    }
}
