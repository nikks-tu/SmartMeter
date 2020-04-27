package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.CurrentDataPostParameter;
import com.techuva.smartmeter.postParameters.DuePaymentDetailsPostParameter;
import com.techuva.smartmeter.responseModel.CurrentDataMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface DuePaymentDetailsInterface {

    @POST(Constants.GetMeterInstallmentDetails)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body DuePaymentDetailsPostParameter postParameter);

    @POST(Constants.GetMeterMigrationDetails)
    Call<JsonElement>  getMeterMigrationDetails(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body JsonObject object);

}

