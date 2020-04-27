package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.VersionInfoMainObject;
import com.techuva.smartmeter.responseModel.VersionInfoPostParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface VersionInfoDataInterface {

    @POST(Constants.VersionCheck)
    Call<VersionInfoMainObject>  getStringScalar(@Body VersionInfoPostParameters postParameter);

    @POST(Constants.VersionCheck)
    Call<VersionInfoMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body VersionInfoPostParameters postParameter);

}
