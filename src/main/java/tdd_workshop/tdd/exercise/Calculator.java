package tdd_workshop.tdd.exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calculator {
    public static int add(int input1, int input2) {
        return input1 + input2;
    }

    public static int compute(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("unsupported expression");
        }
        var firstNumber = Integer.parseInt(parts[0]);
        var operation = parts[1];
        var secondNumber = Integer.parseInt(parts[2]);
        switch (operation) {
            case "+":
                return add(firstNumber,secondNumber);
        }

        return 0;
    }

    public static void main(String[] args) {
        String expression = Arrays.stream(args).collect(Collectors.joining(" "));
        int result = compute(expression);
        System.out.println(result);
    }
}
