package com.everi.xview.models;

import android.database.Cursor;

import com.everi.xview.database.DatabaseConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RSAdmin on 03-03-2017.
 */

public class TransactionType {
    @SerializedName("LocalTransactionCode")
    private String localTransactionCode;
    @SerializedName("Description")
    private String description;

    public String getLocalTransactionCode() {
        return localTransactionCode;
    }

    public void setLocalTransactionCode(String localTransactionCode) {
        this.localTransactionCode = localTransactionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObject(Cursor c) {

        localTransactionCode = (c.getString(c.getColumnIndex(DatabaseConstant.Table_Transaction_Type.TRNS_LOCALCODE)));
        description = (c.getString(c.getColumnIndex(DatabaseConstant.Table_Transaction_Type.TRANS_DESCRIPTION)));

    }

    @Override
    public String toString() {
        return description;
    }
}
