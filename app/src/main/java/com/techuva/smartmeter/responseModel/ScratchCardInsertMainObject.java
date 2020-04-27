package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScratchCardInsertMainObject {

    @SerializedName("info")
    @Expose
    private ScratchCardInfoObject info;
    @SerializedName("result")
    @Expose
    private ScratchCardResultObject result;

    public ScratchCardInfoObject getInfo() {
        return info;
    }

    public void setInfo(ScratchCardInfoObject info) {
        this.info = info;
    }

    public ScratchCardResultObject getResult() {
        return result;
    }

    public void setResult(ScratchCardResultObject result) {
        this.result = result;
    }

}