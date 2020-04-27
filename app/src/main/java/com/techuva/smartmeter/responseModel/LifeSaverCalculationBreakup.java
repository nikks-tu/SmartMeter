package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifeSaverCalculationBreakup {

    @SerializedName("Breakup_Rate")
    @Expose
    private Double breakupRate;
    @SerializedName("Breakup_Desc")
    @Expose
    private String breakupDesc;
    @SerializedName("Breakup_Amount")
    @Expose
    private String breakupAmount;
    @SerializedName("Calculation_Type")
    @Expose
    private int calculationType;

    public Double getBreakupRate() {
        return breakupRate;
    }

    public void setBreakupRate(Double breakupRate) {
        this.breakupRate = breakupRate;
    }

    public String getBreakupDesc() {
        return breakupDesc;
    }

    public void setBreakupDesc(String breakupDesc) {
        this.breakupDesc = breakupDesc;
    }

    public String getBreakupAmount() {
        return breakupAmount;
    }

    public void setBreakupAmount(String breakupAmount) {
        this.breakupAmount = breakupAmount;
    }

    public int getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(int calculationType) {
        this.calculationType = calculationType;
    }

}