package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidBillDataMainObject  {

    @SerializedName("info")
    @Expose
    private PostpaidBillDataInfoObject info;
    @SerializedName("result")
    @Expose
    private PostpaidBillDataResultObject result;

    public PostpaidBillDataInfoObject getInfo() {
        return info;
    }

    public void setInfo(PostpaidBillDataInfoObject info) {
        this.info = info;
    }

    public PostpaidBillDataResultObject getResult() {
        return result;
    }

    public void setResult(PostpaidBillDataResultObject result) {
        this.result = result;
    }

}