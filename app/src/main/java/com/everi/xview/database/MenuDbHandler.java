package com.everi.xview.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.everi.xview.models.Menus;

import java.util.ArrayList;
import java.util.List;


public class MenuDbHandler extends DBHandler{

    public MenuDbHandler(Context context) {
        super(context);
    }

    public void insertOrUpdateMenu(List<Menus> menus) {

        SQLiteStatement statement = null;
        try {
            mSqliteDatabase.beginTransaction();
            StringBuilder builder = new StringBuilder();
            builder.append("INSERT OR REPLACE INTO " + DatabaseConstant.Table_Menu.TABLE_NAME + "(");

            builder.append(DatabaseConstant.Table_Menu.MENU_CODE + ",");
            builder.append(DatabaseConstant.Table_Menu.MENU_NAME + ")");


            builder.append(" VALUES (?,?)");
            statement = mSqliteDatabase.compileStatement(builder.toString());

            for(Menus menu : menus){
                statement.clearBindings();

                statement.bindString(1, menu.getMenuCode());
                statement.bindString(2, menu.getMenuName());

                statement.execute();
            }

            mSqliteDatabase.setTransactionSuccessful();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            mSqliteDatabase.endTransaction();
        }

    }

    public void deleteMenuByCode(String menuCode){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Menu.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Menu.MENU_CODE
                    + " = "
                    + "'"
                    + menuCode
                    + "'"
                    + ")";

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

    public List<Menus> getAllMenus() {

        List<Menus> list = null;

        Cursor cursor = null;
        try {

            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Menu.TABLE_NAME
                    ;

            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                list = new ArrayList<Menus>();
                do{
                    Menus menu = new Menus();
                    menu.setObject(cursor);
                    list.add(menu);

                }while(cursor.moveToNext());

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;

    }

    public Menus getMenuDetails(String menucode) {

        Menus menu = null;

        Cursor cursor = null;
        try {
            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Menu.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Menu.MENU_CODE
                    + " = "
                    + "'"
                    + menucode
                    + "'"
                    ;


            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                menu = new Menus();
                menu.setObject(cursor);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return menu;

    }

    public void deleteAllMenu(){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Menu.TABLE_NAME;

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

}
