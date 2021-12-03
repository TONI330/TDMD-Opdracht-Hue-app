package com.example.hueapp;

import androidx.annotation.NonNull;

import com.example.hueapp.util.ColorConverter;

import java.util.Arrays;

public class HueLamp implements Lamp{

    private boolean state;
    private String id;
    private String name;

    private LightController lightController;

    public HueLamp(String name, String id, boolean state,float[] hsv, LightController lightController) {
        this.state = state;
        this.id = id;
        this.name = name;
        this.lightController = lightController;
        this.hsv = hsv;
    }

    @Override
    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
        lightController.setLight(this, state);
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void on() {
        setState(true);
    }

    @Override
    public void off() {
        setState(false);
    }

    @Override
    public void toggle() {
        setState(!state);
    }

    private final float[] hsv;

    @Override
    public void setColor(float[] hsv) {
        if (this.hsv[0] != hsv[0]) {
            System.arraycopy(hsv, 0, this.hsv, 0, hsv.length);
            float[] floats = ColorConverter.hsvToHueColor(hsv);
            lightController.setLightColor(this,floats);
        }
    }

    @Override
    public float[] getColor() {
        return this.hsv;
    }

    @NonNull
    @Override
    public String toString() {
        return "HueLamp{" +
                "state=" + state +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
