package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifeSaverInsertMainObject {

    @SerializedName("info")
    @Expose
    private LifeSaverInsertInfoObject info;
    @SerializedName("result")
    @Expose
    private LifeSaverInsertResultObject result;

    public LifeSaverInsertInfoObject getInfo() {
        return info;
    }

    public void setInfo(LifeSaverInsertInfoObject info) {
        this.info = info;
    }

    public LifeSaverInsertResultObject getResult() {
        return result;
    }

    public void setResult(LifeSaverInsertResultObject result) {
        this.result = result;
    }

}