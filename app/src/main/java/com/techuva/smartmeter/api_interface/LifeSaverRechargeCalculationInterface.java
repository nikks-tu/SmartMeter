package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.LifeSaverCalPostParameters;
import com.techuva.smartmeter.postParameters.RechargeCalPostParameters;
import com.techuva.smartmeter.responseModel.DenominationMainObject;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationMainObject;
import com.techuva.smartmeter.responseModel.RechargeCalMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface LifeSaverRechargeCalculationInterface {

    @GET(Constants.GetLifesaverDropDown)
    Call<JsonElement> getLifesaverTypes(@Header("authUser") int headerValue, @Header("authorization") String authorization);

    @POST(Constants.LifeSaverRechargeCalculation)
    Call<LifeSaverCalculationMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body LifeSaverCalPostParameters postParameter);

    @POST(Constants.LifeSaverRechargeCalculation)
    Call<JsonElement>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body LifeSaverCalPostParameters postParameter);

}
