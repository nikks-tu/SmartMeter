package com.techuva.smartmeter.postParameters;

public class AccountListPostParameters {
    String userId;
    String pageNumber;
    String pagePerCount;

    public AccountListPostParameters(String userId, String pageNumber, String pagePerCount) {
        this.userId = userId;
        this.pageNumber = pageNumber;
        this.pagePerCount = pagePerCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPagePerCount() {
        return pagePerCount;
    }

    public void setPagePerCount(String pagePerCount) {
        this.pagePerCount = pagePerCount;
    }

}
