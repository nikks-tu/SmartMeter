package com.techuva.smartmeter.postParameters;

public class PrepaidInvoiceDownloadPostParamter {

    private String txnRef;
    public PrepaidInvoiceDownloadPostParamter(String txnRef) {
        this.txnRef = txnRef;
    }

    public String getTxnRef() {
        return txnRef;
    }

    public void setTxnRef(String txnRef) {
        this.txnRef = txnRef;
    }
}
