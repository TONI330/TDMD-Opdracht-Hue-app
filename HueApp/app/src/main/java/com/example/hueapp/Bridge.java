package com.example.hueapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bridge {
    private List<Lamp> lamps;

    public Bridge() {

        this.lamps = new ArrayList<>();


        //todo remove this test code
        Random random = new Random();


        lamps.add(new TestLamp(random.nextLong()+"","henk del amp"));
        lamps.add(new TestLamp(random.nextLong()+"","klaas de lamp"));
        lamps.add(new TestLamp(random.nextLong()+"","piet de lamp"));
        lamps.add(new TestLamp(random.nextLong()+"","gerda de ledstrip"));
        lamps.add(new TestLamp(random.nextLong()+"","Jan de discobol"));


    }

    class TestLamp implements Lamp
    {
        private final String LOGTAG = TestLamp.class.getName();

        private String name;
        private String uiniqueId;

        public TestLamp(String name, String uiniqueid) {
            this.name = name;
            this.uiniqueId = uiniqueid;
        }

        @Override
        public boolean getState() {
            return false;
        }

        @Override
        public String getID() {
            return this.uiniqueId;
        }

        @Override
        public String getName() {
            return this.name;
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
