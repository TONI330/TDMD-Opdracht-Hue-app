package com.example.hueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestIP();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.overviewFragment, OverviewFragment.class, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void RequestIP() {
        HueApiManager apiManager = new HueApiManager(this);
        apiManager.getLights();
        Lamp lamp = new Bridge.TestLamp("2");
        apiManager.setLight(lamp, false);
    }
}