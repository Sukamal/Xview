package com.everi.xview.screens.activity.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.everi.xview.AppConfigData;
import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.BaseActivity;
import com.everi.xview.util.Utility;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public class SettingsActivity extends BaseActivity{

    @Inject
    AppConfigData appConfigData;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.et_baseurl)
    EditText etBaseurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        ((XviewApplication)getApplication()).getComponent().inject(this);
        setTitle();
        getBaseUrl();

    }

    private void setTitle(){
        mToolbar.setTitle("Application Configuration");
    }

    private void getBaseUrl(){
//        String baseUrl = ((XviewApplication)getApplication()).getAppConfig().getBaseURL();
        String baseUrl = appConfigData.getBaseURL();
        if(!Utility.isNullOrBlank(baseUrl)){
            etBaseurl.setText(baseUrl);
        }
    }

    @OnClick(R.id.btn_settings_save)
    void onSettingsSave(){
        finish();
//        ((XviewApplication)getApplication()).getAppConfig().setBaseURL(etBaseurl.getText().toString().trim());
//        ((XviewApplication)getApplication()).getAppConfig().saveConfig();

        appConfigData.setBaseURL(etBaseurl.getText().toString().trim());
        appConfigData.saveConfig();
    }

    @OnClick(R.id.btn_settings_cancel)
    void onSettingsCancel(){
        finish();
    }




}
