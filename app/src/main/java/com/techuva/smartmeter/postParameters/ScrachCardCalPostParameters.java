package com.techuva.smartmeter.postParameters;

public class ScrachCardCalPostParameters {

    private String USN_No;
    private String rechargePlan;
    private String rechargeCode;
    private String PaidBy;
    private String Remarks;

    public ScrachCardCalPostParameters(String USN_No, String rechargePlan, String rechargeCode, String paidBy, String remarks) {
        this.USN_No = USN_No;
        this.rechargePlan = rechargePlan;
        this.rechargeCode = rechargeCode;
        PaidBy = paidBy;
        Remarks = remarks;
    }

    public String getUSN_No() {
        return USN_No;
    }

    public void setUSN_No(String USN_No) {
        this.USN_No = USN_No;
    }

    public String getRechargePlan() {
        return rechargePlan;
    }

    public void setRechargePlan(String rechargePlan) {
        this.rechargePlan = rechargePlan;
    }

    public String getRechargeCode() {
        return rechargeCode;
    }

    public void setRechargeCode(String rechargeCode) {
        this.rechargeCode = rechargeCode;
    }

    public String getPaidBy() {
        return PaidBy;
    }

    public void setPaidBy(String paidBy) {
        PaidBy = paidBy;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }



}
