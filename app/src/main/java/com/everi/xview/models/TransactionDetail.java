package com.everi.xview.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sukamal on 15/2/17.
 */

public class TransactionDetail {

    @SerializedName("TransactionType")
    private String transactionType;
    @SerializedName("Response")
    private String response;
    @SerializedName("TransactionDtTm")
    private String transactionDtTm;
    @SerializedName("ReceivedTotalAmount")
    private String receivedTotalAmount;
    @SerializedName("TerminalID")
    private String terminalID;
    @SerializedName("TransactionHistoryUID")
    private String transactionHistoryUID;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTransactionDtTm() {
        return transactionDtTm;
    }

    public void setTransactionDtTm(String transactionDtTm) {
        this.transactionDtTm = transactionDtTm;
    }

    public String getReceivedTotalAmount() {
        return receivedTotalAmount;
    }

    public void setReceivedTotalAmount(String receivedTotalAmount) {
        this.receivedTotalAmount = receivedTotalAmount;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getTransactionHistoryUID() {
        return transactionHistoryUID;
    }

    public void setTransactionHistoryUID(String transactionHistoryUID) {
        this.transactionHistoryUID = transactionHistoryUID;
    }

}
