package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountListResultObject  {

    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("METER_CONNECTION_ID")
    @Expose
    private Integer mETERCONNECTIONID;
    @SerializedName("SERIAL_NO")
    @Expose
    private String sERIALNO;
    @SerializedName("PHASE")
    @Expose
    private Integer pHASE;
    @SerializedName("CONNECTION_SHORT_CODE")
    @Expose
    private String cONNECTIONSHORTCODE;
    @SerializedName("CONNECTION_TYPE_DESC")
    @Expose
    private String cONNECTIONTYPEDESC;
    @SerializedName("TYPE_DESCRIPTION")
    @Expose
    private String tYPEDESCRIPTION;
    @SerializedName("SLAB_SHORT_CODE")
    @Expose
    private String sLABSHORTCODE;
    @SerializedName("STATE_DESCRIPTION")
    @Expose
    private String sTATEDESCRIPTION;
    @SerializedName("INVENTORY_ID")
    @Expose
    private Integer iNVENTORYID;
    @SerializedName("INVENTORY_NAME")
    @Expose
    private String iNVENTORYNAME;
    @SerializedName("STATUS_ID")
    @Expose
    private Integer sTATUSID;
    @SerializedName("HARD_CUTOFF")
    @Expose
    private Object hARDCUTOFF;
    @SerializedName("STATUS_DESCRIPTION")
    @Expose
    private String sTATUSDESCRIPTION;
    @SerializedName("CYCLE_DESCRIPTION")
    @Expose
    private String cYCLEDESCRIPTION;
    @SerializedName("DISCOUNT_PERCENTAGE")
    @Expose
    private Object dISCOUNTPERCENTAGE;
    @SerializedName("DISTRICT_DESCRIPTION")
    @Expose
    private String dISTRICTDESCRIPTION;
    @SerializedName("POWER_STATUS")
    @Expose
    private Object pOWERSTATUS;
    @SerializedName("LIFE_SAVER")
    @Expose
    private Boolean lIFESAVER;
    @SerializedName("BALANCE")
    @Expose
    private Double bALANCE;

    public String getUSNNO() {
        return uSNNO;
    }

    public void setUSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public Integer getMETERCONNECTIONID() {
        return mETERCONNECTIONID;
    }

    public void setMETERCONNECTIONID(Integer mETERCONNECTIONID) {
        this.mETERCONNECTIONID = mETERCONNECTIONID;
    }

    public String getSERIALNO() {
        return sERIALNO;
    }

    public void setSERIALNO(String sERIALNO) {
        this.sERIALNO = sERIALNO;
    }

    public Integer getPHASE() {
        return pHASE;
    }

    public void setPHASE(Integer pHASE) {
        this.pHASE = pHASE;
    }

    public String getCONNECTIONSHORTCODE() {
        return cONNECTIONSHORTCODE;
    }

    public void setCONNECTIONSHORTCODE(String cONNECTIONSHORTCODE) {
        this.cONNECTIONSHORTCODE = cONNECTIONSHORTCODE;
    }

    public String getCONNECTIONTYPEDESC() {
        return cONNECTIONTYPEDESC;
    }

    public void setCONNECTIONTYPEDESC(String cONNECTIONTYPEDESC) {
        this.cONNECTIONTYPEDESC = cONNECTIONTYPEDESC;
    }

    public String getTYPEDESCRIPTION() {
        return tYPEDESCRIPTION;
    }

    public void setTYPEDESCRIPTION(String tYPEDESCRIPTION) {
        this.tYPEDESCRIPTION = tYPEDESCRIPTION;
    }

    public String getSLABSHORTCODE() {
        return sLABSHORTCODE;
    }

    public void setSLABSHORTCODE(String sLABSHORTCODE) {
        this.sLABSHORTCODE = sLABSHORTCODE;
    }

    public String getSTATEDESCRIPTION() {
        return sTATEDESCRIPTION;
    }

    public void setSTATEDESCRIPTION(String sTATEDESCRIPTION) {
        this.sTATEDESCRIPTION = sTATEDESCRIPTION;
    }

    public Integer getINVENTORYID() {
        return iNVENTORYID;
    }

    public void setINVENTORYID(Integer iNVENTORYID) {
        this.iNVENTORYID = iNVENTORYID;
    }

    public String getINVENTORYNAME() {
        return iNVENTORYNAME;
    }

    public void setINVENTORYNAME(String iNVENTORYNAME) {
        this.iNVENTORYNAME = iNVENTORYNAME;
    }

    public Integer getSTATUSID() {
        return sTATUSID;
    }

    public void setSTATUSID(Integer sTATUSID) {
        this.sTATUSID = sTATUSID;
    }

    public Object getHARDCUTOFF() {
        return hARDCUTOFF;
    }

    public void setHARDCUTOFF(Object hARDCUTOFF) {
        this.hARDCUTOFF = hARDCUTOFF;
    }

    public String getSTATUSDESCRIPTION() {
        return sTATUSDESCRIPTION;
    }

    public void setSTATUSDESCRIPTION(String sTATUSDESCRIPTION) {
        this.sTATUSDESCRIPTION = sTATUSDESCRIPTION;
    }

    public String getCYCLEDESCRIPTION() {
        return cYCLEDESCRIPTION;
    }

    public void setCYCLEDESCRIPTION(String cYCLEDESCRIPTION) {
        this.cYCLEDESCRIPTION = cYCLEDESCRIPTION;
    }

    public Object getDISCOUNTPERCENTAGE() {
        return dISCOUNTPERCENTAGE;
    }

    public void setDISCOUNTPERCENTAGE(Object dISCOUNTPERCENTAGE) {
        this.dISCOUNTPERCENTAGE = dISCOUNTPERCENTAGE;
    }

    public String getDISTRICTDESCRIPTION() {
        return dISTRICTDESCRIPTION;
    }

    public void setDISTRICTDESCRIPTION(String dISTRICTDESCRIPTION) {
        this.dISTRICTDESCRIPTION = dISTRICTDESCRIPTION;
    }

    public Object getPOWERSTATUS() {
        return pOWERSTATUS;
    }

    public void setPOWERSTATUS(Object pOWERSTATUS) {
        this.pOWERSTATUS = pOWERSTATUS;
    }

    public Boolean getLIFESAVER() {
        return lIFESAVER;
    }

    public void setLIFESAVER(Boolean lIFESAVER) {
        this.lIFESAVER = lIFESAVER;
    }

    public Double getBALANCE() {
        return bALANCE;
    }

    public void setBALANCE(Double bALANCE) {
        this.bALANCE = bALANCE;
    }

}