package com.everi.xview.models;

import android.database.Cursor;

import com.everi.xview.database.DatabaseConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RSAdmin on 03-03-2017.
 */

public class Merchant {

    @SerializedName("MerchantSID")
    private Integer merchantSID;
    @SerializedName("MerchantName")
    private String merchantName;

    public Integer getMerchantSID() {
        return merchantSID;
    }

    public void setMerchantSID(Integer merchantSID) {
        this.merchantSID = merchantSID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setObject(Cursor c) {
        merchantSID = (c.getInt(c.getColumnIndex(DatabaseConstant.Table_Merchant.MERCHANT_SID)));
        merchantName = (c.getString(c.getColumnIndex(DatabaseConstant.Table_Merchant.MERCHANT_NAME)));
    }

    @Override
    public String toString() {
        return merchantName;
    }
}
