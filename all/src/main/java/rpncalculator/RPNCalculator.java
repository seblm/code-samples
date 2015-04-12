package rpncalculator;

import java.util.*;
import java.util.function.IntBinaryOperator;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

/**
 * Implementation of https://gist.github.com/dlresende/274194dd6ec993eb5ec5
 */
public class RPNCalculator {
    private Deque<Integer> numbers = new ArrayDeque<>();

    public String compute(String expression) {
        numbers.clear();
        
        stream(expression.split("\\s+"))
                .forEach(element -> numbers.addLast(
                        Operation.parse(element)
                                .map(operation -> operation.compute(numbers.removeLast(), numbers.removeLast()))
                                .orElseGet(() -> parseInt(element))
                ));
        
        return numbers.stream().map(Object::toString).collect(joining(" "));
    }

    private enum Operation {
        ADD('+', (right, left) -> left + right),
        SUBSTRACT('-', (right, left) -> left - right),
        DIVIDE('/', (right, left) -> left / right),
        MULIPLY('*', (right, left) -> left * right);

        private static Map<String, Operation> operationByCharacter;
        private final char character;
        private final IntBinaryOperator function;

        Operation(char character, IntBinaryOperator function) {
            this.character = character;
            this.function = function;
        }

        private int compute(int left, int right) {
            return function.applyAsInt(left, right);
        }

        private static Optional<Operation> parse(String operationAsString) {
            return Optional.ofNullable(getOrCreateOperationByCharacter().get(operationAsString));
        }

        private static Map<String, Operation> getOrCreateOperationByCharacter() {
            if (operationByCharacter == null) {
                operationByCharacter = stream(values()).collect(toMap(
                                operation -> Character.toString(operation.character),
                                operation -> operation)
                );
            }
            return operationByCharacter;
        }
    }
}
