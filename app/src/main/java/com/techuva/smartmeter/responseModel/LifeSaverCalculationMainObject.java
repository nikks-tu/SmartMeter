package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifeSaverCalculationMainObject {

    @SerializedName("info")
    @Expose
    private LifeSaverCalculationInfoObject info;
    @SerializedName("result")
    @Expose
    private LifeSaverCalculationResultObject result;

    public LifeSaverCalculationInfoObject getInfo() {
        return info;
    }

    public void setInfo(LifeSaverCalculationInfoObject info) {
        this.info = info;
    }

    public LifeSaverCalculationResultObject getResult() {
        return result;
    }

    public void setResult(LifeSaverCalculationResultObject result) {
        this.result = result;
    }

}