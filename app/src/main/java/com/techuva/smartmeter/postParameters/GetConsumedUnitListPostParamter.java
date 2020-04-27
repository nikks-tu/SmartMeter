package com.techuva.smartmeter.postParameters;

public class GetConsumedUnitListPostParamter {

    private String usnNo;
    private String Type;
    private String Date;
    private String pagePerCount;
    private String pageNumber;

    public GetConsumedUnitListPostParamter(String usnNo, String type, String date, String pagePerCount, String pageNumber) {
        this.usnNo = usnNo;
        Type = type;
        Date = date;
        this.pagePerCount = pagePerCount;
        this.pageNumber = pageNumber;
    }

    public String getUsnNo() {
        return usnNo;
    }

    public void setUsnNo(String usnNo) {
        this.usnNo = usnNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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
