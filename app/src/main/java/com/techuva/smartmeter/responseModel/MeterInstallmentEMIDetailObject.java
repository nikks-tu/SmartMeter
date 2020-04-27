package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterInstallmentEMIDetailObject {

    @SerializedName("LOAN_EMI_ID")
    @Expose
    private Integer lOANEMIID;
    @SerializedName("METER_LOAN_ID")
    @Expose
    private Integer mETERLOANID;
    @SerializedName("INSTALLMENT_NAME")
    @Expose
    private String iNSTALLMENTNAME;
    @SerializedName("EMI_AMOUNT")
    @Expose
    private Double eMIAMOUNT;
    @SerializedName("PRINCIPAL_AMOUNT")
    @Expose
    private Double pRINCIPALAMOUNT;
    @SerializedName("INTEREST_AMOUNT")
    @Expose
    private Double iNTERESTAMOUNT;
    @SerializedName("EMI_DUE_DATE")
    @Expose
    private String eMIDUEDATE;
    @SerializedName("STATUS_ID")
    @Expose
    private Integer sTATUSID;
    @SerializedName("PAID_AMOUNT")
    @Expose
    private Double pAIDAMOUNT;
    @SerializedName("TAX_AMOUNT")
    @Expose
    private Double tAXAMOUNT;
    @SerializedName("PAID_ON")
    @Expose
    private String pAIDON;
    @SerializedName("PAID_BY")
    @Expose
    private String pAIDBY;
    @SerializedName("PAYMENT_MODE_ID")
    @Expose
    private Integer pAYMENTMODEID;
    @SerializedName("IS_ACTIVE")
    @Expose
    private Boolean iSACTIVE;
    @SerializedName("PAYMENT_ID")
    @Expose
    private Integer pAYMENTID;
    @SerializedName("CREATED_ON")
    @Expose
    private String cREATEDON;
    @SerializedName("LAST_MODIFIED_ON")
    @Expose
    private String lASTMODIFIEDON;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("SHORT_TEXT")
    @Expose
    private String sHORTTEXT;
    @SerializedName("PAID_BY_NAME")
    @Expose
    private String pAIDBYNAME;
    @SerializedName("PAYMENT_DESCRIPTION")
    @Expose
    private String pAYMENTDESCRIPTION;
    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("RECHARGE_AMOUNT")
    @Expose
    private Double rECHARGEAMOUNT;
    @SerializedName("USABLE_AMOUNT")
    @Expose
    private Double uSABLEAMOUNT;
    @SerializedName("RECHARGE_DATE")
    @Expose
    private String rECHARGEDATE;
    @SerializedName("TRANS_REF")
    @Expose
    private String tRANSREF;
    @SerializedName("TRANS_STATUS")
    @Expose
    private String tRANSSTATUS;
    @SerializedName("REMARKS")
    @Expose
    private String rEMARKS;
    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("PAYMENT_STATUS")
    @Expose
    private String pAYMENTSTATUS;

    public Integer getlOANEMIID() {
        return lOANEMIID;
    }

    public void setlOANEMIID(Integer lOANEMIID) {
        this.lOANEMIID = lOANEMIID;
    }

    public Integer getmETERLOANID() {
        return mETERLOANID;
    }

    public void setmETERLOANID(Integer mETERLOANID) {
        this.mETERLOANID = mETERLOANID;
    }

    public String getiNSTALLMENTNAME() {
        return iNSTALLMENTNAME;
    }

    public void setiNSTALLMENTNAME(String iNSTALLMENTNAME) {
        this.iNSTALLMENTNAME = iNSTALLMENTNAME;
    }

    public Double geteMIAMOUNT() {
        return eMIAMOUNT;
    }

    public void seteMIAMOUNT(Double eMIAMOUNT) {
        this.eMIAMOUNT = eMIAMOUNT;
    }

    public Double getpRINCIPALAMOUNT() {
        return pRINCIPALAMOUNT;
    }

    public void setpRINCIPALAMOUNT(Double pRINCIPALAMOUNT) {
        this.pRINCIPALAMOUNT = pRINCIPALAMOUNT;
    }

    public Double getiNTERESTAMOUNT() {
        return iNTERESTAMOUNT;
    }

    public void setiNTERESTAMOUNT(Double iNTERESTAMOUNT) {
        this.iNTERESTAMOUNT = iNTERESTAMOUNT;
    }

    public String geteMIDUEDATE() {
        return eMIDUEDATE;
    }

    public void seteMIDUEDATE(String eMIDUEDATE) {
        this.eMIDUEDATE = eMIDUEDATE;
    }

    public Integer getsTATUSID() {
        return sTATUSID;
    }

    public void setsTATUSID(Integer sTATUSID) {
        this.sTATUSID = sTATUSID;
    }

    public Double getpAIDAMOUNT() {
        return pAIDAMOUNT;
    }

    public void setpAIDAMOUNT(Double pAIDAMOUNT) {
        this.pAIDAMOUNT = pAIDAMOUNT;
    }

    public Double gettAXAMOUNT() {
        return tAXAMOUNT;
    }

    public void settAXAMOUNT(Double tAXAMOUNT) {
        this.tAXAMOUNT = tAXAMOUNT;
    }

    public String getpAIDON() {
        return pAIDON;
    }

    public void setpAIDON(String pAIDON) {
        this.pAIDON = pAIDON;
    }

    public String getpAIDBY() {
        return pAIDBY;
    }

    public void setpAIDBY(String pAIDBY) {
        this.pAIDBY = pAIDBY;
    }

    public Integer getpAYMENTMODEID() {
        return pAYMENTMODEID;
    }

    public void setpAYMENTMODEID(Integer pAYMENTMODEID) {
        this.pAYMENTMODEID = pAYMENTMODEID;
    }

    public Boolean getiSACTIVE() {
        return iSACTIVE;
    }

    public void setiSACTIVE(Boolean iSACTIVE) {
        this.iSACTIVE = iSACTIVE;
    }

    public Integer getpAYMENTID() {
        return pAYMENTID;
    }

    public void setpAYMENTID(Integer pAYMENTID) {
        this.pAYMENTID = pAYMENTID;
    }

    public String getcREATEDON() {
        return cREATEDON;
    }

    public void setcREATEDON(String cREATEDON) {
        this.cREATEDON = cREATEDON;
    }

    public String getlASTMODIFIEDON() {
        return lASTMODIFIEDON;
    }

    public void setlASTMODIFIEDON(String lASTMODIFIEDON) {
        this.lASTMODIFIEDON = lASTMODIFIEDON;
    }

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getsHORTTEXT() {
        return sHORTTEXT;
    }

    public void setsHORTTEXT(String sHORTTEXT) {
        this.sHORTTEXT = sHORTTEXT;
    }

    public String getpAIDBYNAME() {
        return pAIDBYNAME;
    }

    public void setpAIDBYNAME(String pAIDBYNAME) {
        this.pAIDBYNAME = pAIDBYNAME;
    }

    public String getpAYMENTDESCRIPTION() {
        return pAYMENTDESCRIPTION;
    }

    public void setpAYMENTDESCRIPTION(String pAYMENTDESCRIPTION) {
        this.pAYMENTDESCRIPTION = pAYMENTDESCRIPTION;
    }

    public String getuSNNO() {
        return uSNNO;
    }

    public void setuSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public Double getrECHARGEAMOUNT() {
        return rECHARGEAMOUNT;
    }

    public void setrECHARGEAMOUNT(Double rECHARGEAMOUNT) {
        this.rECHARGEAMOUNT = rECHARGEAMOUNT;
    }

    public Double getuSABLEAMOUNT() {
        return uSABLEAMOUNT;
    }

    public void setuSABLEAMOUNT(Double uSABLEAMOUNT) {
        this.uSABLEAMOUNT = uSABLEAMOUNT;
    }

    public String getrECHARGEDATE() {
        return rECHARGEDATE;
    }

    public void setrECHARGEDATE(String rECHARGEDATE) {
        this.rECHARGEDATE = rECHARGEDATE;
    }

    public String gettRANSREF() {
        return tRANSREF;
    }

    public void settRANSREF(String tRANSREF) {
        this.tRANSREF = tRANSREF;
    }

    public String gettRANSSTATUS() {
        return tRANSSTATUS;
    }

    public void settRANSSTATUS(String tRANSSTATUS) {
        this.tRANSSTATUS = tRANSSTATUS;
    }

    public String getrEMARKS() {
        return rEMARKS;
    }

    public void setrEMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getcURRENCY() {
        return cURRENCY;
    }

    public void setcURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getpAYMENTSTATUS() {
        return pAYMENTSTATUS;
    }

    public void setpAYMENTSTATUS(String pAYMENTSTATUS) {
        this.pAYMENTSTATUS = pAYMENTSTATUS;
    }


}