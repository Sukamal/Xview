package com.everi.xview.di.component;

import com.everi.xview.di.modules.LoginDIModule;
import com.everi.xview.di.modules.SharedDIModule;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.activity.login.LoginActivity;
import com.everi.xview.screens.activity.settings.SettingsActivity;
import com.everi.xview.screens.activity.splash.SplashActivity;
import com.everi.xview.screens.fragment.TransactionFilterDateRangeFragment;
import com.everi.xview.screens.fragment.TransactionFilterFragment;
import com.everi.xview.screens.fragment.TransactionFilterWheelFragment;
import com.everi.xview.screens.fragment.transactiondetails.TransactionDetailsFragment;
import com.everi.xview.screens.fragment.transactionlist.TransactionListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by RSAdmin on 06-03-2017.
 */

@Singleton
@Component(modules = {SharedDIModule.class})
public interface XviewComponent {

    void inject(SplashActivity activity);
//    void inject(LoginActivity activity);
    void inject(MainActivity activity);
    void inject(SettingsActivity activity);

    void inject(TransactionFilterDateRangeFragment fragment);
    void inject(TransactionListFragment fragment);
    void inject(TransactionFilterWheelFragment fragment);
    void inject(TransactionFilterFragment fragment);
    void inject(TransactionDetailsFragment fragment);

    LoginComponent getLoginComponent(LoginDIModule module);


}
