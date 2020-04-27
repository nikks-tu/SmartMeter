package com.techuva.smartmeter.postParameters;

public class RechargeHistoryPostParameters {

    private String pagePerCount;
    private String pageNumber;
    private String fromDate;
    private String toDate;
    private String usn;

    public RechargeHistoryPostParameters(String usn, String pagePerCount, String pageNumber, String fromDate, String toDate) {
        this.usn = usn;
        this.pagePerCount = pagePerCount;
        this.pageNumber = pageNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


}
