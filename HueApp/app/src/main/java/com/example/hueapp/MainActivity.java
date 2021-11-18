package com.example.hueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private HueApiManager apiManager;
    private LampsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.apiManager = new HueApiManager(this);
        startApi();


        mViewModel = new ViewModelProvider(this).get(LampsViewModel.class);
        mViewModel.getSelected().observe(this, this::listItemPressed);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(R.id.portraitFragment, OverviewFragment.class, null);
        } else {
            transaction.replace(R.id.overviewFragment, OverviewFragment.class, null);
            transaction.replace(R.id.detailFragment, DetailFragment.class, null);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void listItemPressed(Lamp lamp) {
        FragmentManager manager = getSupportFragmentManager();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            goToDetail(manager);
        } else {
            updateDetail(manager);
        }
    }

    private void goToDetail(FragmentManager manager) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.portraitFragment, DetailFragment.class, null);
        transaction.addToBackStack("portraitDetail");
        transaction.commit();
    }


    private void updateDetail(FragmentManager manager) {
        DetailFragment fragmentById = (DetailFragment) manager.findFragmentById(R.id.detailFragment);
        if (fragmentById == null) {
            return;
        }
        fragmentById.update();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void startApi() {
        this.apiManager.getLights();
    }

    public void linkButtonPressed(View view) {
        this.apiManager.getIpAddress();
    }
}