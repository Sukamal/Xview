package com.everi.xview.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.everi.xview.AppConfigData;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.networkapi.RetrofitSetup;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.util.MessageFactory;
import com.everi.xview.util.Validations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RSAdmin on 08-03-2017.
 */

@Module
public class SharedDIModule {

    private Context context;
    public SharedDIModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public SharedPreferences provideSharePreference(){
        return context.getApplicationContext().getSharedPreferences(AppConfigData.PREFERENCE_NAME,Context.MODE_PRIVATE);
    }

    @Provides @Singleton
    public AppPreferenceInterface provideAppPreferenceInterface(SharedPreferences sharedPreferences){
        return new AppPreferenceImpl(sharedPreferences);
    }

    @Provides @Singleton
    public AppConfigData provideAppConfig(){
        return AppConfigData.getInstance(context);
    }


    @Provides @Singleton
    public ApiServices provideApiService(){
        RetrofitSetup retrofitSetup = new RetrofitSetup();
        ApiServices apiServices = retrofitSetup.getService(context);
        return apiServices;
    }

    @Provides @Singleton
    public MessageFactory provideMessageFactory(){
        return new MessageFactory(context);
    }

    @Provides @Singleton
    public Validations provideValidation(){
        return new Validations();
    }



}
