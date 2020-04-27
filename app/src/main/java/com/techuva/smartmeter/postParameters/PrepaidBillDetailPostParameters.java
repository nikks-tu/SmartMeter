package com.techuva.smartmeter.postParameters;

public class PrepaidBillDetailPostParameters {

    private String RECHARGE_PAYMENT_ID;

    public PrepaidBillDetailPostParameters(String RECHARGE_PAYMENT_ID) {
        this.RECHARGE_PAYMENT_ID = RECHARGE_PAYMENT_ID;

    }

    public String getUSN_NO() {
        return RECHARGE_PAYMENT_ID;
    }

    public void setUSN_NO(String USN_No) {
        this.RECHARGE_PAYMENT_ID = USN_No;
    }
}
