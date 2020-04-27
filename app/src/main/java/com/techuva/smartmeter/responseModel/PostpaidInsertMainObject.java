package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidInsertMainObject {

    @SerializedName("info")
    @Expose
    private PostPaidInsertInfoObject info;
    @SerializedName("result")
    @Expose
    private PostpaidInsertResultObject result;

    public PostPaidInsertInfoObject getInfo() {
        return info;
    }

    public void setInfo(PostPaidInsertInfoObject info) {
        this.info = info;
    }

    public PostpaidInsertResultObject getResult() {
        return result;
    }

    public void setResult(PostpaidInsertResultObject result) {
        this.result = result;
    }

}