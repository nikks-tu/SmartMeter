package com.techuva.smartmeter.api_interface;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.AccountListPostParameters;
import com.techuva.smartmeter.postParameters.InstallmentRechargePostParameters;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.RechargeInsertMainObject;
import com.techuva.smartmeter.responseModel.RechargeUpdateMainObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface InstallmentsRechargeInterface {

    @POST(Constants.MigrationScheduleCalculate)
    Call<JsonElement>  getMeterMigrationCalculation(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body InstallmentRechargePostParameters postParameter);

    @POST(Constants.MigrationScheduleInsert)
    Call<RechargeInsertMainObject>  meterMigrationInsert(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body InstallmentRechargePostParameters postParameter);

    @POST(Constants.MeterLoanInsert)
    Call<RechargeInsertMainObject>  meterLoanInsert(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body JsonObject postParameter);

    @POST(Constants.MigrationScheduleUpdateRecharge)
    Call<RechargeUpdateMainObject>  meterMigrationUpdate(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body JsonObject postParameter);

    @POST(Constants.MeterLoanUpdateRecharge)
    Call<RechargeUpdateMainObject>  meterLoanUpdate(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body JsonObject postParameter);

    @POST(Constants.MeterLoanCalculate)
    Call<JsonElement>  getMeterLoanCalculation(@Header("authUser") int headerValue, @Header("Authorization") String AccessToken, @Body JsonObject postParameter);

}
