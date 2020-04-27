package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonObject;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.RechargeUpdateMainObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RechargeUpdateDataInterface {

    @POST(Constants.PrepaidRechargeUpdate)
    Call<RechargeUpdateMainObject> getStringScalarforPrepaid(@Header("authUser") int headerValue, @Body JSONObject postParameter);

    @POST(Constants.PrepaidRechargeUpdate)
    Call<RechargeUpdateMainObject> getStringScalarforPrepaid(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body JsonObject postParameter);

    @POST(Constants.PostpaidRechargeUpdate)
    Call<RechargeUpdateMainObject>  getStringScalarforPostpaid(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body JsonObject postParameter);

}
