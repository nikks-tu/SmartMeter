package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScracthCardInsertMainObject {

    @SerializedName("info")
    @Expose
    private ScratchCardInsertInfoObject info;
    @SerializedName("result")
    @Expose
    private ScratchCardInsertResultObject result;

    public ScratchCardInsertInfoObject getInfo() {
        return info;
    }

    public void setInfo(ScratchCardInsertInfoObject info) {
        this.info = info;
    }

    public ScratchCardInsertResultObject getResult() {
        return result;
    }

    public void setResult(ScratchCardInsertResultObject result) {
        this.result = result;
    }

}