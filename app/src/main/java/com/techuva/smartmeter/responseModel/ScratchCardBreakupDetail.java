package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScratchCardBreakupDetail {

    @SerializedName("Calculation_Type")
    @Expose
    private Integer calculationType;
    @SerializedName("Breakup_Rate")
    @Expose
    private Integer breakupRate;
    @SerializedName("Breakup_Desc")
    @Expose
    private String breakupDesc;
    @SerializedName("Breakup_Amount")
    @Expose
    private Integer breakupAmount;

    public Integer getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(Integer calculationType) {
        this.calculationType = calculationType;
    }

    public Integer getBreakupRate() {
        return breakupRate;
    }

    public void setBreakupRate(Integer breakupRate) {
        this.breakupRate = breakupRate;
    }

    public String getBreakupDesc() {
        return breakupDesc;
    }

    public void setBreakupDesc(String breakupDesc) {
        this.breakupDesc = breakupDesc;
    }

    public Integer getBreakupAmount() {
        return breakupAmount;
    }

    public void setBreakupAmount(Integer breakupAmount) {
        this.breakupAmount = breakupAmount;
    }

}