package com.everi.xview.screens.activity.main;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.everi.xview.models.Menus;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.util.AppConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public class MainLogicImpl implements MainLogicInterface{

    private Context context;
    private MainViewInterface viewInterface;
    private AppPreferenceInterface preferenceInterface;
    private NavigationView navigationView;

    public MainLogicImpl(Context context, MainViewInterface viewInterface, NavigationView navigationView,AppPreferenceInterface preferenceInterface){
        this.context = context;
        this.viewInterface = viewInterface;
        this.preferenceInterface = preferenceInterface;
        this.navigationView = navigationView;
    }


    @Override
    public void setNavActiveMenu() {

        List<Menus> menuList = null;
        String menuVal = preferenceInterface.getStringValue(AppPreferenceImpl.MenuStorage.TAG_MENU.name());
        Type listType = new TypeToken<List<Menus>>() {}.getType();
        menuList = new Gson().fromJson(menuVal, listType);

        final android.view.Menu appmenu = navigationView.getMenu();
        for(Menus menu : menuList){

            for(AppConstant.MENU enumMenu : AppConstant.MENU.values()){
                if(menu.getMenuCode().equalsIgnoreCase(enumMenu.getMenuName())){
                    appmenu.findItem(enumMenu.getMenuId()).setVisible(true);

                }
            }
        }

        for(int i = 0 ; i <appmenu.size();i++ ){
            MenuItem menuItem = appmenu.getItem(i);
            if(menuItem.isVisible()){
                viewInterface.performNavMenuItemClick(menuItem.getItemId());
                return;
            }
        }

//        appmenu.clear();
//        if(menuList != null){
//            for(int i=0; i < menuList.size(); i++){
//                Menu menu = menuList.get(i);
//                appmenu.add(i, (android.view.Menu.FIRST + 0), android.view.Menu.NONE,menu.getMenuName());
//
//            }
//        }

    }
}
