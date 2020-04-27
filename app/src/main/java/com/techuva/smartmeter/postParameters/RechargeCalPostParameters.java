package com.techuva.smartmeter.postParameters;

public class RechargeCalPostParameters {

    private String USN_No;
    private String rechargeAmount;

    public RechargeCalPostParameters(String USN_No, String rechargeAmount) {
        this.USN_No = USN_No;
        this.rechargeAmount = rechargeAmount;
    }

    public String getUSN_NO() {
        return USN_No;
    }

    public void setUSN_NO(String USN_No) {
        this.USN_No = USN_No;
    }

    public String getUsableAmount() {
        return rechargeAmount;
    }

    public void setUsableAmount(String usableAmount) {
        this.rechargeAmount = usableAmount;
    }
}
