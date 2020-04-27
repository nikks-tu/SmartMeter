package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeUpdateMainObject {

    @SerializedName("info")
    @Expose
    private RechargeUpdateInfoObject info;
    @SerializedName("result")
    @Expose
    private RechargeUpdateResultObject result;

    public RechargeUpdateInfoObject getInfo() {
        return info;
    }

    public void setInfo(RechargeUpdateInfoObject info) {
        this.info = info;
    }

    public RechargeUpdateResultObject getResult() {
        return result;
    }

    public void setResult(RechargeUpdateResultObject result) {
        this.result = result;
    }

}