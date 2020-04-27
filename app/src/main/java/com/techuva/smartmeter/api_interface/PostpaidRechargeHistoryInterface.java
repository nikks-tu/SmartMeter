package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PostpaidRechargeHistoryParameters;
import com.techuva.smartmeter.postParameters.RechargeHistoryPostParameters;
import com.techuva.smartmeter.responseModel.PostpaidHistoryMainObject;
import com.techuva.smartmeter.responseModel.RechargeHistoryMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface PostpaidRechargeHistoryInterface {

    @POST(Constants.PostpaidRechargeHistory)
    Call<JsonElement>  getStringScalar(@Header("authUser") int headerValue, @Body PostpaidRechargeHistoryParameters postParameter);

    @POST(Constants.PostpaidRechargeHistory)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body PostpaidRechargeHistoryParameters postParameter);

}
