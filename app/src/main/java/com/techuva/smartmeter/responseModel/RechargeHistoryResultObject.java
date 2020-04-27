package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeHistoryResultObject {

    @SerializedName("PAYMENT_ID")
    @Expose
    private Integer pAYMENTID;
    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("PAYMENT_MODE_ID")
    @Expose
    private Integer pAYMENTMODEID;
    @SerializedName("RECHARGE_AMOUNT")
    @Expose
    private Integer rECHARGEAMOUNT;
    @SerializedName("USABLE_AMOUNT")
    @Expose
    private Double uSABLEAMOUNT;
    @SerializedName("LIFESAVER_AMOUNT")
    @Expose
    private Integer lIFESAVERAMOUNT;
    @SerializedName("MIGRATION_AMOUNT")
    @Expose
    private Integer mIGRATIONAMOUNT;
    @SerializedName("METER_INST_AMOUNT")
    @Expose
    private Integer mETERINSTAMOUNT;
    @SerializedName("TAX_AMOUNT")
    @Expose
    private Double tAXAMOUNT;
    @SerializedName("RECHARGE_DATE")
    @Expose
    private String rECHARGEDATE;
    @SerializedName("TRANS_REF")
    @Expose
    private String tRANSREF;
    @SerializedName("TRANS_STATUS")
    @Expose
    private Integer tRANSSTATUS;
    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("PAID_BY")
    @Expose
    private String pAIDBY;
    @SerializedName("REMARKS")
    @Expose
    private String rEMARKS;
    @SerializedName("TRANS_DESCRIPTION")
    @Expose
    private String tRANSDESCRIPTION;
    @SerializedName("PAYMENT_DESCRIPTION")
    @Expose
    private String pAYMENTDESCRIPTION;
    @SerializedName("ORDER_REF_NO")
    @Expose
    private String oRDERREFNO;
    @SerializedName("CHARGE_RESPONSE_MESSAGE")
    @Expose
    private String cHARGERESPONSEMESSAGE;
    @SerializedName("PAYMENT_TYPE")
    @Expose
    private String pAYMENTTYPE;
    @SerializedName("UNITS_ADDED")
    @Expose
    private Double uNITSADDED;
    @SerializedName("UNIT_RATE")
    @Expose
    private Double uNITRATE;

    public Integer getPAYMENTID() {
        return pAYMENTID;
    }

    public void setPAYMENTID(Integer pAYMENTID) {
        this.pAYMENTID = pAYMENTID;
    }

    public String getUSNNO() {
        return uSNNO;
    }

    public void setUSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public Integer getPAYMENTMODEID() {
        return pAYMENTMODEID;
    }

    public void setPAYMENTMODEID(Integer pAYMENTMODEID) {
        this.pAYMENTMODEID = pAYMENTMODEID;
    }

    public Integer getRECHARGEAMOUNT() {
        return rECHARGEAMOUNT;
    }

    public void setRECHARGEAMOUNT(Integer rECHARGEAMOUNT) {
        this.rECHARGEAMOUNT = rECHARGEAMOUNT;
    }

    public Double getUSABLEAMOUNT() {
        return uSABLEAMOUNT;
    }

    public void setUSABLEAMOUNT(Double uSABLEAMOUNT) {
        this.uSABLEAMOUNT = uSABLEAMOUNT;
    }

    public Integer getLIFESAVERAMOUNT() {
        return lIFESAVERAMOUNT;
    }

    public void setLIFESAVERAMOUNT(Integer lIFESAVERAMOUNT) {
        this.lIFESAVERAMOUNT = lIFESAVERAMOUNT;
    }

    public Integer getMIGRATIONAMOUNT() {
        return mIGRATIONAMOUNT;
    }

    public void setMIGRATIONAMOUNT(Integer mIGRATIONAMOUNT) {
        this.mIGRATIONAMOUNT = mIGRATIONAMOUNT;
    }

    public Integer getMETERINSTAMOUNT() {
        return mETERINSTAMOUNT;
    }

    public void setMETERINSTAMOUNT(Integer mETERINSTAMOUNT) {
        this.mETERINSTAMOUNT = mETERINSTAMOUNT;
    }

    public Double getTAXAMOUNT() {
        return tAXAMOUNT;
    }

    public void setTAXAMOUNT(Double tAXAMOUNT) {
        this.tAXAMOUNT = tAXAMOUNT;
    }

    public String getRECHARGEDATE() {
        return rECHARGEDATE;
    }

    public void setRECHARGEDATE(String rECHARGEDATE) {
        this.rECHARGEDATE = rECHARGEDATE;
    }

    public String getTRANSREF() {
        return tRANSREF;
    }

    public void setTRANSREF(String tRANSREF) {
        this.tRANSREF = tRANSREF;
    }

    public Integer getTRANSSTATUS() {
        return tRANSSTATUS;
    }

    public void setTRANSSTATUS(Integer tRANSSTATUS) {
        this.tRANSSTATUS = tRANSSTATUS;
    }

    public String getCURRENCY() {
        return cURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getPAIDBY() {
        return pAIDBY;
    }

    public void setPAIDBY(String pAIDBY) {
        this.pAIDBY = pAIDBY;
    }

    public String getREMARKS() {
        return rEMARKS;
    }

    public void setREMARKS(String rEMARKS) {
        this.rEMARKS = rEMARKS;
    }

    public String getTRANSDESCRIPTION() {
        return tRANSDESCRIPTION;
    }

    public void setTRANSDESCRIPTION(String tRANSDESCRIPTION) {
        this.tRANSDESCRIPTION = tRANSDESCRIPTION;
    }

    public String getPAYMENTDESCRIPTION() {
        return pAYMENTDESCRIPTION;
    }

    public void setPAYMENTDESCRIPTION(String pAYMENTDESCRIPTION) {
        this.pAYMENTDESCRIPTION = pAYMENTDESCRIPTION;
    }

    public String getORDERREFNO() {
        return oRDERREFNO;
    }

    public void setORDERREFNO(String oRDERREFNO) {
        this.oRDERREFNO = oRDERREFNO;
    }

    public String getCHARGERESPONSEMESSAGE() {
        return cHARGERESPONSEMESSAGE;
    }

    public void setCHARGERESPONSEMESSAGE(String cHARGERESPONSEMESSAGE) {
        this.cHARGERESPONSEMESSAGE = cHARGERESPONSEMESSAGE;
    }

    public String getPAYMENTTYPE() {
        return pAYMENTTYPE;
    }

    public void setPAYMENTTYPE(String pAYMENTTYPE) {
        this.pAYMENTTYPE = pAYMENTTYPE;
    }

    public Double getUNITSADDED() {
        return uNITSADDED;
    }

    public void setUNITSADDED(Double uNITSADDED) {
        this.uNITSADDED = uNITSADDED;
    }

    public Double getUNITRATE() {
        return uNITRATE;
    }

    public void setUNITRATE(Double uNITRATE) {
        this.uNITRATE = uNITRATE;
    }

}