package com.everi.xview.screens.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class SplashScreenLogicImpl implements SplashScreenLogicInterface{

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private SplashScreenViewInterface viewInterface;

    public SplashScreenLogicImpl(SplashScreenViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }


    @Override
    public void displayNextScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewInterface.displayLoginScreen();
            }
        },3000);

    }
}
