package com.everi.xview.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBHandler {

    protected SQLiteDatabase mSqliteDatabase = null;
    private DataBaseHelper mDatabaseHelper = null;
    private Context mContext;

    public DBHandler(Context context){

        this.mContext = context;
        mDatabaseHelper = DataBaseHelper.getInstance(context);
        open();
    }


    public void open() throws SQLException {

        if(mDatabaseHelper == null){
            mDatabaseHelper = DataBaseHelper.getInstance(mContext);
        }

        mSqliteDatabase = mDatabaseHelper.getWritableDatabase();
    }



    public void close() {
        mDatabaseHelper.close();
        mSqliteDatabase = null;
    }

}
