package com.everi.xview.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.everi.xview.models.Merchant;
import com.everi.xview.models.Merchant;

import java.util.ArrayList;
import java.util.List;


public class MerchantDbHandler extends DBHandler{

    public MerchantDbHandler(Context context) {
        super(context);
    }

    public void insertOrUpdateMerchant(List<Merchant> merchantList) {

        SQLiteStatement statement = null;
        try {
            mSqliteDatabase.beginTransaction();
            StringBuilder builder = new StringBuilder();
            builder.append("INSERT OR REPLACE INTO " + DatabaseConstant.Table_Merchant.TABLE_NAME + "(");

            builder.append(DatabaseConstant.Table_Merchant.MERCHANT_SID + ",");
            builder.append(DatabaseConstant.Table_Merchant.MERCHANT_NAME + ")");


            builder.append(" VALUES (?,?)");
            statement = mSqliteDatabase.compileStatement(builder.toString());

            for(Merchant merchant : merchantList){
                statement.clearBindings();

                statement.bindLong(1, merchant.getMerchantSID());
                statement.bindString(2, merchant.getMerchantName());

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

    public void deleteMerchantBySID(int merchantSID){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Merchant.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Merchant.MERCHANT_SID
                    + " = "
                    + merchantSID
                    + ")";

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

    public List<Merchant> getAllMerchant() {

        List<Merchant> list = null;

        Cursor cursor = null;
        try {

            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Merchant.TABLE_NAME
                    ;

            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                list = new ArrayList<Merchant>();
                do{
                    Merchant merchant = new Merchant();
                    merchant.setObject(cursor);
                    list.add(merchant);

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

    public Merchant getMerchantDetails(int merchantSID) {

        Merchant merchant = null;

        Cursor cursor = null;
        try {
            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Merchant.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Merchant.MERCHANT_SID
                    + " = "
                    + merchantSID
                    ;


            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                merchant = new Merchant();
                merchant.setObject(cursor);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return merchant;

    }

    public void deleteAllMenu(){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Merchant.TABLE_NAME;

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

}
