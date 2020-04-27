package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidHistoryResultObject  {

    @SerializedName("POSTPAID_BILLING_ID")
    @Expose
    private Integer pOSTPAIDBILLINGID;
    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("UNITS_CONSUMED")
    @Expose
    private Integer uNITSCONSUMED;
    @SerializedName("BILLING_AMOUNT")
    @Expose
    private Double bILLINGAMOUNT;
    @SerializedName("BILLING_DATE")
    @Expose
    private String bILLINGDATE;
    @SerializedName("DUE_DATE")
    @Expose
    private String dUEDATE;
    @SerializedName("PAID_STATUS")
    @Expose
    private String pAIDSTATUS;
    @SerializedName("IS_ACTIVE")
    @Expose
    private Boolean iSACTIVE;
    @SerializedName("CREATED_ON")
    @Expose
    private String cREATEDON;
    @SerializedName("CREATED_BY")
    @Expose
    private Integer cREATEDBY;
    @SerializedName("LAST_MODIFIED_ON")
    @Expose
    private String lASTMODIFIEDON;
    @SerializedName("BILL_LINK")
    @Expose
    private String BILL_LINK;
    @SerializedName("LAST_MODIFIED_BY")
    @Expose
    private Integer lASTMODIFIEDBY;
    @SerializedName("RECHARGE_PAYMENT_ID")
    @Expose
    private Integer rECHARGEPAYMENTID;
    @SerializedName("FROM_DATE")
    @Expose
    private String fROMDATE;
    @SerializedName("TO_DATE")
    @Expose
    private String tODATE;
    @SerializedName("EMAIL_SENT")
    @Expose
    private Integer eMAILSENT;

    public Integer getPOSTPAIDBILLINGID() {
        return pOSTPAIDBILLINGID;
    }

    public void setPOSTPAIDBILLINGID(Integer pOSTPAIDBILLINGID) {
        this.pOSTPAIDBILLINGID = pOSTPAIDBILLINGID;
    }

    public String getUSNNO() {
        return uSNNO;
    }

    public void setUSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public Integer getUNITSCONSUMED() {
        return uNITSCONSUMED;
    }

    public void setUNITSCONSUMED(Integer uNITSCONSUMED) {
        this.uNITSCONSUMED = uNITSCONSUMED;
    }

    public Double getBILLINGAMOUNT() {
        return bILLINGAMOUNT;
    }

    public void setBILLINGAMOUNT(Double bILLINGAMOUNT) {
        this.bILLINGAMOUNT = bILLINGAMOUNT;
    }

    public String getBILLINGDATE() {
        return bILLINGDATE;
    }

    public void setBILLINGDATE(String bILLINGDATE) {
        this.bILLINGDATE = bILLINGDATE;
    }

    public String getDUEDATE() {
        return dUEDATE;
    }

    public void setDUEDATE(String dUEDATE) {
        this.dUEDATE = dUEDATE;
    }

    public String getPAIDSTATUS() {
        return pAIDSTATUS;
    }

    public void setPAIDSTATUS(String pAIDSTATUS) {
        this.pAIDSTATUS = pAIDSTATUS;
    }

    public Boolean getISACTIVE() {
        return iSACTIVE;
    }

    public void setISACTIVE(Boolean iSACTIVE) {
        this.iSACTIVE = iSACTIVE;
    }

    public String getCREATEDON() {
        return cREATEDON;
    }

    public void setCREATEDON(String cREATEDON) {
        this.cREATEDON = cREATEDON;
    }

    public Integer getCREATEDBY() {
        return cREATEDBY;
    }

    public void setCREATEDBY(Integer cREATEDBY) {
        this.cREATEDBY = cREATEDBY;
    }

    public String getLASTMODIFIEDON() {
        return lASTMODIFIEDON;
    }

    public void setLASTMODIFIEDON(String lASTMODIFIEDON) {
        this.lASTMODIFIEDON = lASTMODIFIEDON;
    }

    public Integer getLASTMODIFIEDBY() {
        return lASTMODIFIEDBY;
    }

    public void setLASTMODIFIEDBY(Integer lASTMODIFIEDBY) {
        this.lASTMODIFIEDBY = lASTMODIFIEDBY;
    }

    public Integer getRECHARGEPAYMENTID() {
        return rECHARGEPAYMENTID;
    }

    public void setRECHARGEPAYMENTID(Integer rECHARGEPAYMENTID) {
        this.rECHARGEPAYMENTID = rECHARGEPAYMENTID;
    }

    public String getFROMDATE() {
        return fROMDATE;
    }

    public void setFROMDATE(String fROMDATE) {
        this.fROMDATE = fROMDATE;
    }

    public String getTODATE() {
        return tODATE;
    }

    public void setTODATE(String tODATE) {
        this.tODATE = tODATE;
    }

    public Integer getEMAILSENT() {
        return eMAILSENT;
    }

    public void setEMAILSENT(Integer eMAILSENT) {
        this.eMAILSENT = eMAILSENT;
    }


    public String getBILL_LINK() {
        return BILL_LINK;
    }

    public void setBILL_LINK(String BILL_LINK) {
        this.BILL_LINK = BILL_LINK;
    }

}