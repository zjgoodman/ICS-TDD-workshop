package tdd_workshop.tdd.exercise.solution;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.function.BiFunction;


interface Operation extends BiFunction<Integer, Integer, Integer> {}
@AllArgsConstructor
@EqualsAndHashCode
public class Expression {
    public char operator;
    public int input1;
    public int input2;

    public Expression(String expression){
        var array = expression.toCharArray();
        int input1 = Integer.parseInt(String.valueOf(array[0]));
        char operation = array[1];
        int input2 = Integer.parseInt(String.valueOf(array[2]));
        this.operator = operation;
        this.input1 = input1;
        this.input2 = input2;
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
