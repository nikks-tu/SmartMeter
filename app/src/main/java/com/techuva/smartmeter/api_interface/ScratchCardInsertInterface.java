package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ScrachCardInsertPostParameters;
import com.techuva.smartmeter.responseModel.ScratchCardInsertMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ScratchCardInsertInterface {

    @POST(Constants.ScratchCardInsert)
    Call<ScratchCardInsertMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body ScrachCardInsertPostParameters postParameter);

    @POST(Constants.ScratchCardInsert)
    Call<ScratchCardInsertMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body ScrachCardInsertPostParameters postParameter);

}
