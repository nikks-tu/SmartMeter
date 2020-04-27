package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PostpaidBillDetailPostParameters;
import com.techuva.smartmeter.postParameters.PrepaidBillDetailPostParameters;
import com.techuva.smartmeter.responseModel.PostpaidBillDataMainObject;
import com.techuva.smartmeter.responseModel.PostpaidBillDetailsMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface GetPostpaidBillDetailsInterface {

    @POST(Constants.GetPostpaidBillDetails)
    Call<JsonElement>  getStringScalar(@Header("authUser") int headerValue, @Body PostpaidBillDetailPostParameters postParameter);

    @POST(Constants.GetPostpaidBillDetails)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body PostpaidBillDetailPostParameters postParameter);

}
