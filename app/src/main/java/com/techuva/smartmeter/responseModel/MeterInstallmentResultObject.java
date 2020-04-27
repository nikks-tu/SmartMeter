package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MeterInstallmentResultObject {

    @SerializedName("METER_LOAN_ID")
    @Expose
    private Integer mETERLOANID;
    @SerializedName("METER_CONNECTION_ID")
    @Expose
    private Integer mETERCONNECTIONID;
    @SerializedName("TOTAL_LOAN")
    @Expose
    private Double tOTALLOAN;
    @SerializedName("INTEREST_RATE")
    @Expose
    private Double iNTERESTRATE;
    @SerializedName("TOTAL_TENURE")
    @Expose
    private Integer tOTALTENURE;
    @SerializedName("FINANCIER_ID")
    @Expose
    private Integer fINANCIERID;
    @SerializedName("INTEREST_AMOUNT")
    @Expose
    private Double iNTERESTAMOUNT;
    @SerializedName("CURRENT_BALANCE")
    @Expose
    private Double cURRENTBALANCE;
    @SerializedName("DOWN_PAYMENT")
    @Expose
    private Double dOWNPAYMENT;
    @SerializedName("STATUS_ID")
    @Expose
    private Integer sTATUSID;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("SHORT_TEXT")
    @Expose
    private String sHORTTEXT;
    @SerializedName("FINANCIER_SHORT_CODE")
    @Expose
    private String fINANCIERSHORTCODE;
    @SerializedName("FINANCIER_NAME")
    @Expose
    private String fINANCIERNAME;
    @SerializedName("METER_COST")
    @Expose
    private Double mETERCOST;
    @SerializedName("INVENTORY_MODEL")
    @Expose
    private String iNVENTORYMODEL;
    @SerializedName("EMI_DETAILS")
    @Expose
    private ArrayList<MeterInstallmentEMIDetailObject> eMIDETAILS = null;

    public Integer getMETERLOANID() {
        return mETERLOANID;
    }

    public void setMETERLOANID(Integer mETERLOANID) {
        this.mETERLOANID = mETERLOANID;
    }

    public Integer getMETERCONNECTIONID() {
        return mETERCONNECTIONID;
    }

    public void setMETERCONNECTIONID(Integer mETERCONNECTIONID) {
        this.mETERCONNECTIONID = mETERCONNECTIONID;
    }

    public Double getTOTALLOAN() {
        return tOTALLOAN;
    }

    public void setTOTALLOAN(Double tOTALLOAN) {
        this.tOTALLOAN = tOTALLOAN;
    }

    public Double getINTERESTRATE() {
        return iNTERESTRATE;
    }

    public void setINTERESTRATE(Double iNTERESTRATE) {
        this.iNTERESTRATE = iNTERESTRATE;
    }

    public Integer getTOTALTENURE() {
        return tOTALTENURE;
    }

    public void setTOTALTENURE(Integer tOTALTENURE) {
        this.tOTALTENURE = tOTALTENURE;
    }

    public Integer getFINANCIERID() {
        return fINANCIERID;
    }

    public void setFINANCIERID(Integer fINANCIERID) {
        this.fINANCIERID = fINANCIERID;
    }

    public Double getINTERESTAMOUNT() {
        return iNTERESTAMOUNT;
    }

    public void setINTERESTAMOUNT(Double iNTERESTAMOUNT) {
        this.iNTERESTAMOUNT = iNTERESTAMOUNT;
    }

    public Double getCURRENTBALANCE() {
        return cURRENTBALANCE;
    }

    public void setCURRENTBALANCE(Double cURRENTBALANCE) {
        this.cURRENTBALANCE = cURRENTBALANCE;
    }

    public Double getDOWNPAYMENT() {
        return dOWNPAYMENT;
    }

    public void setDOWNPAYMENT(Double dOWNPAYMENT) {
        this.dOWNPAYMENT = dOWNPAYMENT;
    }

    public Integer getSTATUSID() {
        return sTATUSID;
    }

    public void setSTATUSID(Integer sTATUSID) {
        this.sTATUSID = sTATUSID;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getSHORTTEXT() {
        return sHORTTEXT;
    }

    public void setSHORTTEXT(String sHORTTEXT) {
        this.sHORTTEXT = sHORTTEXT;
    }

    public String getFINANCIERSHORTCODE() {
        return fINANCIERSHORTCODE;
    }

    public void setFINANCIERSHORTCODE(String fINANCIERSHORTCODE) {
        this.fINANCIERSHORTCODE = fINANCIERSHORTCODE;
    }

    public String getFINANCIERNAME() {
        return fINANCIERNAME;
    }

    public void setFINANCIERNAME(String fINANCIERNAME) {
        this.fINANCIERNAME = fINANCIERNAME;
    }

    public Double getMETERCOST() {
        return mETERCOST;
    }

    public void setMETERCOST(Double mETERCOST) {
        this.mETERCOST = mETERCOST;
    }

    public String getINVENTORYMODEL() {
        return iNVENTORYMODEL;
    }

    public void setINVENTORYMODEL(String iNVENTORYMODEL) {
        this.iNVENTORYMODEL = iNVENTORYMODEL;
    }

    public ArrayList<MeterInstallmentEMIDetailObject> getEMIDETAILS() {
        return eMIDETAILS;
    }

    public void setEMIDETAILS(ArrayList<MeterInstallmentEMIDetailObject> eMIDETAILS) {
        this.eMIDETAILS = eMIDETAILS;
    }

}