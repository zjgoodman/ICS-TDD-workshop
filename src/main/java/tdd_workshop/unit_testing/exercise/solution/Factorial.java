package tdd_workshop.unit_testing.exercise.solution;

public class Factorial {
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative number");
        } else if (n == 0) {
            return 1;
        } else {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
}
