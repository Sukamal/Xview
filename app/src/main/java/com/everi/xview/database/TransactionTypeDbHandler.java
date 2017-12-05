package com.everi.xview.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.everi.xview.models.Menus;
import com.everi.xview.models.TransactionType;

import java.util.ArrayList;
import java.util.List;


public class TransactionTypeDbHandler extends DBHandler{

    public TransactionTypeDbHandler(Context context) {
        super(context);
    }

    public void insertOrUpdateTransactionType(List<TransactionType> transactionTypes) {

        SQLiteStatement statement = null;
        try {
            mSqliteDatabase.beginTransaction();
            StringBuilder builder = new StringBuilder();
            builder.append("INSERT OR REPLACE INTO " + DatabaseConstant.Table_Transaction_Type.TABLE_NAME + "(");

            builder.append(DatabaseConstant.Table_Transaction_Type.TRNS_LOCALCODE + ",");
            builder.append(DatabaseConstant.Table_Transaction_Type.TRANS_DESCRIPTION + ")");


            builder.append(" VALUES (?,?)");
            statement = mSqliteDatabase.compileStatement(builder.toString());

            for(TransactionType type : transactionTypes){
                statement.clearBindings();

                statement.bindString(1, type.getLocalTransactionCode());
                statement.bindString(2, type.getDescription());

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

    public void deletetransactiontypeByCode(String transCode){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Transaction_Type.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Transaction_Type.TRNS_LOCALCODE
                    + " = "
                    + "'"
                    + transCode
                    + "'"
                    + ")";

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

    public List<TransactionType> getAllTransactionType() {

        List<TransactionType> list = null;
        Cursor cursor = null;
        try {

            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Transaction_Type.TABLE_NAME
                    ;

            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                list = new ArrayList<TransactionType>();
                do{
                    TransactionType type = new TransactionType();
                    type.setObject(cursor);
                    list.add(type);

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

    public TransactionType getTransactionTypeDetails(String transCode) {

        TransactionType type = null;
        Cursor cursor = null;
        try {
            String sql = "SELECT * FROM "
                    + DatabaseConstant.Table_Transaction_Type.TABLE_NAME
                    + " WHERE "
                    + DatabaseConstant.Table_Transaction_Type.TRNS_LOCALCODE
                    + " = "
                    + "'"
                    + transCode
                    + "'"
                    ;
            cursor = mSqliteDatabase.rawQuery(sql, null);

            if (cursor != null && !cursor.isClosed() && cursor.getCount()>0) {
                cursor.moveToFirst();

                type = new TransactionType();
                type.setObject(cursor);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return type;

    }

    public void deleteAllTransactionType(){
        try {
            String sql = "DELETE FROM "
                    + DatabaseConstant.Table_Transaction_Type.TABLE_NAME;

            mSqliteDatabase.execSQL(sql);

        }catch (Exception e){

        }
    }

}
