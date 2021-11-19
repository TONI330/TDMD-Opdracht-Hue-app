package com.example.hueapp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bridge {
    private List<Lamp> lamps;

    public Bridge() {

        this.lamps = new ArrayList<>();

        Random random = new Random();


        lamps.add(new TestLamp("henk de lamp",random.nextLong()+""));
        lamps.add(new TestLamp("piet de discobol",random.nextLong()+""));
        lamps.add(new TestLamp("jan de ledstrip",random.nextLong()+""));
        lamps.add(new TestLamp("klaasje de kernreactor",random.nextLong()+""));
        lamps.add(new TestLamp("harry hallogeen",random.nextLong()+""));
        lamps.add(new TestLamp("olaf oled",random.nextLong()+""));


    }

    static class TestLamp implements Lamp
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
            Log.i(LOGTAG, "Turned: "+ name +" on");
        }

        @Override
        public void off() {
            Log.i(LOGTAG, "Turned: "+ name +" off");
        }

        @Override
        public void toggle() {
            Log.i(LOGTAG, "Toggled: "+ name);
        }

        @Override
        public void setColor(float[] hsv) {
            Log.i(LOGTAG, "setColor: "+ Arrays.toString(hsv));
        }

        @NonNull
        @Override
        public String toString() {
            return "TestLamp{" +
                    "LOGTAG='" + LOGTAG + '\'' +
                    ", name='" + name + '\'' +
                    ", uiniqueId='" + uiniqueId + '\'' +
                    '}';
        }
    }


    public List<Lamp> getLamps() {
        return lamps;
    }
}
