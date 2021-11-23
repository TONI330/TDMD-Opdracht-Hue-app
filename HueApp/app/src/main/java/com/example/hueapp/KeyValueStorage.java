package com.example.hueapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.StringRes;

public class KeyValueStorage {


    public static void setValue(Activity activity, @StringRes int stringID, String value) {
        setValue(activity, activity.getString(stringID), value);
    }

    public static void setValue(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getValue(Activity activity,String key) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }


    public static String getValue(Activity activity,@StringRes int stringID) {
        return getValue(activity, activity.getString(stringID));
    }
}
