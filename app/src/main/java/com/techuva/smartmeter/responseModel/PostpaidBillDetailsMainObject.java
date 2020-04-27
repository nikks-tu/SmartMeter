package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidBillDetailsMainObject {

    @SerializedName("result")
    @Expose
    private PostpaidBillDetailsResultObject result;
    @SerializedName("info")
    @Expose
    private PostpaidBillDetailsInfoObject info;

    public PostpaidBillDetailsResultObject getResult() {
        return result;
    }

    public void setResult(PostpaidBillDetailsResultObject result) {
        this.result = result;
    }

    public PostpaidBillDetailsInfoObject getInfo() {
        return info;
    }

    public void setInfo(PostpaidBillDetailsInfoObject info) {
        this.info = info;
    }

}