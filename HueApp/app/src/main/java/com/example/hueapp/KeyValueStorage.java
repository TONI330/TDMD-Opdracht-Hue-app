package com.example.hueapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.StringRes;

public class KeyValueStorage {


    public static boolean setValue(Activity activity, @StringRes int stringID, String value) {
       return setValue(activity, activity.getString(stringID), value);
    }

    public static boolean setValue(Activity activity, String key, String newValue) {

        String oldValue = getValue(activity, key);
        if (oldValue.equals(newValue))
            return false;

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, newValue);
        editor.apply();
        return true;
    }

    public static String getValue(Activity activity,String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }


    public static String getValue(Activity activity,@StringRes int stringID) {
        return getValue(activity, activity.getString(stringID));
    }
}
