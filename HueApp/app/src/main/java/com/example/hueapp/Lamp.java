package com.example.hueapp;

public interface Lamp{

    boolean getState();

    String getID();

    String getName();

    void on();

    void off();

    void toggle();

}
