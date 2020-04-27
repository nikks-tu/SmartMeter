package com.techuva.smartmeter.postParameters;

public class DuePaymentDetailsPostParameter {

    String USN;
    public DuePaymentDetailsPostParameter(String USN) {
        this.USN = USN;
    }

    public String getUSN() {
        return USN;
    }

    public void setUSN(String USN) {
        this.USN = USN;
    }

}
