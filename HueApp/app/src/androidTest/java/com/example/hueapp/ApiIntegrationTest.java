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
    private final String LANIPv4Address = "192.168.178.81:8000";
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    //Don't run these test simultaneously, run them one by one using their specific entry criteria

    //ENTRY CRITERIA
    //1. All of the unittests should be successfully executed
    //2. The LANIPv4Address final above should be set using cmd -> ipconfig -> IPv4 Address
    //3. The hue emulator should be started on port 8000
    //4. The Link Button should be pressed in the emulator within 20 seconds before running the test
    @Test
    public void testApiConnection() {
        //Arrange
        HueApiManager apiManager = new HueApiManager(activityTestRule.getActivity());
        KeyValueStorage.setValue(activityTestRule.getActivity(), R.string.chosenIp, LANIPv4Address);

        //Act
        apiManager.getIpAddress();
        LampsViewModel viewModel = new ViewModelProvider(activityTestRule.getActivity()).get(LampsViewModel.class);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        Assert.assertFalse(viewModel.getItems().isEmpty());
    }

    //ENTRY CRITERIA
    //Don't press the link button, restart the hue emulator application to undo linking
    @Test
    public void linkingFailed()
    {
        //Arrange
        HueApiManager apiManager = new HueApiManager(activityTestRule.getActivity());
        KeyValueStorage.setValue(activityTestRule.getActivity(), R.string.chosenIp, LANIPv4Address);
        KeyValueStorage.setValue(activityTestRule.getActivity(), R.string.username, "");

        //Act
        apiManager.getIpAddress();
        LampsViewModel viewModel = new ViewModelProvider(activityTestRule.getActivity()).get(LampsViewModel.class);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        if(viewModel.getIsConnected().getValue() == null) {
            Assert.assertFalse(false);
            return;
        }
        Assert.assertFalse(viewModel.getIsConnected().getValue());
    }
}
