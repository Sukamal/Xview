package com.everi.xview.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RSAdmin on 01-03-2017.
 */

public class TransactionTypeResponse {

    @SerializedName("TransactionType")
    private List<TransactionType> transactionType = null;

    public List<TransactionType> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(List<TransactionType> transactionType) {
        this.transactionType = transactionType;
    }

}
