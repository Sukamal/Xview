package com.everi.xview;

import android.content.Context;
import android.content.SharedPreferences;

import com.everi.xview.preference.AppPreferenceImpl;

import javax.inject.Singleton;

/**
 * Created by RSAdmin on 08-03-2017.
 */


public class AppConfigData {

    public final static String PREFERENCE_NAME = "everi.xview.pref";

    private SharedPreferences sharedPreferences;
    private AppPreferenceImpl appPreference;
    public static AppConfigData instance;

    private String baseURL = "http://10.121.43.110";

    private AppConfigData(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        appPreference = new AppPreferenceImpl(sharedPreferences);
    }

    public static AppConfigData getInstance(Context context){
        if(instance == null){
            synchronized (AppConfigData.class){
                if(instance == null){
                    instance = new AppConfigData(context);
                }
            }
        }
        return instance;
    }

    public void loadConfig(){
        baseURL = appPreference.getStringValue(AppPreferenceImpl.AppConfig.TAG_BASE_URL.name(),"http://10.121.43.110");

    }

    public void saveConfig(){
        appPreference.saveStringValue(AppPreferenceImpl.AppConfig.TAG_BASE_URL.name(),baseURL);

    }


    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }



}
