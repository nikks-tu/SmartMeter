package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.RechargeCalPostParameters;
import com.techuva.smartmeter.responseModel.PostpaidInsertMainObject;
import com.techuva.smartmeter.responseModel.RechargeCalMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface PostpaidBillInsertInterface {

    @POST(Constants.PostpaidBillInsert)
    Call<PostpaidInsertMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body RechargeCalPostParameters postParameter);

    @POST(Constants.PostpaidBillInsert)
    Call<PostpaidInsertMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body RechargeCalPostParameters postParameter);

}
