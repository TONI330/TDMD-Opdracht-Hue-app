package com.example.hueapp;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ApiIntegrationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    //ENTRY CRITERIA
    //1. All of the unittests should be successfully executed
    //2. The LinkButton should be pressed
    @Test
    public void testApiConnection() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        HueApiManager apiManager = new HueApiManager(activityTestRule.getActivity());
        String ip = "145.49.6.230:8000";
        KeyValueStorage.setValue(activityTestRule.getActivity(), R.string.chosenIp,ip);


        //Act
        apiManager.getIpAddress();
        //apiManager.getLights();
        LampsViewModel viewModel = new ViewModelProvider(activityTestRule.getActivity()).get(LampsViewModel.class);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        Assert.assertFalse(viewModel.getItems().isEmpty());
    }


    //ENTRY CRITERIA
    //Don't press the link button
    @Test
    public void linkingFailed()
    {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        HueApiManager apiManager = new HueApiManager(activityTestRule.getActivity());
        String ip = "145.49.6.230:8000";
        KeyValueStorage.setValue(activityTestRule.getActivity(), R.string.chosenIp,ip);


        //Act
        apiManager.getIpAddress();
        //apiManager.getLights();
        LampsViewModel viewModel = new ViewModelProvider(activityTestRule.getActivity()).get(LampsViewModel.class);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        Assert.assertFalse(viewModel.getIsConnected().getValue());

    }

}
