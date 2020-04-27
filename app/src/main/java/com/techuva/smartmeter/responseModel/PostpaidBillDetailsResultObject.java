package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostpaidBillDetailsResultObject {

    @SerializedName("PostPaidBillData")
    @Expose
    private PostPaidBillDataObject postPaidBillData;
    @SerializedName("PaymentDetails")
    @Expose
    private PostpaidPaymentDetailsObject paymentDetails;
    @SerializedName("UnitsConsumptionHistory")
    @Expose
    private List<Object> unitsConsumptionHistory = null;

    public PostPaidBillDataObject getPostPaidBillData() {
        return postPaidBillData;
    }

    public void setPostPaidBillData(PostPaidBillDataObject postPaidBillData) {
        this.postPaidBillData = postPaidBillData;
    }

    public PostpaidPaymentDetailsObject getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PostpaidPaymentDetailsObject paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<Object> getUnitsConsumptionHistory() {
        return unitsConsumptionHistory;
    }

    public void setUnitsConsumptionHistory(List<Object> unitsConsumptionHistory) {
        this.unitsConsumptionHistory = unitsConsumptionHistory;
    }

}