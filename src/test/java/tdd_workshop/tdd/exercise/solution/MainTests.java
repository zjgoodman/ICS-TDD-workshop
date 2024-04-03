package tdd_workshop.tdd.exercise.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testOneArg() {
        String[] args = {"1+1"};
        Main.main(args);

        Assertions.assertEquals("2", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testSeparateArgs() {
        String[] args = {"1", "+", "1"};
        Main.main(args);

        Assertions.assertEquals("2", outputStreamCaptor.toString().trim());
    }
}
