package tdd_workshop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestCase<T,V> {
    public final T input;
    public final V expectedOutput;
}
