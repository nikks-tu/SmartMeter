package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DenominationResultObject {

    @SerializedName("DENOMINATION_ID")
    @Expose
    private Integer dENOMINATIONID;
    @SerializedName("DENOMINATION")
    @Expose
    private Integer dENOMINATION;

    public Integer getDENOMINATIONID() {
        return dENOMINATIONID;
    }

    public void setDENOMINATIONID(Integer dENOMINATIONID) {
        this.dENOMINATIONID = dENOMINATIONID;
    }

    public Integer getDENOMINATION() {
        return dENOMINATION;
    }

    public void setDENOMINATION(Integer dENOMINATION) {
        this.dENOMINATION = dENOMINATION;
    }

}