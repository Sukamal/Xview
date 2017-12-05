package com.everi.xview.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sukamal on 17/2/17.
 */

public class AppPreference {

    private  final String PREFERENCE_NAME = "everi.xview.pref";
    private SharedPreferences appShared;
    private SharedPreferences.Editor appEditor;

    public AppPreference(Context context)
    {
        this.appShared = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        this.appEditor = appShared.edit();


    }

    public void clearAllPreferences(){
        appEditor.clear();
        appEditor.commit();
    }

    public void clearPreferences(String key) {
        appEditor.remove(key);
        appEditor.commit();
    }

    public void saveBooleanValue(String touple, boolean data) {

        appEditor.putBoolean(touple, data);
        appEditor.commit();
    }

    public boolean getBooleanValue(String touple) {

        return appShared.getBoolean(touple, false);
    }

    public void saveStringValue(String key, String value) {

        appEditor.putString(key, value);
        appEditor.commit();
    }

    public String getStringValue(String Key) {

        return appShared.getString(Key, "");
    }

    public void saveIntValue(String key, int value) {

        appEditor.putInt(key, value);
        appEditor.commit();
    }

    public int getIntValue(String key) {
        return appShared.getInt(key, -1);
    }


    public enum TransactionFilterTag {
        TAG_MERCHANT,
        TAG_STATUS,
        TAG_TYPE,
        TAG_DATE_FROM,
        TAG_DATE_TO;
    }

    public enum MenuStorage {
        TAG_MENU;
    }
}
