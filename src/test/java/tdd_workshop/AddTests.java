package tdd_workshop;

import static tdd_workshop.Add.add;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTests {
    @Test
    public void testAdd() {
        int number1 = 1;
        int number2 = 2;
        int expectedSum = 3; // 1 + 2 = 3
        int actualSum = add(number1, number2);
        assertEquals(expectedSum, actualSum);
    }
}
