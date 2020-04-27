package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumedDataHistoryObject {

    @SerializedName("ENG_CONSUMPTION_ID")
    @Expose
    private String eNGCONSUMPTIONID;
    @SerializedName("USN_NO")
    @Expose
    private String uSNNO;
    @SerializedName("UNITS_CONSUMED")
    @Expose
    private String uNITSCONSUMED;
    @SerializedName("AMOUNT")
    @Expose
    private String aMOUNT;
    @SerializedName("TRANSACTION_TYPE")
    @Expose
    private String tRANSACTIONTYPE;
    @SerializedName("TRANSACTION_DATE")
    @Expose
    private String tRANSACTIONDATE;
    @SerializedName("KWH")
    @Expose
    private String kWH;
    @SerializedName("UNIT_RATE")
    @Expose
    private String uNITRATE;

    public String getENGCONSUMPTIONID() {
        return eNGCONSUMPTIONID;
    }

    public void setENGCONSUMPTIONID(String eNGCONSUMPTIONID) {
        this.eNGCONSUMPTIONID = eNGCONSUMPTIONID;
    }

    public String getUSNNO() {
        return uSNNO;
    }

    public void setUSNNO(String uSNNO) {
        this.uSNNO = uSNNO;
    }

    public String getUNITSCONSUMED() {
        return uNITSCONSUMED;
    }

    public void setUNITSCONSUMED(String uNITSCONSUMED) {
        this.uNITSCONSUMED = uNITSCONSUMED;
    }

    public String getAMOUNT() {
        return aMOUNT;
    }

    public void setAMOUNT(String aMOUNT) {
        this.aMOUNT = aMOUNT;
    }

    public String getTRANSACTIONTYPE() {
        return tRANSACTIONTYPE;
    }

    public void setTRANSACTIONTYPE(String tRANSACTIONTYPE) {
        this.tRANSACTIONTYPE = tRANSACTIONTYPE;
    }

    public String getTRANSACTIONDATE() {
        return tRANSACTIONDATE;
    }

    public void setTRANSACTIONDATE(String tRANSACTIONDATE) {
        this.tRANSACTIONDATE = tRANSACTIONDATE;
    }

    public String getKWH() {
        return kWH;
    }

    public void setKWH(String kWH) {
        this.kWH = kWH;
    }

    public String getUNITRATE() {
        return uNITRATE;
    }

    public void setUNITRATE(String uNITRATE) {
        this.uNITRATE = uNITRATE;
    }

}
