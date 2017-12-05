package com.everi.xview.preference;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public interface AppPreferenceInterface {

    void clearAllPreferences();
    void clearPreferences(String key);

    void saveBooleanValue(String touple, boolean data);
    void saveStringValue(String key, String value);
    void saveIntValue(String key, int value);

    boolean getBooleanValue(String touple);
    boolean getBooleanValue(String touple,boolean defaultVal);
    String getStringValue(String Key);
    String getStringValue(String Key,String defaultVal);
    int getIntValue(String key);
    int getIntValue(String key,int defaultVal);
}
