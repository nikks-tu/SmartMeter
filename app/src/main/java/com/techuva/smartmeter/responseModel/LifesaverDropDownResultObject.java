package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifesaverDropDownResultObject{

    @SerializedName("LIFESAVER_ID")
    @Expose
    private Integer lIFESAVERID;
    @SerializedName("AMOUNT")
    @Expose
    private Integer aMOUNT;

    public Integer getLIFESAVERID() {
        return lIFESAVERID;
    }

    public void setLIFESAVERID(Integer lIFESAVERID) {
        this.lIFESAVERID = lIFESAVERID;
    }

    public Integer getAMOUNT() {
        return aMOUNT;
    }

    public void setAMOUNT(Integer aMOUNT) {
        this.aMOUNT = aMOUNT;
    }


    @Override
    public String toString() {
        return String.valueOf(getAMOUNT());
    }

}