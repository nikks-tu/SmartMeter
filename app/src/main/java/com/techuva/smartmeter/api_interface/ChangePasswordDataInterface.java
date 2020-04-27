package com.techuva.smartmeter.api_interface;


import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ChangePasswordPostParameters;
import com.techuva.smartmeter.postParameters.ForgotPassPostParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ChangePasswordDataInterface {

    @POST(Constants.ChangePassword)
    Call<JsonElement> getStringScalar(@Body ChangePasswordPostParameters postParameter);

    @POST(Constants.ChangePassword)
    Call<JsonElement> getStringScalar(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body ChangePasswordPostParameters postParameter);

}
