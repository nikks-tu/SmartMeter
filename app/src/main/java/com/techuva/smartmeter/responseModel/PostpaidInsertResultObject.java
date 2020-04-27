package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostpaidInsertResultObject {

    @SerializedName("GatewayInfo")
    @Expose
    private PostpaidGatewayInfoObject gatewayInfo;
    @SerializedName("postpaidBillingId")
    @Expose
    private Integer postpaidBillingId;
    @SerializedName("paidBy")
    @Expose
    private String paidBy;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("billingDate")
    @Expose
    private String billingDate;
    @SerializedName("USN_No")
    @Expose
    private String uSNNo;
    @SerializedName("breakup")
    @Expose
    private List<PostpaidGatewayInfoObject> breakup = null;
    @SerializedName("txref")
    @Expose
    private String txref;
    @SerializedName("usableAmount")
    @Expose
    private Integer usableAmount;
    @SerializedName("rechargePaymentId")
    @Expose
    private Integer rechargePaymentId;
    @SerializedName("currency")
    @Expose
    private String currency;

    public PostpaidGatewayInfoObject getGatewayInfo() {
        return gatewayInfo;
    }

    public void setGatewayInfo(PostpaidGatewayInfoObject gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }

    public Integer getPostpaidBillingId() {
        return postpaidBillingId;
    }

    public void setPostpaidBillingId(Integer postpaidBillingId) {
        this.postpaidBillingId = postpaidBillingId;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getUSNNo() {
        return uSNNo;
    }

    public void setUSNNo(String uSNNo) {
        this.uSNNo = uSNNo;
    }

    public List<PostpaidGatewayInfoObject> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<PostpaidGatewayInfoObject> breakup) {
        this.breakup = breakup;
    }

    public String getTxref() {
        return txref;
    }

    public void setTxref(String txref) {
        this.txref = txref;
    }

    public Integer getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Integer usableAmount) {
        this.usableAmount = usableAmount;
    }

    public Integer getRechargePaymentId() {
        return rechargePaymentId;
    }

    public void setRechargePaymentId(Integer rechargePaymentId) {
        this.rechargePaymentId = rechargePaymentId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}