package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargeCalResultObject{

    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("USN_No")
    @Expose
    private String uSNNo;
    @SerializedName("breakup")
    @Expose
    private List<RechargeCalBreakupObject> breakup = null;
    @SerializedName("usableAmount")
    @Expose
    private Double usableAmount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUSNNo() {
        return uSNNo;
    }

    public void setUSNNo(String uSNNo) {
        this.uSNNo = uSNNo;
    }

    public List<RechargeCalBreakupObject> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<RechargeCalBreakupObject> breakup) {
        this.breakup = breakup;
    }

    public Double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Double usableAmount) {
        this.usableAmount = usableAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}