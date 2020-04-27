package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargeInsertResultObject {

    @SerializedName("GatewayInfo")
    @Expose
    private GatewayInfoObject gatewayInfo;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("USN_No")
    @Expose
    private String uSNNo;
    @SerializedName("breakup")
    @Expose
    private List<RechargeInserBreakupObject> breakup = null;
    @SerializedName("txref")
    @Expose
    private String txref;
    @SerializedName("paidBy")
    @Expose
    private String paidBy;
    @SerializedName("usableAmount")
    @Expose
    private Double usableAmount;
    @SerializedName("rechargePaymentId")
    @Expose
    private Integer rechargePaymentId;
    @SerializedName("currency")
    @Expose
    private String currency;

    public GatewayInfoObject getGatewayInfo() {
        return gatewayInfo;
    }

    public void setGatewayInfo(GatewayInfoObject gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUSNNo() {
        return uSNNo;
    }

    public void setUSNNo(String uSNNo) {
        this.uSNNo = uSNNo;
    }

    public List<RechargeInserBreakupObject> getBreakup() {
        return breakup;
    }

    public void setBreakup(List<RechargeInserBreakupObject> breakup) {
        this.breakup = breakup;
    }

    public String getTxref() {
        return txref;
    }

    public void setTxref(String txref) {
        this.txref = txref;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(Double usableAmount) {
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