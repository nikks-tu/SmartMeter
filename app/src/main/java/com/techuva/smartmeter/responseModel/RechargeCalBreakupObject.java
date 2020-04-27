package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeCalBreakupObject  {

    @SerializedName("Calculation_Type")
    @Expose
    private Integer calculationType;
    @SerializedName("Breakup_Rate")
    @Expose
    private Double breakupRate;
    @SerializedName("Breakup_Desc")
    @Expose
    private String breakupDesc;
    @SerializedName("Breakup_Amount")
    @Expose
    private String breakupAmount;
    @SerializedName("Taxes")
    @Expose
    private String taxes;

    public Integer getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(Integer calculationType) {
        this.calculationType = calculationType;
    }

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

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }


}