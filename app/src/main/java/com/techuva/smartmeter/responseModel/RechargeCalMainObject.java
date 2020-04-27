package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeCalMainObject {

    @SerializedName("info")
    @Expose
    private RechargeCalInfoObject info;
    @SerializedName("result")
    @Expose
    private RechargeCalResultObject result;

    public RechargeCalInfoObject getInfo() {
        return info;
    }

    public void setInfo(RechargeCalInfoObject info) {
        this.info = info;
    }

    public RechargeCalResultObject getResult() {
        return result;
    }

    public void setResult(RechargeCalResultObject result) {
        this.result = result;
    }

}