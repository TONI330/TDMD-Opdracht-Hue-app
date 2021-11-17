package com.example.hueapp;

public class HueLamp implements Lamp{

    private boolean state;
    private String id;
    private String name;

    public HueLamp(String name, String id, boolean state) {
        this.state = state;
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean getState() {
        return this.state;
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
