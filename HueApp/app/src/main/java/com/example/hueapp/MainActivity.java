package com.example.hueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HueApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.apiManager = new HueApiManager(this);

        startApi();


        //
        //FragmentManager manager = getSupportFragmentManager();
        //FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.overviewFragment, OverviewFragment.class, null);
        //transaction.addToBackStack(null);
        //transaction.commit();
    }

    private void startApi() {
        this.apiManager.getLights();
    }
}