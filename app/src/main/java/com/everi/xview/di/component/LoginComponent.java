package com.everi.xview.di.component;

import com.everi.xview.di.modules.LoginDIModule;
import com.everi.xview.di.scopes.LoginActivityScope;
import com.everi.xview.screens.activity.login.LoginActivity;

import javax.inject.Scope;

import dagger.Subcomponent;

/**
 * Created by RSAdmin on 09-03-2017.
 */

@LoginActivityScope
@Subcomponent(modules = {LoginDIModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
