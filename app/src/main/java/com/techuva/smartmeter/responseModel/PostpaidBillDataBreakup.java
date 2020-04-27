package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidBillDataBreakup {

    @SerializedName("BREAKUP_DESC")
    @Expose
    private String bREAKUPDESC;
    @SerializedName("BREAKUP_AMOUNT")
    @Expose
    private String bREAKUPAMOUNT;
    @SerializedName("BREAKUP_RATE")
    @Expose
    private String bREAKUPRATE;
    @SerializedName("BILLING_BREAKUP_ID")
    @Expose
    private String bILLINGBREAKUPID;

    public String getBREAKUPDESC() {
        return bREAKUPDESC;
    }

    public void setBREAKUPDESC(String bREAKUPDESC) {
        this.bREAKUPDESC = bREAKUPDESC;
    }

    public String getBREAKUPAMOUNT() {
        return bREAKUPAMOUNT;
    }

    public void setBREAKUPAMOUNT(String bREAKUPAMOUNT) {
        this.bREAKUPAMOUNT = bREAKUPAMOUNT;
    }

    public String getBREAKUPRATE() {
        return bREAKUPRATE;
    }

    public void setBREAKUPRATE(String bREAKUPRATE) {
        this.bREAKUPRATE = bREAKUPRATE;
    }

    public String getBILLINGBREAKUPID() {
        return bILLINGBREAKUPID;
    }

    public void setBILLINGBREAKUPID(String bILLINGBREAKUPID) {
        this.bILLINGBREAKUPID = bILLINGBREAKUPID;
    }

}