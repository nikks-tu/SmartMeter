package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LifeSaverCalculationResultObject{

    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("USN_No")
    @Expose
    private String uSNNo;
    @SerializedName("breakup")
    @Expose
    private List<LifeSaverCalculationBreakup> breakup = null;
    @SerializedName("usableAmount")
    @Expose
    private Integer usableAmount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUSNNo() {
        return uSNNo;
    }

    public void setUSNNo(String uSNNo) {
        this.uSNNo = uSNNo;
    }

    public List<LifeSaverCalculationBreakup> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<LifeSaverCalculationBreakup> breakup) {
        this.breakup = breakup;
    }

    public Integer getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Integer usableAmount) {
        this.usableAmount = usableAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}