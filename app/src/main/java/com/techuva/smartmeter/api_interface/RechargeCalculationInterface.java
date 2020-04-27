package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.RechargeCalPostParameters;
import com.techuva.smartmeter.responseModel.RechargeCalMainObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RechargeCalculationInterface {

    @POST(Constants.RechargeCalculation)
    Call<RechargeCalMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body RechargeCalPostParameters postParameter);

    @POST(Constants.RechargeCalculation)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body RechargeCalPostParameters postParameter);

}
