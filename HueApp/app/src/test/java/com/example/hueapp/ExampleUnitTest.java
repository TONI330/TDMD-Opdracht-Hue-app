package com.example.hueapp;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Random;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addLampTest()
    {
        Bridge bridge = new Bridge();
        Bridge.TestLamp testLamp = new Bridge.TestLamp(1234567 + "", "henk de lamp");
        bridge.getLamps().add(testLamp);
        assertTrue(bridge.getLamps().contains(testLamp));


    }

}