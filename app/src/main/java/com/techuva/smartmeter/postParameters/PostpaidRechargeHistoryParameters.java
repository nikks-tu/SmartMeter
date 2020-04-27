package com.techuva.smartmeter.postParameters;

public class PostpaidRechargeHistoryParameters {

    private String usnNo;
    private String pagePerCount;
    private String pageNumber;

    public PostpaidRechargeHistoryParameters(String usnNo, String pagePerCount, String pageNumber) {
        this.usnNo = usnNo;
        this.pagePerCount = pagePerCount;
        this.pageNumber = pageNumber;
    }


    public String getUsnNo() {
        return usnNo;
    }

    public void setUsnNo(String usnNo) {
        this.usnNo = usnNo;
    }

    public String getPagePerCount() {
        return pagePerCount;
    }

    public void setPagePerCount(String pagePerCount) {
        this.pagePerCount = pagePerCount;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }


}
