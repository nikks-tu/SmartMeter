package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterMigrationEMIDetailObject  {

    @SerializedName("PaidOn")
    @Expose
    private String paidOn;
    @SerializedName("RECHARGE_AMOUNT")
    @Expose
    private Double rECHARGEAMOUNT;
    @SerializedName("SCHEDULE_DESCRIPTION")
    @Expose
    private String sCHEDULEDESCRIPTION;
    @SerializedName("PaymentReference")
    @Expose
    private String paymentReference;
    @SerializedName("SHORT_TEXT")
    @Expose
    private String sHORTTEXT;
    @SerializedName("PAID_STATUS")
    @Expose
    private String pAIDSTATUS;
    @SerializedName("MGR_PAYMENT_ID")
    @Expose
    private Integer mGRPAYMENTID;
    @SerializedName("USABLE_AMOUNT")
    @Expose
    private Double uSABLEAMOUNT;
    @SerializedName("RECHARGE_DATE")
    @Expose
    private String rECHARGEDATE;
    @SerializedName("RECHARGE_PAYMENT_ID")
    @Expose
    private Integer rECHARGEPAYMENTID;
    @SerializedName("SCHEDULE_AMOUNT")
    @Expose
    private Double sCHEDULEAMOUNT;
    @SerializedName("SCHEDULE_DATE")
    @Expose
    private String sCHEDULEDATE;

    public String getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(String paidOn) {
        this.paidOn = paidOn;
    }

    public Double getRECHARGEAMOUNT() {
        return rECHARGEAMOUNT;
    }

    public void setRECHARGEAMOUNT(Double rECHARGEAMOUNT) {
        this.rECHARGEAMOUNT = rECHARGEAMOUNT;
    }

    public String getSCHEDULEDESCRIPTION() {
        return sCHEDULEDESCRIPTION;
    }

    public void setSCHEDULEDESCRIPTION(String sCHEDULEDESCRIPTION) {
        this.sCHEDULEDESCRIPTION = sCHEDULEDESCRIPTION;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getSHORTTEXT() {
        return sHORTTEXT;
    }

    public void setSHORTTEXT(String sHORTTEXT) {
        this.sHORTTEXT = sHORTTEXT;
    }

    public String getPAIDSTATUS() {
        return pAIDSTATUS;
    }

    public void setPAIDSTATUS(String pAIDSTATUS) {
        this.pAIDSTATUS = pAIDSTATUS;
    }

    public Integer getMGRPAYMENTID() {
        return mGRPAYMENTID;
    }

    public void setMGRPAYMENTID(Integer mGRPAYMENTID) {
        this.mGRPAYMENTID = mGRPAYMENTID;
    }

    public Double getUSABLEAMOUNT() {
        return uSABLEAMOUNT;
    }

    public void setUSABLEAMOUNT(Double uSABLEAMOUNT) {
        this.uSABLEAMOUNT = uSABLEAMOUNT;
    }

    public String getRECHARGEDATE() {
        return rECHARGEDATE;
    }

    public void setRECHARGEDATE(String rECHARGEDATE) {
        this.rECHARGEDATE = rECHARGEDATE;
    }

    public Integer getRECHARGEPAYMENTID() {
        return rECHARGEPAYMENTID;
    }

    public void setRECHARGEPAYMENTID(Integer rECHARGEPAYMENTID) {
        this.rECHARGEPAYMENTID = rECHARGEPAYMENTID;
    }

    public Double getSCHEDULEAMOUNT() {
        return sCHEDULEAMOUNT;
    }

    public void setSCHEDULEAMOUNT(Double sCHEDULEAMOUNT) {
        this.sCHEDULEAMOUNT = sCHEDULEAMOUNT;
    }

    public String getSCHEDULEDATE() {
        return sCHEDULEDATE;
    }

    public void setSCHEDULEDATE(String sCHEDULEDATE) {
        this.sCHEDULEDATE = sCHEDULEDATE;
    }

}