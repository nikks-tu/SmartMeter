package com.techuva.smartmeter.api_interface;


import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ForgotPassPostParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ForgotPasswordDataInterface {

    @POST(Constants.ForgetPassword)
    Call<JsonElement> getStringScalar(@Body ForgotPassPostParameters postParameter);

    @POST(Constants.ForgetPassword)
    Call<JsonElement> getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body ForgotPassPostParameters postParameter);

}
