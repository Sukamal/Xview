package com.everi.xview.screens.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.everi.xview.R;
import com.everi.xview.screens.activity.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements SplashScreenViewInterface{

    private SplashScreenLogicImpl splashScreenLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashScreenLogic = new SplashScreenLogicImpl(this);
        splashScreenLogic.displayNextScreen();

    }

    @Override
    public void displayLoginScreen() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
