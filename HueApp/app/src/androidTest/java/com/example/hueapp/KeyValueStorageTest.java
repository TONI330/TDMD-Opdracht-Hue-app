package com.example.hueapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class KeyValueStorageTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.hueapp", appContext.getPackageName());

    }
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void setNewValue() {
        //arrange
        String testValue = "testValue";

        //act
        KeyValueStorage.setValue(activityTestRule.getActivity(), "value", testValue);
        String actualValue = KeyValueStorage.getValue(activityTestRule.getActivity(), "value");

        //assert
        assertEquals(testValue, actualValue);
    }

    @Test
    public void setNewValueWithID() {
        //arrange
        int testKeyID = R.string.testKey;
        String expectedValue = "testedkey";

        //act
        KeyValueStorage.setValue(activityTestRule.getActivity(), testKeyID, "testedkey");
        String actualValue = KeyValueStorage.getValue(activityTestRule.getActivity(), testKeyID);

        //assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void checkNonExistingKey() {
        //arrange
        String assertValue = "";

        //act
        String actualValue = KeyValueStorage.getValue(activityTestRule.getActivity(), "yabadabadoo");

        //assert
        assertTrue(actualValue.isEmpty());
    }
    @Test
    public void checkDuplicateInput() {
        //arrange
        String oldValue = "val";
        String newValue = "val";

        //act
        KeyValueStorage.setValue(activityTestRule.getActivity(), "keyValue", oldValue);
        assertFalse(KeyValueStorage.setValue(activityTestRule.getActivity(), "keyValue", newValue));

    }

}
