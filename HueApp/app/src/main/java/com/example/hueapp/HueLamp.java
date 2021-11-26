package com.example.hueapp;

import androidx.annotation.NonNull;

public class HueLamp implements Lamp{

    private boolean state;
    private String id;
    private String name;
    private float[] color;

    private LightController lightController;

    public HueLamp(String name, String id, boolean state, LightController lightController) {
        this.state = state;
        this.id = id;
        this.name = name;
        this.lightController = lightController;
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

    private float[] hsv;

    @Override
    public void setColor(float[] hsv) {
        this.color = hsv;
        hsv[0] = hsv[0] / 365f * 65535f;
        hsv[1] = hsv[1] * 254f;
        hsv[2] = hsv[2] * 254f;

        if (this.hsv == null || this.hsv[0] != hsv[0]) {
            this.hsv = hsv;
            lightController.setLightColor(this,this.hsv);
        }
    }

    @Override
    public float[] getColor() {
        return this.color;
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
