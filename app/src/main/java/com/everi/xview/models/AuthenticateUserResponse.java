package com.everi.xview.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RSAdmin on 01-03-2017.
 */

public class AuthenticateUserResponse {

    @SerializedName("ResponseStatus")
    private List<ResponseStatus> responseStatus = null;

    @SerializedName("Menus")
    private List<Menus> menus = null;

    public List<ResponseStatus> getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(List<ResponseStatus> responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }


}
