package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostpaidGatewayInfoObject {

    @SerializedName("PBFPubKey")
    @Expose
    private String pBFPubKey;
    @SerializedName("customer_lastname")
    @Expose
    private String customerLastname;
    @SerializedName("txref")
    @Expose
    private String txref;
    @SerializedName("customer_firstname")
    @Expose
    private String customerFirstname;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("custom_description")
    @Expose
    private String customDescription;

    public String getPBFPubKey() {
        return pBFPubKey;
    }

    public void setPBFPubKey(String pBFPubKey) {
        this.pBFPubKey = pBFPubKey;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public String getTxref() {
        return txref;
    }

    public void setTxref(String txref) {
        this.txref = txref;
    }

    public String getCustomerFirstname() {
        return customerFirstname;
    }

    public void setCustomerFirstname(String customerFirstname) {
        this.customerFirstname = customerFirstname;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomDescription() {
        return customDescription;
    }

    public void setCustomDescription(String customDescription) {
        this.customDescription = customDescription;
    }

}