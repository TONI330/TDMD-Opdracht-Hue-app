package com.example.hueapp;

public interface Lamp{


    boolean getState();


    String getID();

    void on();

    void off();

    void toggle();
}
