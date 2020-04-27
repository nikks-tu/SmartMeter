package com.techuva.smartmeter.api_interface;

import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.DenominationMainObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDenominationDataInterface {

    @GET(Constants.GetDenomination)
    Call<DenominationMainObject> getDenominations();
}
