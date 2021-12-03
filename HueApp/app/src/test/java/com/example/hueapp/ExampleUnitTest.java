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
        HueLamp hueLamp = new HueLamp("test", "1", true,new float[3], new LightController() {
            @Override
            public void setLight(Lamp lamp, boolean state) {

            }

            @Override
            public void setLightColor(Lamp lamp, float[] hsv) {

            }
        });
        bridge.getLamps().add(hueLamp);
        assertTrue(bridge.getLamps().contains(hueLamp));


    }

}