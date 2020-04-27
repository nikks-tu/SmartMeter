package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeUpdateResultObject {

    @SerializedName("rechargePaymentId")
    @Expose
    private Integer rechargePaymentId;
    @SerializedName("txRef")
    @Expose
    private String txRef;
    @SerializedName("chargeResponseCode")
    @Expose
    private String chargeResponseCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("orderRef")
    @Expose
    private String orderRef;
    @SerializedName("authModelUsed")
    @Expose
    private String authModelUsed;
    @SerializedName("fraud_status")
    @Expose
    private String fraudStatus;
    @SerializedName("charge_type")
    @Expose
    private String chargeType;
    @SerializedName("chargeResponseMessage")
    @Expose
    private String chargeResponseMessage;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("postpaidBillingId")
    @Expose
    private Integer postpaidBillingId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Integer getRechargePaymentId() {
        return rechargePaymentId;
    }

    public void setRechargePaymentId(Integer rechargePaymentId) {
        this.rechargePaymentId = rechargePaymentId;
    }

    public String getTxRef() {
        return txRef;
    }

    public void setTxRef(String txRef) {
        this.txRef = txRef;
    }

    public String getChargeResponseCode() {
        return chargeResponseCode;
    }

    public void setChargeResponseCode(String chargeResponseCode) {
        this.chargeResponseCode = chargeResponseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getAuthModelUsed() {
        return authModelUsed;
    }

    public void setAuthModelUsed(String authModelUsed) {
        this.authModelUsed = authModelUsed;
    }

    public String getFraudStatus() {
        return fraudStatus;
    }

    public void setFraudStatus(String fraudStatus) {
        this.fraudStatus = fraudStatus;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeResponseMessage() {
        return chargeResponseMessage;
    }

    public void setChargeResponseMessage(String chargeResponseMessage) {
        this.chargeResponseMessage = chargeResponseMessage;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPostpaidBillingId() {
        return postpaidBillingId;
    }

    public void setPostpaidBillingId(Integer postpaidBillingId) {
        this.postpaidBillingId = postpaidBillingId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}