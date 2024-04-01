package tdd_workshop.tdd.exercise.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressionTests {
    @Test
    public void testParseExpression() {
        String inputString = "1+1";
        Expression expectedExpression = new Expression('+',1,1);
        Expression actualExpression = new Expression(inputString);
        Assertions.assertEquals(expectedExpression, actualExpression);
    }

    @Test
    public void testEvaluate(){
        Expression expression = new Expression('+',1,2);
        int expectedResult = 3;
        int actualResult = expression.evaluate();
        Assertions.assertEquals(expectedResult,actualResult);
    }
    @Test
    public void testEvaluateFromString() {
        runEvaluateTest("1+1", 2);
        runEvaluateTest("8+2", 10);
        runEvaluateTest("4-1", 3);
        runEvaluateTest("1-4", -3);
        runEvaluateTest("1*4", 4);
        runEvaluateTest("2*3", 6);
        runEvaluateTest("0*12", 0);
        runEvaluateTest("1/2", 0);
        runEvaluateTest("4/2", 2);
        runEvaluateTest("7/2", 3);
    }
    private void runEvaluateTest(String expression, int expectedResult){
        Expression expressionObject = new Expression(expression);
        int actualResult = expressionObject.evaluate();
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void testEvaluateUnsupportedOperation() {
        try {
            runEvaluateTest("2^2", 0);
            Assertions.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // this is the expected behavior
        }
    }
}
