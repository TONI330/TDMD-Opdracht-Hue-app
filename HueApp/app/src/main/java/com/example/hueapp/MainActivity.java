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

        startApi();
        //
        //FragmentManager manager = getSupportFragmentManager();
        //FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.overviewFragment, OverviewFragment.class, null);
        //transaction.addToBackStack(null);
        //transaction.commit();
    }

    private void startApi() {
        HueApiManager apiManager = new HueApiManager(this);
        apiManager.getLights();
        Lamp lamp = new Bridge.TestLamp("2", "00:17:88:01:00:d4:12:08-0b");
        apiManager.setLight(lamp, true);
    }
}