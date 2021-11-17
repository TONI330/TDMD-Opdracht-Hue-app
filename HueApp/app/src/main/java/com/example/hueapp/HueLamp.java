package com.example.hueapp;

public class HueLamp implements Lamp{

    private boolean state;
    private String id;
    private String name;

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

    }

    @Override
    public void off() {

    }

    @Override
    public void toggle() {

    }

    @Override
    public String toString() {
        return "HueLamp{" +
                "state=" + state +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}