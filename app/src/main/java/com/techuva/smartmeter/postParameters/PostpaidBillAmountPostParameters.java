package com.techuva.smartmeter.postParameters;

public class PostpaidBillAmountPostParameters {

    private String USN_No;

    public PostpaidBillAmountPostParameters(String USN_No) {
        this.USN_No = USN_No;

    }

    public String getUSN_NO() {
        return USN_No;
    }

    public void setUSN_NO(String USN_No) {
        this.USN_No = USN_No;
    }
}
