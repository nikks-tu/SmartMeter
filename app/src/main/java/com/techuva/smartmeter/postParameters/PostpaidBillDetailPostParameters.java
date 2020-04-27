package com.techuva.smartmeter.postParameters;

public class PostpaidBillDetailPostParameters {


    private int postPaidBillId;

    public PostpaidBillDetailPostParameters(int postPaidBillId) {
        this.postPaidBillId = postPaidBillId;
    }

    public int getPostPaidBillId() {
        return postPaidBillId;
    }

    public void setPostPaidBillId(int postPaidBillId) {
        this.postPaidBillId = postPaidBillId;
    }

}
