package com.everi.xview.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RSAdmin on 03-03-2017.
 */

public class MerchantResult {
    @SerializedName("Merchant")
    private List<Merchant> merchant = null;

    public List<Merchant> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<Merchant> merchant) {
        this.merchant = merchant;
    }
}
