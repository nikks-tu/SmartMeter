package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrepaidBillDetailsResultObject {

    @SerializedName("RECHARGE_AMOUNT")
    @Expose
    private Double rECHARGEAMOUNT;
    @SerializedName("Fixed_Charge")
    @Expose
    private Double fixedCharge;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("PAYMENT_DESCRIPTION")
    @Expose
    private String pAYMENTDESCRIPTION;
    @SerializedName("Usable_Amount")
    @Expose
    private Double usableAmount;
    @SerializedName("Service_Charge")
    @Expose
    private String serviceCharge;
    @SerializedName("VAT")
    @Expose
    private Double vAT;
    @SerializedName("Life_Saver")
    @Expose
    private Double lifeSaver;
    @SerializedName("TRANS_REF")
    @Expose
    private String tRANSREF;
    @SerializedName("RECHARGE_USABLE_AMOUNT")
    @Expose
    private Double rECHARGEUSABLEAMOUNT;
    @SerializedName("RECHARGE_DATE")
    @Expose
    private String rECHARGEDATE;

    public Double getRECHARGEAMOUNT() {
        return rECHARGEAMOUNT;
    }

    public void setRECHARGEAMOUNT(Double rECHARGEAMOUNT) {
        this.rECHARGEAMOUNT = rECHARGEAMOUNT;
    }

    public Double getFixedCharge() {
        return fixedCharge;
    }

    public void setFixedCharge(Double fixedCharge) {
        this.fixedCharge = fixedCharge;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getPAYMENTDESCRIPTION() {
        return pAYMENTDESCRIPTION;
    }

    public void setPAYMENTDESCRIPTION(String pAYMENTDESCRIPTION) {
        this.pAYMENTDESCRIPTION = pAYMENTDESCRIPTION;
    }

    public Double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Double usableAmount) {
        this.usableAmount = usableAmount;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getVAT() {
        return vAT;
    }

    public void setVAT(Double vAT) {
        this.vAT = vAT;
    }

    public Double getLifeSaver() {
        return lifeSaver;
    }

    public void setLifeSaver(Double lifeSaver) {
        this.lifeSaver = lifeSaver;
    }

    public String getTRANSREF() {
        return tRANSREF;
    }

    public void setTRANSREF(String tRANSREF) {
        this.tRANSREF = tRANSREF;
    }

    public Double getRECHARGEUSABLEAMOUNT() {
        return rECHARGEUSABLEAMOUNT;
    }

    public void setRECHARGEUSABLEAMOUNT(Double rECHARGEUSABLEAMOUNT) {
        this.rECHARGEUSABLEAMOUNT = rECHARGEUSABLEAMOUNT;
    }
    public String getrECHARGEDATE() {
        return rECHARGEDATE;
    }

    public void setrECHARGEDATE(String rECHARGEDATE) {
        this.rECHARGEDATE = rECHARGEDATE;
    }

}