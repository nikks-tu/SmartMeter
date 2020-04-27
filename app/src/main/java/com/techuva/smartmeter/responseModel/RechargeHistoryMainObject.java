package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargeHistoryMainObject {

    @SerializedName("result")
    @Expose
    private List<RechargeHistoryResultObject> result = null;
    @SerializedName("info")
    @Expose
    private RechargeHistoryInfoObject info;

    public List<RechargeHistoryResultObject> getResult() {
        return result;
    }

    public void setResult(List<RechargeHistoryResultObject> result) {
        this.result = result;
    }

    public RechargeHistoryInfoObject getInfo() {
        return info;
    }

    public void setInfo(RechargeHistoryInfoObject info) {
        this.info = info;
    }

}