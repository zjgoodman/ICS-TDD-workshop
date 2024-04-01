package tdd_workshop.tdd.exercise.solution;

public class Calculator {
    public static int add(int input1, int input2) {
        return input1 + input2;
    }

    public static int subtract(int input1, int input2) {
        return input1 - input2;
    }

    public static int multiply(int input1, int input2) {
        return input1 * input2;
    }

    public static int divide(int input1, int input2) {
        return (int) ((double) input1 / (double) input2);
    }
}
