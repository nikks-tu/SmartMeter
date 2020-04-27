package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PrepaidInvoiceDownloadPostParamter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PrepaidRechargeInvoiceDownloadInterface {

    @POST(Constants.PrepaidInvoiceDownload)
    Call<JsonElement> getStringScalar(@Body PrepaidInvoiceDownloadPostParamter postParameter);

    @POST(Constants.PrepaidInvoiceDownload)
    Call<JsonElement> getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body PrepaidInvoiceDownloadPostParamter postParameter);
}
