package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.LifeSaverCalPostParameters;
import com.techuva.smartmeter.postParameters.LifeSaverInsertPostParameters;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationMainObject;
import com.techuva.smartmeter.responseModel.LifeSaverInsertMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface LifeSaverRechargeActivationInterface {

    @POST(Constants.LifeSaverRechargeInsert)
    Call<LifeSaverInsertMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body LifeSaverInsertPostParameters postParameter);

    @POST(Constants.LifeSaverRechargeInsert)
    Call<LifeSaverInsertMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body LifeSaverInsertPostParameters postParameter);

}
