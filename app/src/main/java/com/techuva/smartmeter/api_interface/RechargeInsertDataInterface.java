package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.RechargeInsertPostParameter;
import com.techuva.smartmeter.responseModel.RechargeInsertMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RechargeInsertDataInterface {

    @POST(Constants.RechargeInsert)
    Call<RechargeInsertMainObject> getStringScalar(@Header("authUser") int headerValue, @Body RechargeInsertPostParameter postParameter);

    @POST(Constants.RechargeInsert)
    Call<RechargeInsertMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body RechargeInsertPostParameter postParameter);

    @POST(Constants.PostpaidBillInsert)
    Call<RechargeInsertMainObject>  insertPostpaidBillData(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body RechargeInsertPostParameter postParameter);

}
