package com.techuva.smartmeter.postParameters;

public class RechargeInsertPostParameter {

    private String USN_No;
    private Double rechargeAmount;
    private String PaidBy;
    private String Remarks;
    private int postpaidBillingId;

    public RechargeInsertPostParameter(String USN_No, double usableAmount, String paidBy, String remarks) {
        this.USN_No = USN_No;
        this.rechargeAmount = usableAmount;
        PaidBy = paidBy;
        Remarks = remarks;
    }

    public RechargeInsertPostParameter(String USN_No, String paidBy, String remarks, int postpaidBillingId) {
        this.USN_No = USN_No;
        PaidBy = paidBy;
        Remarks = remarks;
        this.postpaidBillingId = postpaidBillingId;
    }

    public String getUSN_No() {
        return USN_No;
    }

    public void setUSN_No(String USN_No) {
        this.USN_No = USN_No;
    }

    public Double getUsableAmount() {
        return rechargeAmount;
    }

    public void setUsableAmount(Double usableAmount) {
        this.rechargeAmount = usableAmount;
    }

    public String getPaidBy() {
        return PaidBy;
    }

    public void setPaidBy(String paidBy) {
        PaidBy = paidBy;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public int getPostpaidBillingId() {
        return postpaidBillingId;
    }

    public void setPostpaidBillingId(int postpaidBillingId) {
        this.postpaidBillingId = postpaidBillingId;
    }


}
