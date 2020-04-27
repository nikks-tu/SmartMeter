package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.AccountListPostParameters;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.VersionInfoMainObject;
import com.techuva.smartmeter.responseModel.VersionInfoPostParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface AccountDetailsInterface {

    @POST(Constants.ListofConnections)
    Call<AccountsListMainObject>  getStringScalar(@Header("authUser") int headerValue, @Body AccountListPostParameters postParameter);

    @POST(Constants.ListofConnections)
    Call<AccountsListMainObject>  getStringScalarWithSession(@Header("authUser") int headerValue, @Header("authorization") String authorization, @Body AccountListPostParameters postParameter);

}
