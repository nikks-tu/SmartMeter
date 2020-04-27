package com.techuva.smartmeter.postParameters;

public class LifeSaverCalPostParameters {

    private String USN_No;
    private String lifeSaverAmount;

    public LifeSaverCalPostParameters(String USN_No, String lifeSaverAmount) {
        this.USN_No = USN_No;
        this.lifeSaverAmount = lifeSaverAmount;
    }



    public String getUSN_NO() {
        return USN_No;
    }

    public void setUSN_NO(String USN_No) {
        this.USN_No = USN_No;
    }

    public String getLifeSaverAmount() {
        return lifeSaverAmount;
    }

    public void setLifeSaverAmount(String lifeSaverAmount) {
        this.lifeSaverAmount = lifeSaverAmount;
    }



}
