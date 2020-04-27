package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidBillBreakupCal {

    @SerializedName("Breakup_Desc")
    @Expose
    private String breakupDesc;
    @SerializedName("Breakup_Rate")
    @Expose
    private Integer breakupRate;
    @SerializedName("Breakup_Amount")
    @Expose
    private Double breakupAmount;

    public String getBreakupDesc() {
        return breakupDesc;
    }

    public void setBreakupDesc(String breakupDesc) {
        this.breakupDesc = breakupDesc;
    }

    public Integer getBreakupRate() {
        return breakupRate;
    }

    public void setBreakupRate(Integer breakupRate) {
        this.breakupRate = breakupRate;
    }

    public Double getBreakupAmount() {
        return breakupAmount;
    }

    public void setBreakupAmount(Double
                                         breakupAmount) {
        this.breakupAmount = breakupAmount;
    }

}