package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MeterMigrationResultObject{

    @SerializedName("CONNECTION_TYPE_ID")
    @Expose
    private Integer cONNECTIONTYPEID;
    @SerializedName("CONNECTION_SHORT_CODE")
    @Expose
    private String cONNECTIONSHORTCODE;
    @SerializedName("NEW_USN_NO")
    @Expose
    private String nEWUSNNO;
    @SerializedName("PAID_STATUS")
    @Expose
    private Integer pAIDSTATUS;
    @SerializedName("CONSUMER_ICON")
    @Expose
    private Integer cONSUMERICON;
    @SerializedName("PREV_BALANCE_UNITS")
    @Expose
    private Integer pREVBALANCEUNITS;
    @SerializedName("CONSUMER_TYPE_ID")
    @Expose
    private Integer cONSUMERTYPEID;
    @SerializedName("OLD_DEVICE_ID")
    @Expose
    private String oLDDEVICEID;
    @SerializedName("MGR_PAYMENT_SCHEDULE")
    @Expose
    private ArrayList<MeterMigrationEMIDetailObject> mGRPAYMENTSCHEDULE = null;
    @SerializedName("MIGRATED_DATE")
    @Expose
    private String mIGRATEDDATE;
    @SerializedName("CONNECTION_ICON")
    @Expose
    private Integer cONNECTIONICON;
    @SerializedName("TYPE_DESCRIPTION")
    @Expose
    private String tYPEDESCRIPTION;
    @SerializedName("DEVICE_MIGRATION_ID")
    @Expose
    private Integer dEVICEMIGRATIONID;
    @SerializedName("MODULE")
    @Expose
    private String mODULE;
    @SerializedName("MIGRATED_BY")
    @Expose
    private Integer mIGRATEDBY;
    @SerializedName("SHORT_TEXT")
    @Expose
    private String sHORTTEXT;
    @SerializedName("OLD_USN_NO")
    @Expose
    private String oLDUSNNO;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("PREV_BALANCE_AMT")
    @Expose
    private Double pREVBALANCEAMT;
    @SerializedName("CURRENT_BALANCE")
    @Expose
    private Double cURRENTBALANCE;
    @SerializedName("CONNECTION_TYPE_DESC")
    @Expose
    private String cONNECTIONTYPEDESC;
    @SerializedName("TYPE_SHORT_CODE")
    @Expose
    private String tYPESHORTCODE;

    public Integer getCONNECTIONTYPEID() {
        return cONNECTIONTYPEID;
    }

    public void setCONNECTIONTYPEID(Integer cONNECTIONTYPEID) {
        this.cONNECTIONTYPEID = cONNECTIONTYPEID;
    }

    public String getCONNECTIONSHORTCODE() {
        return cONNECTIONSHORTCODE;
    }

    public void setCONNECTIONSHORTCODE(String cONNECTIONSHORTCODE) {
        this.cONNECTIONSHORTCODE = cONNECTIONSHORTCODE;
    }

    public String getNEWUSNNO() {
        return nEWUSNNO;
    }

    public void setNEWUSNNO(String nEWUSNNO) {
        this.nEWUSNNO = nEWUSNNO;
    }

    public Integer getPAIDSTATUS() {
        return pAIDSTATUS;
    }

    public void setPAIDSTATUS(Integer pAIDSTATUS) {
        this.pAIDSTATUS = pAIDSTATUS;
    }

    public Integer getCONSUMERICON() {
        return cONSUMERICON;
    }

    public void setCONSUMERICON(Integer cONSUMERICON) {
        this.cONSUMERICON = cONSUMERICON;
    }

    public Integer getPREVBALANCEUNITS() {
        return pREVBALANCEUNITS;
    }

    public void setPREVBALANCEUNITS(Integer pREVBALANCEUNITS) {
        this.pREVBALANCEUNITS = pREVBALANCEUNITS;
    }

    public Integer getCONSUMERTYPEID() {
        return cONSUMERTYPEID;
    }

    public void setCONSUMERTYPEID(Integer cONSUMERTYPEID) {
        this.cONSUMERTYPEID = cONSUMERTYPEID;
    }

    public String getOLDDEVICEID() {
        return oLDDEVICEID;
    }

    public void setOLDDEVICEID(String oLDDEVICEID) {
        this.oLDDEVICEID = oLDDEVICEID;
    }

    public ArrayList<MeterMigrationEMIDetailObject> getMGRPAYMENTSCHEDULE() {
        return mGRPAYMENTSCHEDULE;
    }

    public void setMGRPAYMENTSCHEDULE(ArrayList<MeterMigrationEMIDetailObject> mGRPAYMENTSCHEDULE) {
        this.mGRPAYMENTSCHEDULE = mGRPAYMENTSCHEDULE;
    }

    public String getMIGRATEDDATE() {
        return mIGRATEDDATE;
    }

    public void setMIGRATEDDATE(String mIGRATEDDATE) {
        this.mIGRATEDDATE = mIGRATEDDATE;
    }

    public Integer getCONNECTIONICON() {
        return cONNECTIONICON;
    }

    public void setCONNECTIONICON(Integer cONNECTIONICON) {
        this.cONNECTIONICON = cONNECTIONICON;
    }

    public String getTYPEDESCRIPTION() {
        return tYPEDESCRIPTION;
    }

    public void setTYPEDESCRIPTION(String tYPEDESCRIPTION) {
        this.tYPEDESCRIPTION = tYPEDESCRIPTION;
    }

    public Integer getDEVICEMIGRATIONID() {
        return dEVICEMIGRATIONID;
    }

    public void setDEVICEMIGRATIONID(Integer dEVICEMIGRATIONID) {
        this.dEVICEMIGRATIONID = dEVICEMIGRATIONID;
    }

    public String getMODULE() {
        return mODULE;
    }

    public void setMODULE(String mODULE) {
        this.mODULE = mODULE;
    }

    public Integer getMIGRATEDBY() {
        return mIGRATEDBY;
    }

    public void setMIGRATEDBY(Integer mIGRATEDBY) {
        this.mIGRATEDBY = mIGRATEDBY;
    }

    public String getSHORTTEXT() {
        return sHORTTEXT;
    }

    public void setSHORTTEXT(String sHORTTEXT) {
        this.sHORTTEXT = sHORTTEXT;
    }

    public String getOLDUSNNO() {
        return oLDUSNNO;
    }

    public void setOLDUSNNO(String oLDUSNNO) {
        this.oLDUSNNO = oLDUSNNO;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public Double getPREVBALANCEAMT() {
        return pREVBALANCEAMT;
    }

    public void setPREVBALANCEAMT(Double pREVBALANCEAMT) {
        this.pREVBALANCEAMT = pREVBALANCEAMT;
    }

    public Double getCURRENTBALANCE() {
        return cURRENTBALANCE;
    }

    public void setCURRENTBALANCE(Double cURRENTBALANCE) {
        this.cURRENTBALANCE = cURRENTBALANCE;
    }

    public String getCONNECTIONTYPEDESC() {
        return cONNECTIONTYPEDESC;
    }

    public void setCONNECTIONTYPEDESC(String cONNECTIONTYPEDESC) {
        this.cONNECTIONTYPEDESC = cONNECTIONTYPEDESC;
    }

    public String getTYPESHORTCODE() {
        return tYPESHORTCODE;
    }

    public void setTYPESHORTCODE(String tYPESHORTCODE) {
        this.tYPESHORTCODE = tYPESHORTCODE;
    }

}