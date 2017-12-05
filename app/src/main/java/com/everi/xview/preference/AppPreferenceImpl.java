package com.everi.xview.preference;

import android.content.SharedPreferences;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class AppPreferenceImpl implements AppPreferenceInterface{

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

    public enum User {
        TAG_USER_ID,
        TAG_USER_PASSWORD;
    }

    public enum AppConfig {
        TAG_BASE_URL;
    }


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor appEditor;

    public AppPreferenceImpl(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        appEditor = sharedPreferences.edit();
    }

    @Override
    public void clearAllPreferences() {
        appEditor.clear();
        appEditor.commit();
    }

    @Override
    public void clearPreferences(String key) {
        appEditor.remove(key);
        appEditor.commit();
    }

    @Override
    public void saveBooleanValue(String touple, boolean data) {
        appEditor.putBoolean(touple, data);
        appEditor.commit();
    }

    @Override
    public void saveStringValue(String key, String value) {
        appEditor.putString(key, value);
        appEditor.commit();
    }

    @Override
    public void saveIntValue(String key, int value) {
        appEditor.putInt(key, value);
        appEditor.commit();
    }

    @Override
    public boolean getBooleanValue(String touple) {
        return sharedPreferences.getBoolean(touple, false);
    }

    @Override
    public boolean getBooleanValue(String touple, boolean defaultVal) {
        return sharedPreferences.getBoolean(touple, defaultVal);
    }

    @Override
    public String getStringValue(String Key) {
        return sharedPreferences.getString(Key, "");
    }

    @Override
    public String getStringValue(String Key, String defaultVal) {
        return sharedPreferences.getString(Key, defaultVal);
    }

    @Override
    public int getIntValue(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    @Override
    public int getIntValue(String key, int defaultVal) {
        return sharedPreferences.getInt(key, defaultVal);
    }
}
