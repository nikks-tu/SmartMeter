package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ScrachCardCalPostParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ScratchCardCalculationInterface {

    @POST(Constants.ScratchCardCalculation)
    Call<JsonElement>  getStringScalar(@Header("authUser") int headerValue, @Body ScrachCardCalPostParameters postParameter);

    @POST(Constants.ScratchCardCalculation)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body ScrachCardCalPostParameters postParameter);

}
