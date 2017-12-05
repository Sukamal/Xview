package com.everi.xview.di.modules;

import android.content.Context;

import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.login.LoginLogicImpl;
import com.everi.xview.screens.activity.login.LoginLogicInterface;
import com.everi.xview.screens.activity.login.LoginViewInterface;
import com.everi.xview.util.MessageFactory;
import com.everi.xview.util.Validations;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RSAdmin on 09-03-2017.
 */

@Module
public class LoginDIModule {

    private Context context;
    private LoginViewInterface viewInterface;

    public LoginDIModule(Context context,LoginViewInterface viewInterface){
        this.context = context;
        this.viewInterface = viewInterface;
    }
    @Provides
    public LoginLogicInterface provideLoginLogicInterface(AppPreferenceInterface preferenceInterface,
                                                          ApiServices apiServices, MessageFactory messageFactory, Validations validations){
        return new LoginLogicImpl(context, viewInterface, preferenceInterface, apiServices, messageFactory, validations);
    }


}
