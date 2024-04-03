package tdd_workshop.tdd.exercise.solution;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;


interface Operation extends BiFunction<Integer, Integer, Integer> {}
@AllArgsConstructor
@EqualsAndHashCode
public class Expression {
    public char operator;
    public int input1;
    public int input2;

    private static final Set<Character> DELIMITERS = Set.of('+','-','*','/');

    public Expression(String expression){
        List<String> strings = splitString(expression, DELIMITERS);
        int input1 = Integer.parseInt(strings.get(0));
        char operation = strings.get(1).charAt(0);
        int input2 = Integer.parseInt(strings.get(2));
        this.operator = operation;
        this.input1 = input1;
        this.input2 = input2;
    }

    public static List<String> splitString(String input, Set<Character> delimiters) {
        List<String> strings = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (char character : input.toCharArray()) {
            if (delimiters.contains(character)) {
                strings.add(builder.toString());
                strings.add(String.valueOf(character));
                builder = new StringBuilder();
            } else {
                builder.append(character);
            }
        }
        strings.add(builder.toString());
        return strings.stream().filter(string -> !string.isEmpty()).toList();
    }

    public int evaluate() {
        final Operation operation;
        if (this.operator == '+') {
            operation = Calculator::add;
        } else if (this.operator == '-') {
            operation = Calculator::subtract;
        } else if (this.operator == '*') {
            operation = Calculator::multiply;
        } else if (this.operator == '/') {
            operation = Calculator::divide;
        } else {
            throw new IllegalArgumentException("Unsupported operator");
        }
        return operation.apply(input1, input2);
    }
}
