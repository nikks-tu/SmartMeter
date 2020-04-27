package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.DenominationMainObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GetPowerStatusDataInterface {

    @GET("getPowerStatus/{UserID}")
    Call<JsonElement> getPowerStatus(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Path("UserID") String UserID);

}
