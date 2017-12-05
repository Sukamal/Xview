package com.everi.xview.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RSAdmin on 01-03-2017.
 */

public class TransactionDetailsResponse {

    @SerializedName("ResponseStatus")
    private List<ResponseStatus> responseStatus = null;
    @SerializedName("TransactionDetail")
    private List<TransactionDetails> transactionDetail = null;

    public List<ResponseStatus> getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(List<ResponseStatus> responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<TransactionDetails> getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(List<TransactionDetails> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

}
