package com.everi.xview.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sukamal on 15/2/17.
 */

public class TransactionDetails {


    @SerializedName("TransactionCategory")
    private String transactionCategory;
    @SerializedName("TransactionType")
    private String transactionType;
    @SerializedName("TransactionSubType")
    private String transactionSubType;
    @SerializedName("TransactionSystemDateTime")
    private String transactionSystemDateTime;
    @SerializedName("TransactionDateTime")
    private String transactionDateTime;
    @SerializedName("TransactionStatus")
    private String transactionStatus;
    @SerializedName("Response")
    private String response;
    @SerializedName("Currency")
    private String currency;
    @SerializedName("RequestedAmount")
    private String requestedAmount;
    @SerializedName("RequestedFee")
    private String requestedFee;
    @SerializedName("RequestedTotalAmount")
    private String requestedTotalAmount;
    @SerializedName("AuthorizedAmount")
    private String authorizedAmount;
    @SerializedName("AuthorizedCurrency")
    private String authorizedCurrency;
    @SerializedName("ReceivedAmount")
    private String receivedAmount;
    @SerializedName("ReceivedFee")
    private String receivedFee;
    @SerializedName("ReceivedTotalAmount")
    private String receivedTotalAmount;
    @SerializedName("CardOrAccountNumber")
    private String cardOrAccountNumber;
    @SerializedName("CardType")
    private String cardType;
    @SerializedName("Dispensed")
    private String dispensed;
    @SerializedName("AuthorizationNumber")
    private String authorizationNumber;
    @SerializedName("EVERIReceiptNumber")
    private String eVERIReceiptNumber;
    @SerializedName("ReferenceNumber")
    private String referenceNumber;
    @SerializedName("SequenceNumber")
    private String sequenceNumber;
    @SerializedName("RRN")
    private String rRN;
    @SerializedName("EntryMode")
    private String entryMode;
    @SerializedName("AddressVerificationCode")
    private String addressVerificationCode;
    @SerializedName("AVSZipCodeVerified")
    private String aVSZipCodeVerified;
    @SerializedName("FinancialNetwork")
    private String financialNetwork;
    @SerializedName("Processor")
    private String processor;
    @SerializedName("SettlementDate")
    private String settlementDate;
    @SerializedName("MerchantID")
    private String merchantID;
    @SerializedName("MerchantName ")
    private String merchantName;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("TerminalIDInitiated")
    private String terminalIDInitiated;
    @SerializedName("TerminalIDCompleted")
    private String terminalIDCompleted;
    @SerializedName("TerminalCategory")
    private String terminalCategory;
    @SerializedName("TerminalType")
    private String terminalType;
    @SerializedName("UserID")
    private String userID;
    @SerializedName("PatronName")
    private String patronName;

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionSubType() {
        return transactionSubType;
    }

    public void setTransactionSubType(String transactionSubType) {
        this.transactionSubType = transactionSubType;
    }

    public String getTransactionSystemDateTime() {
        return transactionSystemDateTime;
    }

    public void setTransactionSystemDateTime(String transactionSystemDateTime) {
        this.transactionSystemDateTime = transactionSystemDateTime;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getRequestedFee() {
        return requestedFee;
    }

    public void setRequestedFee(String requestedFee) {
        this.requestedFee = requestedFee;
    }

    public String getRequestedTotalAmount() {
        return requestedTotalAmount;
    }

    public void setRequestedTotalAmount(String requestedTotalAmount) {
        this.requestedTotalAmount = requestedTotalAmount;
    }

    public String getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(String authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public String getAuthorizedCurrency() {
        return authorizedCurrency;
    }

    public void setAuthorizedCurrency(String authorizedCurrency) {
        this.authorizedCurrency = authorizedCurrency;
    }

    public String getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(String receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getReceivedFee() {
        return receivedFee;
    }

    public void setReceivedFee(String receivedFee) {
        this.receivedFee = receivedFee;
    }

    public String getReceivedTotalAmount() {
        return receivedTotalAmount;
    }

    public void setReceivedTotalAmount(String receivedTotalAmount) {
        this.receivedTotalAmount = receivedTotalAmount;
    }

    public String getCardOrAccountNumber() {
        return cardOrAccountNumber;
    }

    public void setCardOrAccountNumber(String cardOrAccountNumber) {
        this.cardOrAccountNumber = cardOrAccountNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getDispensed() {
        return dispensed;
    }

    public void setDispensed(String dispensed) {
        this.dispensed = dispensed;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        this.authorizationNumber = authorizationNumber;
    }

    public String getEVERIReceiptNumber() {
        return eVERIReceiptNumber;
    }

    public void setEVERIReceiptNumber(String eVERIReceiptNumber) {
        this.eVERIReceiptNumber = eVERIReceiptNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getRRN() {
        return rRN;
    }

    public void setRRN(String rRN) {
        this.rRN = rRN;
    }

    public String getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    public String getAddressVerificationCode() {
        return addressVerificationCode;
    }

    public void setAddressVerificationCode(String addressVerificationCode) {
        this.addressVerificationCode = addressVerificationCode;
    }

    public String getAVSZipCodeVerified() {
        return aVSZipCodeVerified;
    }

    public void setAVSZipCodeVerified(String aVSZipCodeVerified) {
        this.aVSZipCodeVerified = aVSZipCodeVerified;
    }

    public String getFinancialNetwork() {
        return financialNetwork;
    }

    public void setFinancialNetwork(String financialNetwork) {
        this.financialNetwork = financialNetwork;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTerminalIDInitiated() {
        return terminalIDInitiated;
    }

    public void setTerminalIDInitiated(String terminalIDInitiated) {
        this.terminalIDInitiated = terminalIDInitiated;
    }

    public String getTerminalIDCompleted() {
        return terminalIDCompleted;
    }

    public void setTerminalIDCompleted(String terminalIDCompleted) {
        this.terminalIDCompleted = terminalIDCompleted;
    }

    public String getTerminalCategory() {
        return terminalCategory;
    }

    public void setTerminalCategory(String terminalCategory) {
        this.terminalCategory = terminalCategory;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }









}
