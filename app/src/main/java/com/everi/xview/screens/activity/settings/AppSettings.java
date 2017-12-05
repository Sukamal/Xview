package com.everi.xview.screens.activity.settings;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public class AppSettings {

    private static AppSettings instance;
    private String baseUrl = "http://10.121.43.110";

    private AppSettings(){

    }

    public AppSettings getInstance(){
        if(instance == null){
            synchronized (AppSettings.class){
                if(instance == null){
                    instance = new AppSettings();
                }
            }
        }
        return instance;
    }
}
