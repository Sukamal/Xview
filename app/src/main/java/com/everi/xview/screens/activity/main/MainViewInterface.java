package com.everi.xview.screens.activity.main;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public interface MainViewInterface {

    void setToolbarTitle(String title);
    void openNavDrawer();
    void closeNavDrawer();
    void setNavDrawerEnabled(boolean val);
    void performNavMenuItemClick(int menuId);
    ActionBarDrawerToggle getNavDrawerToggle();
    Toolbar getToolbar();

}
