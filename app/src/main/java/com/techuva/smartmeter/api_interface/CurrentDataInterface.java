package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.CurrentDataPostParameter;
import com.techuva.smartmeter.responseModel.CurrentDataMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface CurrentDataInterface {

    @POST(Constants.CurrentData)
    Call<CurrentDataMainObject>  getStringScalar(@Body CurrentDataPostParameter postParameter);

    @POST(Constants.CurrentData)
    Call<CurrentDataMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body CurrentDataPostParameter postParameter);

}

