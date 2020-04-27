package com.techuva.smartmeter.postParameters;

public class LifeSaverInsertPostParameters {


    private String USN_No;

    private String PaidBy;

    private String Remarks;

    public LifeSaverInsertPostParameters(String USN_No, String lifeSaverAmount) {
        this.USN_No = USN_No;
        this.lifeSaverAmount = lifeSaverAmount;
    }

    private String lifeSaverAmount;

    public LifeSaverInsertPostParameters(String USN_No, String paidBy, String remarks) {
        this.USN_No = USN_No;
        PaidBy = paidBy;
        Remarks = remarks;
    }

    public LifeSaverInsertPostParameters(String USN_No) {
        this.USN_No = USN_No;
    }
    public String getUSN_No() {
        return USN_No;
    }

    public void setUSN_No(String USN_No) {
        this.USN_No = USN_No;
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

    public String getLifeSaverAmount() {
        return lifeSaverAmount;
    }

    public void setLifeSaverAmount(String lifeSaverAmount) {
        this.lifeSaverAmount = lifeSaverAmount;
    }


}
