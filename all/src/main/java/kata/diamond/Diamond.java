package kata.diamond;

import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Diamond {

    private static final UnaryOperator<Character> increment = character -> (char) (character + 1);
    private static final UnaryOperator<Character> decrement = character -> (char) (character - 1);

    private final char startChar;
    private final int distanceFromA;
    private final String diamond;

    public Diamond(char startChar) {
        this.startChar = startChar;
        this.distanceFromA = startChar - 'A';
        this.diamond =
                fromAToStartCharToA().map(line ->
                        fromStartToAToStart().map(column ->
                                Objects.equals(line, column) ? column : " ").collect(joining())).collect(joining("\n"));
    }

    private Stream<String> fromAToStartCharToA() {
        return Stream.concat(
                Stream.iterate('A', increment).limit(distanceFromA),
                Stream.iterate(startChar, decrement).limit(distanceFromA + 1)).map(Object::toString);
    }

    private Stream<String> fromStartToAToStart() {
        return Stream.concat(
                Stream.iterate(startChar, decrement).limit(distanceFromA),
                Stream.iterate('A', increment).limit(distanceFromA + 1)).map(Object::toString);
    }

    @Override
    public String toString() {
        return diamond;
    }
}
