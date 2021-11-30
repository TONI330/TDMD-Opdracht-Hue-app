package com.example.hueapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class HueLampTest {

    HueLamp hueLamp;

    final static String name = "TestingLamp";
final static String id = "1";


    @Before
    public void onTestStart()
    {
        hueLamp = new HueLamp(name, id, false, new LightController() {
            @Override
            public void setLight(Lamp lamp, boolean state) {

            }

            @Override
            public void setLightColor(Lamp lamp, float[] hsv) {

            }
        });
    }

    @Test
    public void getState() {
        assertFalse(hueLamp.getState());
    }

    @Test
    public void setState() {
        hueLamp.setState(true);
        assertTrue(hueLamp.getState());
        hueLamp.setState(false);
        assertFalse(hueLamp.getState());
    }

    @Test
    public void getID() {
        assertEquals(id,hueLamp.getID());
    }

    @Test
    public void getName() {
        assertEquals(name,hueLamp.getName());
    }

    @Test
    public void on() {
        hueLamp.on();
        assertTrue(hueLamp.getState());
    }

    @Test
    public void off() {
        hueLamp.off();
        assertFalse(hueLamp.getState());
    }

    @Test
    public void toggle() {
        boolean lastState = hueLamp.getState();
        hueLamp.toggle();
        assertEquals(!lastState,hueLamp.getState());


    }

    @Test
    public void setColor() {
        float[] hsv = {365f,100f,100f};
        hueLamp.setColor(hsv);
        assertArrayEquals(hsv,hueLamp.getColor(),0f);

        hsv[2] = 50f;
        hueLamp.setColor(hsv);
        boolean equals = Arrays.equals(hsv, hueLamp.getColor());
        assertFalse(equals);

        hsv[0] = 50f;
        hueLamp.setColor(hsv);
        assertArrayEquals(hsv,hueLamp.getColor(),0f);

    }



    @Test
    public void getColor() {

        float[] color = hueLamp.getColor();
        boolean allZero = true;

        for (float v : color) {
            if (v != 0f) {
                allZero = false;
                break;
            }
        }
        assertTrue(allZero);


        float[] hsv = {365f,100f,100f};
        hueLamp.setColor(hsv);
        assertArrayEquals(hsv, hueLamp.getColor(),0f);
    }

}