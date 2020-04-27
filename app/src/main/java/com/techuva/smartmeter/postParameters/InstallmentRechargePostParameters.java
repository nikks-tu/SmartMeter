package com.techuva.smartmeter.postParameters;

public class InstallmentRechargePostParameters {
    String USN_No;
    String mgrPaymentId;
    String PaidBy;
    String Remarks;

    public InstallmentRechargePostParameters(String USN_No, String mgrPaymentId) {
        this.USN_No = USN_No;
        this.mgrPaymentId = mgrPaymentId;
    }

    public InstallmentRechargePostParameters(String USN_No, String mgrPaymentId, String paidBy, String remarks) {
        this.USN_No = USN_No;
        this.mgrPaymentId = mgrPaymentId;
        PaidBy = paidBy;
        Remarks = remarks;
    }


    public String getUSN_No() {
        return USN_No;
    }

    public void setUSN_No(String USN_No) {
        this.USN_No = USN_No;
    }

    public String getMgrPaymentId() {
        return mgrPaymentId;
    }

    public void setMgrPaymentId(String mgrPaymentId) {
        this.mgrPaymentId = mgrPaymentId;
    }
}
