package com.everi.xview.models;

import android.database.Cursor;

import com.everi.xview.database.DatabaseConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RSAdmin on 01-03-2017.
 */

public class Menus {

    @SerializedName("MenuCode")
    private String MenuCode;
    @SerializedName("MenuName")
    private String MenuName;

    public Menus(){}

    public Menus(String menuCode,String menuName){
        this.MenuCode = menuCode;
        this.MenuName = menuName;
    }

    public String getMenuCode() {
        return MenuCode;
    }

    public void setMenuCode(String MenuCode) {
        this.MenuCode = MenuCode;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String MenuName) {
        this.MenuName = MenuName;
    }

    public void setObject(Cursor c) {

        MenuCode = (c.getString(c.getColumnIndex(DatabaseConstant.Table_Menu.MENU_CODE)));
        MenuName = (c.getString(c.getColumnIndex(DatabaseConstant.Table_Menu.MENU_NAME)));

    }

}
