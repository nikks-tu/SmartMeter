package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostPaidBillDataObject {

    @SerializedName("POSTPAID_BILLING_ID")
    @Expose
    private String pOSTPAIDBILLINGID;
    @SerializedName("FROM_DATE")
    @Expose
    private String fROMDATE;
    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("BILLING_DATE")
    @Expose
    private String bILLINGDATE;
    @SerializedName("breakup")
    @Expose
    private List<PostpaidBillDataBreakup> breakup = null;
    @SerializedName("DUE_DATE")
    @Expose
    private String dUEDATE;
    @SerializedName("SHORT_TEXT")
    @Expose
    private String sHORTTEXT;
    @SerializedName("BILLING_AMOUNT")
    @Expose
    private String bILLINGAMOUNT;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("TO_DATE")
    @Expose
    private String tODATE;
    @SerializedName("UNITS_CONSUMED")
    @Expose
    private String uNITSCONSUMED;

    public String getPOSTPAIDBILLINGID() {
        return pOSTPAIDBILLINGID;
    }

    public void setPOSTPAIDBILLINGID(String pOSTPAIDBILLINGID) {
        this.pOSTPAIDBILLINGID = pOSTPAIDBILLINGID;
    }

    public String getFROMDATE() {
        return fROMDATE;
    }

    public void setFROMDATE(String fROMDATE) {
        this.fROMDATE = fROMDATE;
    }

    public String getUSNNO() {
        return uSNNO;
    }

    public void setUSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public String getBILLINGDATE() {
        return bILLINGDATE;
    }

    public void setBILLINGDATE(String bILLINGDATE) {
        this.bILLINGDATE = bILLINGDATE;
    }

    public List<PostpaidBillDataBreakup> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<PostpaidBillDataBreakup> breakup) {
        this.breakup = breakup;
    }

    public String getDUEDATE() {
        return dUEDATE;
    }

    public void setDUEDATE(String dUEDATE) {
        this.dUEDATE = dUEDATE;
    }

    public String getSHORTTEXT() {
        return sHORTTEXT;
    }

    public void setSHORTTEXT(String sHORTTEXT) {
        this.sHORTTEXT = sHORTTEXT;
    }

    public String getBILLINGAMOUNT() {
        return bILLINGAMOUNT;
    }

    public void setBILLINGAMOUNT(String bILLINGAMOUNT) {
        this.bILLINGAMOUNT = bILLINGAMOUNT;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getTODATE() {
        return tODATE;
    }

    public void setTODATE(String tODATE) {
        this.tODATE = tODATE;
    }

    public String getUNITSCONSUMED() {
        return uNITSCONSUMED;
    }

    public void setUNITSCONSUMED(String uNITSCONSUMED) {
        this.uNITSCONSUMED = uNITSCONSUMED;
    }

}