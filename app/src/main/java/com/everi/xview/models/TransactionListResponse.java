package com.everi.xview.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public class TransactionListResponse {


    @SerializedName("ResponseStatus")
    private List<ResponseStatus> responseStatus = null;
    @SerializedName("TransactionDetail")
    private List<TransactionDetail> transactionDetail = null;

    public List<ResponseStatus> getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(List<ResponseStatus> responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<TransactionDetail> getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(List<TransactionDetail> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

}
