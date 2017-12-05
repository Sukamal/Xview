package com.everi.xview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static DataBaseHelper instance;

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class){
                if(instance == null){
                    instance = new DataBaseHelper(context);
                }
            }

        }
        return instance;
    }

    private DataBaseHelper(Context context) {

        super(context, /*"/mnt/sdcard/"+*/ DatabaseConstant.DATABASE_NAME, null,
                DatabaseConstant.DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL=null;


        /*------Table_Menu START--------*/

        SQL = "CREATE TABLE IF NOT EXISTS "
                + DatabaseConstant.Table_Menu.TABLE_NAME

                + " ( " + DatabaseConstant.Table_Menu.MENU_CODE + " TEXT, "
                + DatabaseConstant.Table_Menu.MENU_NAME + " TEXT , "

                + "PRIMARY KEY ( " + DatabaseConstant.Table_Menu.MENU_CODE
                + " ) );";

        db.execSQL(SQL);

        /*------Table_Menu END--------*/

        /*------Table_Transaction_Type START--------*/

        SQL = "CREATE TABLE IF NOT EXISTS "
                + DatabaseConstant.Table_Transaction_Type.TABLE_NAME

                + " ( " + DatabaseConstant.Table_Transaction_Type.TRNS_LOCALCODE + " TEXT, "
                + DatabaseConstant.Table_Transaction_Type.TRANS_DESCRIPTION + " TEXT , "

                + "PRIMARY KEY ( " + DatabaseConstant.Table_Transaction_Type.TRANS_DESCRIPTION
                + " ) );";

        db.execSQL(SQL);

        /*------Table_Transaction_Type END--------*/

        /*------Table_Transaction_Type START--------*/

        SQL = "CREATE TABLE IF NOT EXISTS "
                + DatabaseConstant.Table_Merchant.TABLE_NAME

                + " ( " + DatabaseConstant.Table_Merchant.MERCHANT_SID + " INTEGER, "
                + DatabaseConstant.Table_Merchant.MERCHANT_NAME + " TEXT , "

                + "PRIMARY KEY ( " + DatabaseConstant.Table_Merchant.MERCHANT_SID
                + " ) );";

        db.execSQL(SQL);

        /*------Table_Transaction_Type END--------*/




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {


        }
    }
}
