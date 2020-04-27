package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PostpaidBillAmountPostParameters;
import com.techuva.smartmeter.postParameters.RechargeCalPostParameters;
import com.techuva.smartmeter.responseModel.PostpaidBillDataMainObject;
import com.techuva.smartmeter.responseModel.RechargeCalMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface PostpaidBillDataCalInterface {

    @POST(Constants.GetRechargePostpaidBill)
    Call<PostpaidBillDataMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body PostpaidBillAmountPostParameters postParameter);

    @POST(Constants.GetRechargePostpaidBill)
    Call<PostpaidBillDataMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body PostpaidBillAmountPostParameters postParameter);

}
