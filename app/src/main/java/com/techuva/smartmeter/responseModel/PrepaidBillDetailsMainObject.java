package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrepaidBillDetailsMainObject  {

    @SerializedName("result")
    @Expose
    private PrepaidBillDetailsResultObject result;
    @SerializedName("info")
    @Expose
    private PrepaidBillDetailsInfoObject info;

    public PrepaidBillDetailsResultObject getResult() {
        return result;
    }

    public void setResult(PrepaidBillDetailsResultObject result) {
        this.result = result;
    }

    public PrepaidBillDetailsInfoObject getInfo() {
        return info;
    }

    public void setInfo(PrepaidBillDetailsInfoObject info) {
        this.info = info;
    }

}