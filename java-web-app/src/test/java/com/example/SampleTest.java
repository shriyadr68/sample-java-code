package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    public void testAddition() {
        Sample sample = new Sample();
        assertEquals(5, sample.add(2, 3));
    }

    @Test
    public void testString() {
        Sample sample = new Sample();
        assertTrue(sample.startsWithH("Hello"));
    }
}
