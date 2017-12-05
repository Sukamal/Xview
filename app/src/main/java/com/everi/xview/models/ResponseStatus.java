package com.everi.xview.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RSAdmin on 01-03-2017.
 */

public class ResponseStatus {

    @SerializedName("Status")
    private String Status;
    @SerializedName("ResponseMessage")
    private String ResponseMessage;
    @SerializedName("UserGroupSID")
    private String UserGroupSID;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String ResponseMessage) {
        this.ResponseMessage = ResponseMessage;
    }

    public String getUserGroupSID() {
        return UserGroupSID;
    }

    public void setUserGroupSID(String UserGroupSID) {
        this.UserGroupSID = UserGroupSID;
    }

}
