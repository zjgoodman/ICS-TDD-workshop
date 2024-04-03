package tdd_workshop.tdd.exercise.solution;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String expression = Arrays.stream(args).collect(Collectors.joining());
        int result = new Expression(expression).evaluate();
        System.out.println(result);
    }
}
