package com.techuva.smartmeter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostpaidHistoryMainObject{

    @SerializedName("result")
    @Expose
    private List<PostpaidHistoryResultObject> result = null;
    @SerializedName("info")
    @Expose
    private PostpaidHistoryInfoObject info;

    public List<PostpaidHistoryResultObject> getResult() {
        return result;
    }

    public void setResult(List<PostpaidHistoryResultObject> result) {
        this.result = result;
    }

    public PostpaidHistoryInfoObject getInfo() {
        return info;
    }

    public void setInfo(PostpaidHistoryInfoObject info) {
        this.info = info;
    }

}