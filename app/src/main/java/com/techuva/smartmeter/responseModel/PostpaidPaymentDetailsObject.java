package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostpaidPaymentDetailsObject {

    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("RECHARGE_AMOUNT")
    @Expose
    private String rECHARGEAMOUNT;
    @SerializedName("PAYMENT_DESCRIPTION")
    @Expose
    private String pAYMENTDESCRIPTION;
    @SerializedName("USABLE_AMOUNT")
    @Expose
    private String uSABLEAMOUNT;
    @SerializedName("PAID_BY")
    @Expose
    private String pAIDBY;
    @SerializedName("RECHARGE_PAYMENT_ID")
    @Expose
    private String rECHARGEPAYMENTID;
    @SerializedName("PAYMENT_SHORT_CODE")
    @Expose
    private String pAYMENTSHORTCODE;
    @SerializedName("PAYMENT_TYPE")
    @Expose
    private String pAYMENTTYPE;
    @SerializedName("AUTH_MODEL")
    @Expose
    private String aUTHMODEL;
    @SerializedName("breakup")
    @Expose
    private List<PostpaidBillDataBreakup> breakup = null;
    @SerializedName("TRANS_STATUS")
    @Expose
    private String tRANSSTATUS;
    @SerializedName("CHARGE_RESPONSE_MESSAGE")
    @Expose
    private String cHARGERESPONSEMESSAGE;
    @SerializedName("RECHARGE_DATE")
    @Expose
    private String rECHARGEDATE;
    @SerializedName("CHARGE_TYPE")
    @Expose
    private String cHARGETYPE;
    @SerializedName("TRANS_REF")
    @Expose
    private String tRANSREF;
    @SerializedName("FRAUD_STATUS")
    @Expose
    private String fRAUDSTATUS;

    public String getCURRENCY() {
        return cURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getRECHARGEAMOUNT() {
        return rECHARGEAMOUNT;
    }

    public void setRECHARGEAMOUNT(String rECHARGEAMOUNT) {
        this.rECHARGEAMOUNT = rECHARGEAMOUNT;
    }

    public String getPAYMENTDESCRIPTION() {
        return pAYMENTDESCRIPTION;
    }

    public void setPAYMENTDESCRIPTION(String pAYMENTDESCRIPTION) {
        this.pAYMENTDESCRIPTION = pAYMENTDESCRIPTION;
    }

    public String getUSABLEAMOUNT() {
        return uSABLEAMOUNT;
    }

    public void setUSABLEAMOUNT(String uSABLEAMOUNT) {
        this.uSABLEAMOUNT = uSABLEAMOUNT;
    }

    public String getPAIDBY() {
        return pAIDBY;
    }

    public void setPAIDBY(String pAIDBY) {
        this.pAIDBY = pAIDBY;
    }

    public String getRECHARGEPAYMENTID() {
        return rECHARGEPAYMENTID;
    }

    public void setRECHARGEPAYMENTID(String rECHARGEPAYMENTID) {
        this.rECHARGEPAYMENTID = rECHARGEPAYMENTID;
    }

    public String getPAYMENTSHORTCODE() {
        return pAYMENTSHORTCODE;
    }

    public void setPAYMENTSHORTCODE(String pAYMENTSHORTCODE) {
        this.pAYMENTSHORTCODE = pAYMENTSHORTCODE;
    }

    public String getPAYMENTTYPE() {
        return pAYMENTTYPE;
    }

    public void setPAYMENTTYPE(String pAYMENTTYPE) {
        this.pAYMENTTYPE = pAYMENTTYPE;
    }

    public String getAUTHMODEL() {
        return aUTHMODEL;
    }

    public void setAUTHMODEL(String aUTHMODEL) {
        this.aUTHMODEL = aUTHMODEL;
    }

    public List<PostpaidBillDataBreakup> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<PostpaidBillDataBreakup> breakup) {
        this.breakup = breakup;
    }

    public String getTRANSSTATUS() {
        return tRANSSTATUS;
    }

    public void setTRANSSTATUS(String tRANSSTATUS) {
        this.tRANSSTATUS = tRANSSTATUS;
    }

    public String getCHARGERESPONSEMESSAGE() {
        return cHARGERESPONSEMESSAGE;
    }

    public void setCHARGERESPONSEMESSAGE(String cHARGERESPONSEMESSAGE) {
        this.cHARGERESPONSEMESSAGE = cHARGERESPONSEMESSAGE;
    }

    public String getRECHARGEDATE() {
        return rECHARGEDATE;
    }

    public void setRECHARGEDATE(String rECHARGEDATE) {
        this.rECHARGEDATE = rECHARGEDATE;
    }

    public String getCHARGETYPE() {
        return cHARGETYPE;
    }

    public void setCHARGETYPE(String cHARGETYPE) {
        this.cHARGETYPE = cHARGETYPE;
    }

    public String getTRANSREF() {
        return tRANSREF;
    }

    public void setTRANSREF(String tRANSREF) {
        this.tRANSREF = tRANSREF;
    }

    public String getFRAUDSTATUS() {
        return fRAUDSTATUS;
    }

    public void setFRAUDSTATUS(String fRAUDSTATUS) {
        this.fRAUDSTATUS = fRAUDSTATUS;
    }

}