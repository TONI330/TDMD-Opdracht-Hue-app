package com.example.hueapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bridge {
    private List<Lamp> lamps;

    public Bridge() {
        Random random = new Random();
        this.lamps = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            lamps.add(new TestLamp(random.nextInt() + ""));
        }
    }

    static class TestLamp implements Lamp
    {
        private final String LOGTAG = TestLamp.class.getName();

        private String id;

        public TestLamp(String id) {
            this.id = id;
        }

        @Override
        public boolean getState() {
            return false;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public void on() {
            Log.i(LOGTAG, "on: ");
        }

        @Override
        public void off() {
            Log.i(LOGTAG, "off: ");
        }

        @Override
        public void toggle() {
            Log.i(LOGTAG, "toggle: ");
        }
    }


    public List<Lamp> getLamps() {
        return lamps;
    }
}
