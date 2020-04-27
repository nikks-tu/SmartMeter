package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostpaidBillDataResultObject {

    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("billingDate")
    @Expose
    private String billingDate;
    @SerializedName("postpaidBillingId")
    @Expose
    private Integer postpaidBillingId;
    @SerializedName("USN_No")
    @Expose
    private String uSNNo;
    @SerializedName("breakup")
    @Expose
    private List<RechargeCalBreakupObject> breakup = null;
    @SerializedName("usableAmount")
    @Expose
    private Double usableAmount;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("unitsConsumed")
    @Expose
    private Double unitsConsumed;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public Integer getPostpaidBillingId() {
        return postpaidBillingId;
    }

    public void setPostpaidBillingId(Integer postpaidBillingId) {
        this.postpaidBillingId = postpaidBillingId;
    }

    public String getUSNNo() {
        return uSNNo;
    }

    public void setUSNNo(String uSNNo) {
        this.uSNNo = uSNNo;
    }

    public List<RechargeCalBreakupObject> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<RechargeCalBreakupObject> breakup) {
        this.breakup = breakup;
    }

    public Double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Double usableAmount) {
        this.usableAmount = usableAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(Double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

}