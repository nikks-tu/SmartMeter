package com.techuva.smartmeter.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.GridAdapterDashboard;
import com.techuva.smartmeter.api_interface.AccountDetailsInterface;
import com.techuva.smartmeter.api_interface.CurrentDataInterface;
import com.techuva.smartmeter.api_interface.GetConsumedUnits;
import com.techuva.smartmeter.api_interface.GetPowerStatusDataInterface;
import com.techuva.smartmeter.api_interface.LifeSaverRechargeCalculationInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.OnOneOffClickListener;
import com.techuva.smartmeter.postParameters.AccountListPostParameters;
import com.techuva.smartmeter.postParameters.CurrentDataPostParameter;
import com.techuva.smartmeter.postParameters.GetConsumedDataPostParamter;
import com.techuva.smartmeter.postParameters.LifeSaverCalPostParameters;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.CurrentDataMainObject;
import com.techuva.smartmeter.responseModel.CurrentDataResultObject;
import com.techuva.smartmeter.responseModel.CurrentDataValueObject;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationMainObject;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationResultObject;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeActivity extends BaseActivity {

    TextView tv_usntxt, tv_usn_num, tv_conn_txt, tv_conn_type, tv_zone_txt, tv_zone, tv_consumed_units_txt, tv_consumed_units;
    TextView tv_currency_txt, tv_bill_amt, tv_tarrif_txt, tv_tariff, txt_serverError, tv_power_status;
    TextView tv_recharge, tv_history, tv_refresh, tv_activate_lifesaver, tv_phase_txt, tv_phase, tv_district_txt, tv_district;
    LinearLayout ll_recharge, ll_history, ll_refresh, ll_main, ll_activate_lifesaver;
    RelativeLayout ll_serverError, internetConnection;
    LinearLayout cv_consumed_units;
    FrameLayout fl_bottom_view;
    GridView grid_bottom;
    Context context;
    MPreferences mPreferences;
    private View decorView;
    Intent intent;
    String inventoryId, usn_num, balance_amt, connection_type, tariff, zone, currency, userId, phase, district;
    String inventoryName="";
    int UserId;
    String authorityKey="";
    GridAdapterDashboard gridAdapterDashboard;
    ArrayList<LifeSaverCalculationResultObject> resultList;
    String date= "";
    private long mLastClickTime = 0;
    /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }
    */
    @Override
    public void initialize() {
        init();
    }

    @Override
    public void goto_home() {
            MApplication.setBoolean(context, Constants.IsHomeSelected, true);
            intent = new Intent(context, UserAccountsActivity.class);
            startActivity(intent);
    }

    @Override
    public void goto_recharge_meter() {
        MApplication.setBoolean(context, Constants.IsUSNSelected, false);
        intent = new Intent(context, RechargeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goto_consumed_units() {
        intent = new Intent(context, ConsumedUnitsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goto_recharge_history() {
        MApplication.setBoolean(context, Constants.IsUSNSelected, true);
        MApplication.setString(context, Constants.SelectedUSN, usn_num);
        MApplication.setString(context, Constants.CONNECTION_TYPE, connection_type);
        intent = new Intent(context, ConsumedUnitsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goto_change_password() {
        intent = new Intent(context, ChangePasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goto_about_app() {

    }

    @Override
    public void goto_terms_conditions() {

    }

    @Override
    public void goto_meter_loan_details() {
        intent = new Intent(context, MeterLoanSummaryActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_migration_schedule_details() {
        intent = new Intent(context, MigrationScheduleDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_logout_method() {
        MApplication.setBoolean(context, Constants.IsLoggedIn, false);
        MApplication.setString(context, Constants.UserID, "");
        MApplication.setString(context, Constants.UserMailId, "");
        MApplication.setString(context, Constants.USN_NUM, "");
        MApplication.setString(context, Constants.SelectedUSN, "");
        MApplication.setString(context, Constants.UserMailId, "");
        intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void init() {
        context = HomeActivity.this;
        mPreferences = new MPreferences(context);
        llContent.addView(inflater.inflate(R.layout.activity_home, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decorView = getWindow().getDecorView();
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));
        tv_usntxt = findViewById(R.id.tv_usntxt);
        tv_usn_num = findViewById(R.id.tv_usn_num);
        tv_conn_txt = findViewById(R.id.tv_conn_txt);
        tv_conn_type = findViewById(R.id.tv_conn_type);
        tv_zone_txt = findViewById(R.id.tv_zone_txt);
        tv_zone = findViewById(R.id.tv_zone);
        tv_currency_txt = findViewById(R.id.tv_currency_txt);
        tv_bill_amt = findViewById(R.id.tv_bill_amt);
        tv_tarrif_txt = findViewById(R.id.tv_tarrif_txt);
        tv_tariff = findViewById(R.id.tv_tariff);
        tv_recharge = findViewById(R.id.tv_recharge);
        tv_history = findViewById(R.id.tv_history);
        tv_refresh = findViewById(R.id.tv_refresh);
        tv_activate_lifesaver = findViewById(R.id.tv_activate_lifesaver);
        tv_consumed_units_txt = findViewById(R.id.tv_consumed_units_txt);
        tv_consumed_units = findViewById(R.id.tv_consumed_units);
        tv_phase_txt = findViewById(R.id.tv_phase_txt);
        tv_phase = findViewById(R.id.tv_phase);
        tv_district_txt = findViewById(R.id.tv_district_txt);
        tv_district = findViewById(R.id.tv_district);
        tv_power_status = findViewById(R.id.tv_power_status);
        txt_serverError = findViewById(R.id.txt_serverError);
        fl_bottom_view = findViewById(R.id.fl_bottom_view);
        ll_history = findViewById(R.id.ll_history);
        ll_refresh = findViewById(R.id.ll_refresh);
        ll_recharge = findViewById(R.id.ll_recharge);
        ll_main = findViewById(R.id.ll_main);
        ll_activate_lifesaver = findViewById(R.id.ll_activate_lifesaver);
        internetConnection = findViewById(R.id.internetConnection);
        ll_serverError = findViewById(R.id.rl_serverError);
        cv_consumed_units = findViewById(R.id.cv_consumed_units);
        grid_bottom = findViewById(R.id.grid_bottom);
        userId = "1001";
        cv_consumed_units.setEnabled(true);
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(calendar.getTime());
        inventoryName = MApplication.getString(context, Constants.InventoryName);
        inventoryId = MApplication.getString(context, Constants.INVENTORY_ID);
        usn_num = MApplication.getString(context, Constants.USN_NUM);
        balance_amt = MApplication.getString(context, Constants.BALANCE);
        connection_type = MApplication.getString(context, Constants.CONNECTION_TYPE);
        tariff = MApplication.getString(context, Constants.TARIFF);
        zone = MApplication.getString(context, Constants.ZONE);
        currency = MApplication.getString(context, Constants.CURRENCY);
        phase = MApplication.getString(context, Constants.Phase_Type);
        district = MApplication.getString(context, Constants.District_Name);
        tv_usn_num.setText(usn_num);
        //tv_currency_txt.setText(currency);
        tv_bill_amt.setText(balance_amt);
        tv_conn_type.setText(connection_type);
        tv_tariff.setText(tariff);
        tv_zone.setText(zone);
        tv_district.setText(district);
        if(phase.equals("0")){
            tv_phase.setText(getResources().getString(R.string.phase_one));
        }
        else if(phase.equals("1")){
            tv_phase.setText(getResources().getString(R.string.phase_three));
        }
        if(connection_type.equals("VIP"))
        {
            ll_recharge.setVisibility(View.GONE);
        }
        else {
            ll_recharge.setVisibility(View.VISIBLE);
        }
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Light.otf");
        tv_usn_num.setTypeface(faceLight);
        tv_usntxt.setTypeface(faceLight);
        tv_usn_num.setTypeface(faceLight);
        tv_conn_txt.setTypeface(faceLight);
        tv_conn_type.setTypeface(faceLight);
        tv_zone_txt.setTypeface(faceLight);
        tv_zone.setTypeface(faceLight);
        tv_currency_txt.setTypeface(faceLight);
        tv_bill_amt.setTypeface(faceLight);
        tv_tarrif_txt.setTypeface(faceLight);
        tv_tariff.setTypeface(faceLight);
        txt_serverError.setTypeface(faceLight);
        tv_recharge.setTypeface(faceLight);
        tv_history.setTypeface(faceLight);
        tv_refresh.setTypeface(faceLight);
        tv_activate_lifesaver.setTypeface(faceLight);
        tv_consumed_units_txt.setTypeface(faceLight);
        tv_consumed_units.setTypeface(faceLight);
        tv_phase_txt.setTypeface(faceLight);
        tv_phase.setTypeface(faceLight);
        tv_power_status.setTypeface(faceLight);
        tv_district_txt.setTypeface(faceLight);
        tv_district.setTypeface(faceLight);
        double balance = Double.parseDouble(MApplication.getString(context, Constants.BALANCE));
        String balanceAmt = MApplication.getString(context, Constants.BALANCE);
        Boolean isNegative = false;

        if(balanceAmt.contains("-") ||  balance==0)
        {
            isNegative = true;
        }
        else {
            isNegative = false;
        }
        if( MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Prepaid"))
        {
            tv_history.setText(context.getResources().getString(R.string.rc_history));
        }
        else {
            tv_history.setText(context.getResources().getString(R.string.bill_history));
        }
        if(!MApplication.getBoolean(context, Constants.LIFE_SAVER) && isNegative && MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Prepaid"))
        {
            ll_activate_lifesaver.setVisibility(View.VISIBLE);
        }
        else {
            ll_activate_lifesaver.setVisibility(View.GONE);
        }

        if(MApplication.isNetConnected(context)){
            serviceCall();
            serviceCallforConsumedData();
            serviceCallForPowerStatus();
        }else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        ll_history.setOnClickListener(v -> {
            if (connection_type.equals("Postpaid"))
            {
                Intent intent = new Intent(context, PostpaidRechargeHistory.class);
                startActivity(intent);
            }
            else if (connection_type.equals("VIP"))
            {
                Intent intent = new Intent(context, PostpaidRechargeHistory.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(context, RechargeHistoryActivity.class);
                startActivity(intent);
            }
        });

        ll_recharge.setOnClickListener(v -> {
            MApplication.setBoolean(context, Constants.FROM_LIFESAVER, false);
            MApplication.setBoolean(context, Constants.IsUSNSelected, true);
            MApplication.setString(context, Constants.SelectedUSN, usn_num);
            Intent intent = new Intent(context, RechargeActivity.class);
            startActivity(intent);
        });
        if(connection_type.equals("Postpaid"))
        {

            MApplication.setBoolean(context, Constants.FROM_LIFESAVER, false);
        }
        ll_activate_lifesaver.setOnClickListener(v -> dataResponseForLifeSaver());
/*

        cv_consumed_units.setOnClickListener(v -> {
            cv_consumed_units.setEnabled(false);
            showLoaderNew();
            new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              Intent intent = new Intent(context, ConsumedUnitsActivity.class);
                                              startActivity(intent);
                                          }
                                      },500);
        });
*/
        cv_consumed_units.setOnClickListener(new OnOneOffClickListener() {
            @Override
            public void onSingleClick(View v) {
                cv_consumed_units.setEnabled(false);
                showLoaderNew();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, ConsumedUnitsActivity.class);
                        startActivity(intent);
                    }
                },1000);
            }
        });
        ll_refresh.setOnClickListener(v -> {
            serviceCall();
            serviceCallForBalance();
            serviceCallforConsumedData();
            serviceCallForPowerStatus();

        });
    }

    private void serviceCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrentDataInterface service = retrofit.create(CurrentDataInterface .class);

        Call<CurrentDataMainObject> call = service.getStringScalarWithSession(UserId, authorityKey, new CurrentDataPostParameter(inventoryId ,userId));
        call.enqueue(new Callback<CurrentDataMainObject>() {
            @Override
            public void onResponse(Call<CurrentDataMainObject> call, Response<CurrentDataMainObject> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if(response.body().getResult()!=null)
                    {
                        try {
                            ll_main.setVisibility(View.VISIBLE);
                            ll_serverError.setVisibility(View.GONE);
                            dataResponse(response.body().getResult());
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        //Toast.makeText(getBaseContext(), "Data not found",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<CurrentDataMainObject> call, Throwable t) {
                hideloader();
                //  Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
                ll_main.setVisibility(View.GONE);
                ll_serverError.setVisibility(View.VISIBLE);
            }

        });

    }


    private void dataResponseForLifeSaver() {
       /* resultList = new ArrayList<>();
        resultList.add(result);*/
        Intent intent = new Intent(HomeActivity.this, RechargeActivity.class);
        //intent.putExtra(Constants.LIFE_SAVER_DETAILS, resultList);
        MApplication.setBoolean(context, Constants.FROM_LIFESAVER, true);
        startActivity(intent);
    }

    private void dataResponse(CurrentDataResultObject currentDataResultObject) throws java.text.ParseException {
        List<CurrentDataValueObject> list = currentDataResultObject.getValues();
        generateDataList(list);
    }

    private void generateDataList(List<CurrentDataValueObject> values)
    {
        ArrayList<CurrentDataValueObject> arrayList = new ArrayList<>();
        if(values.size()>0)
        {

            for (int i =0; i<values.size() ; i++)

            {
                String s2 = values.get(0).getIcon();

                String s1 = s2.replaceAll("&#x", "");

                s1 = s1.replaceAll(";", "");

                arrayList.add(values.get(i));
            }

        }
        if(arrayList.size()>0)
            gridAdapterDashboard = new GridAdapterDashboard(context, R.layout.grid_item_dashboard, arrayList);
        grid_bottom.setAdapter(gridAdapterDashboard);

    }


    @Override
    public void onBackPressed() {
        MApplication.setBoolean(context, Constants.IsHomeSelected, true);
        Intent it = new Intent(HomeActivity.this, UserAccountsActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();
    }

    private void serviceCallforConsumedData() {
        Retrofit retrofit = new Retrofit.Builder()
                  //For Test
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetConsumedUnits service = retrofit.create(GetConsumedUnits.class);
        Call<JsonElement> call = service.getStringScalarWithSession(UserId, authorityKey, new GetConsumedDataPostParamter(date, date, inventoryName));
        call.enqueue(new Callback<JsonElement>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();

                if (response.code() == 401) {
                    MApplication.setBoolean(context, Constants.IsSessionExpired, true);
                    Intent intent = new Intent(context, TokenExpireActivity.class);
                    startActivity(intent);
                } else if (response.body() != null) {

                    JsonObject jsonObject = response.body().getAsJsonObject();
                    if (!jsonObject.isJsonNull()) {
                        JsonObject jObj = jsonObject.getAsJsonObject("info");
                        if (jObj.get("errorCode").getAsInt() == 0) {
                            JsonObject resultObject = jsonObject.getAsJsonObject("result");
                            JsonArray dataArray = resultObject.getAsJsonArray("DaywiseConsumption");
                            JsonObject unitObject = dataArray.get(0).getAsJsonObject();
                            if(!unitObject.get("SUM").getAsString().equals("-"))
                            {
                                tv_consumed_units.setText(unitObject.get("SUM").getAsString());
                            }
                            else {
                                tv_consumed_units.setText("-NA-");
                            }
                            //Toast.makeText(context, ""+unitObject.get("SUM"), Toast.LENGTH_SHORT).show();
                        } else if (jObj.get("ErrorCode").getAsInt() == 1) {

                        } else {

                        }

                    } else {
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
            }
        });

    }

    @Override
    protected void onResume() {
        hideloader();
        cv_consumed_units.setEnabled(true);
        serviceCallForPowerStatus();
        serviceCallForBalance();
        super.onResume();
    }


    private void serviceCallForBalance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccountDetailsInterface service = retrofit.create(AccountDetailsInterface.class);

        // Call<AccountsListMainObject> call = service.getUserAccountDetails(UserId);
        Call<AccountsListMainObject> call = service.getStringScalarWithSession(UserId, authorityKey, new AccountListPostParameters(String.valueOf(UserId), "",""));
        call.enqueue(new Callback<AccountsListMainObject>() {
            @Override
            public void onResponse(Call<AccountsListMainObject> call, Response<AccountsListMainObject> response) {
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();

                    if(response.body().getInfo().getErrorCode()==0)
                    {
                        for (int i=0; i<response.body().getResult().size(); i++) {
                            if(response.body().getResult().get(i).getUSNNO().equals(usn_num)){
                                String balanceAmt = String.format("%.2f", response.body().getResult().get(i).getBALANCE());
                                tv_bill_amt.setText(balanceAmt);
                                double balance = Double.parseDouble(balanceAmt);
                               // String balanceAmt = MApplication.getString(context, Constants.BALANCE);
                                Boolean isNegative = false;
                                if(balanceAmt.contains("-") ||  balance==0)
                                {
                                    isNegative = true;
                                }
                                else {
                                    isNegative = false;
                                }
                                if(!MApplication.getBoolean(context, Constants.LIFE_SAVER) && isNegative && MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Prepaid"))
                                {
                                    ll_activate_lifesaver.setVisibility(View.VISIBLE);
                                }
                                else {
                                    ll_activate_lifesaver.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                    else if(response.body().getInfo().getErrorCode().equals(1))
                    {
                        Toast toast = Toast.makeText(context, response.body().getInfo().getErrorMessage(), Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_back_red));
                        toast.show();
                    }
                }else {

                }
            }
            @Override
            public void onFailure(Call<AccountsListMainObject> call, Throwable t) {
                Toast toast = Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_back_red));
                toast.show();

            }
        });
    }


    private void serviceCallForPowerStatus()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPowerStatusDataInterface service = retrofit.create(GetPowerStatusDataInterface.class);

        Call<JsonElement> call = service.getPowerStatus(UserId, authorityKey, usn_num);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    String errorMsg = infoObject.get("errorMessage").getAsString();
                    if (errorCode==0){
                        JsonObject resultObject = jsonObject.get("result").getAsJsonObject();
                        if (!resultObject.isJsonNull())
                        {

                            if(!resultObject.get("POWER").isJsonNull())
                            {
                                String status = resultObject.get("POWER").getAsString();
                                tv_power_status.setText("ON/Off Status : "+status);
                            }

                            else {
                                tv_power_status.setText("ON/Off Status : "+"-");
                            }
                        }
                    }


                }else {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
                //  Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
                ll_main.setVisibility(View.GONE);
                ll_serverError.setVisibility(View.VISIBLE);
            }

        });

    }

}
