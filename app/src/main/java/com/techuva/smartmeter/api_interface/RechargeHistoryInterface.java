package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.RechargeHistoryPostParameters;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.RechargeHistoryMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RechargeHistoryInterface {

    @POST(Constants.GetRechargeHistoryDetails)
    Call<RechargeHistoryMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body RechargeHistoryPostParameters postParameter);

    @POST(Constants.GetRechargeHistoryDetails)
    Call<RechargeHistoryMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body RechargeHistoryPostParameters postParameter);

}
