package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DenominationMainObject {

    @SerializedName("info")
    @Expose
    private DenominationInfoObject info;
    @SerializedName("result")
    @Expose
    private List<DenominationResultObject> result = null;

    public DenominationInfoObject getInfo() {
        return info;
    }

    public void setInfo(DenominationInfoObject info) {
        this.info = info;
    }

    public List<DenominationResultObject> getResult() {
        return result;
    }

    public void setResult(List<DenominationResultObject> result) {
        this.result = result;
    }

}