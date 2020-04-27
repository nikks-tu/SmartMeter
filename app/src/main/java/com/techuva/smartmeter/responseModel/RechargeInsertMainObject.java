package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeInsertMainObject {

    @SerializedName("info")
    @Expose
    private RechargeInsertInfoObject info;
    @SerializedName("result")
    @Expose
    private RechargeInsertResultObject result;

    public RechargeInsertInfoObject getInfo() {
        return info;
    }

    public void setInfo(RechargeInsertInfoObject info) {
        this.info = info;
    }

    public RechargeInsertResultObject getResult() {
        return result;
    }

    public void setResult(RechargeInsertResultObject result) {
        this.result = result;
    }

}