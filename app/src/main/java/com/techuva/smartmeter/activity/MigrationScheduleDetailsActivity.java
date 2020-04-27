package com.techuva.smartmeter.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.MeterMigrationInstallmentRcvAdapter;
import com.techuva.smartmeter.api_interface.DuePaymentDetailsInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.MeterMigrationEMIDetailObject;
import com.techuva.smartmeter.responseModel.MeterMigrationResultObject;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MigrationScheduleDetailsActivity extends BaseActivity  {

    Context context;
    MPreferences mPreferences;
    private View decorView;
    String authorityKey="";
    String usnNum="";
    String oldPassword ="";
    String newPassword="";
    String confirmPassword="";
    String EmailId="";
    private int UserId;
    TextView tv_old_meter_details, tv_new_connection_txt, tv_new_usn_value, tv_old_connection, tv_old_connection_value, tv_no_data;
    TextView tv_migrated_on_txt, tv_migrated_on, tv_old_meter_num_txt, tv_old_meter_num, tv_connection_type_txt, tv_connection_type;
    TextView tv_consumer_type_txt, tv_consumer_type, tv_old_meter_balance, tv_old_meter, tv_balance_txt, tv_balance_payable;
    CardView cv_meter_install_details, cv_emi_list;
    RecyclerView rcv_emi_list;
    LinearLayout ll_details, ll_main;
    ArrayList<MeterMigrationEMIDetailObject> emiList;
    MeterMigrationInstallmentRcvAdapter emiAdapter;
    @Override
    public void initialize() {
        init();
    }
    Intent intent;

    private void init() {
        context = MigrationScheduleDetailsActivity.this;
        mPreferences = new MPreferences(context);
        llContent.addView(inflater.inflate(R.layout.activity_meter_migration, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decorView = getWindow().getDecorView();
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        usnNum = MApplication.getString(context, Constants.USN_NUM);
        initialize_views();
        showLoaderNew();
        makeInputObject();

     /*   rcv_emi_list.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv_emi_list, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PayInstallmentsActivity.class);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));*/
    }

    private void makeInputObject() {
        JsonObject object = new JsonObject();
        object.addProperty("USN_NO", usnNum);
        serviceCall(object);
    }

    private void setTypeface() {
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        tv_old_meter_details.setTypeface(faceLight);
        tv_new_connection_txt.setTypeface(faceLight);
        tv_new_usn_value.setTypeface(faceLight);
        tv_old_connection.setTypeface(faceLight);
        tv_old_connection_value.setTypeface(faceLight);
        tv_no_data.setTypeface(faceLight);
        tv_migrated_on_txt.setTypeface(faceLight);
        tv_migrated_on.setTypeface(faceLight);
        tv_old_meter_num_txt.setTypeface(faceLight);
        tv_old_meter_num.setTypeface(faceLight);
        tv_connection_type_txt.setTypeface(faceLight);
        tv_connection_type.setTypeface(faceLight);
        tv_consumer_type_txt.setTypeface(faceLight);
        tv_consumer_type.setTypeface(faceLight);
        tv_old_meter_balance.setTypeface(faceLight);
        tv_old_meter.setTypeface(faceLight);
        tv_balance_txt.setTypeface(faceLight);
        tv_balance_payable.setTypeface(faceLight);
    }

    private void initialize_views() {
        UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));
        tv_old_meter_details = findViewById(R.id.tv_old_meter_details);
        tv_new_connection_txt = findViewById(R.id.tv_new_connection_txt);
        tv_new_usn_value = findViewById(R.id.tv_new_usn_value);
        tv_old_connection = findViewById(R.id.tv_old_connection);
        tv_old_connection_value = findViewById(R.id.tv_old_connection_value);
        tv_migrated_on_txt = findViewById(R.id.tv_migrated_on_txt);
        /*, , , , ;
    TextView , , , , , ;
    TextView , , , , , ;*/
        tv_migrated_on = findViewById(R.id.tv_migrated_on);
        tv_old_meter_num_txt = findViewById(R.id.tv_old_meter_num_txt);
        tv_old_meter_num = findViewById(R.id.tv_old_meter_num);
        tv_connection_type_txt = findViewById(R.id.tv_connection_type_txt);
        tv_connection_type = findViewById(R.id.tv_connection_type);
        tv_consumer_type_txt = findViewById(R.id.tv_consumer_type_txt);
        tv_consumer_type = findViewById(R.id.tv_consumer_type);
        tv_old_meter_balance = findViewById(R.id.tv_old_meter_balance);
        tv_old_meter = findViewById(R.id.tv_old_meter);
        tv_balance_txt = findViewById(R.id.tv_balance_txt);
        tv_balance_payable = findViewById(R.id.tv_balance_payable);
        tv_no_data = findViewById(R.id.tv_no_data);
        cv_meter_install_details = findViewById(R.id.cv_meter_install_details);
        cv_emi_list = findViewById(R.id.cv_emi_list);
        ll_details = findViewById(R.id.ll_details);
        ll_main = findViewById(R.id.ll_main);
        rcv_emi_list = findViewById(R.id.rcv_emi_list);
        emiList = new ArrayList<>();
        setTypeface();
        // ll_button_update = findViewById(R.id.ll_button_update);
    }


    @Override
    public void goto_home() {
        MApplication.setBoolean(context, Constants.IsHomeSelected, true);
        intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_recharge_meter() {
        intent = new Intent(context, RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_consumed_units() {
        /*intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void goto_recharge_history() {

    }

    @Override
    public void goto_change_password() {

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
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void serviceCall(JsonObject  object) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DuePaymentDetailsInterface service = retrofit.create(DuePaymentDetailsInterface.class);
        Call<JsonElement> call = service.getMeterMigrationDetails(UserId, authorityKey, object);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
               hideloader();
                if(response.body()!=null){
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    if(errorCode==0)
                    {
                        ll_main.setVisibility(View.VISIBLE);
                        tv_no_data.setVisibility(View.GONE);
                        hideloader();
                        JsonObject resultObject = jsonObject.get("result").getAsJsonObject();
                        MeterMigrationResultObject object = new MeterMigrationResultObject();
                        //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();


                        if(!resultObject.get("CONNECTION_TYPE_ID").isJsonNull())
                        {
                            object.setCONNECTIONTYPEID(resultObject.get("CONNECTION_TYPE_ID").getAsInt());
                        }
                        if(!resultObject.get("CONNECTION_SHORT_CODE").isJsonNull())
                        {
                            object.setCONNECTIONSHORTCODE(resultObject.get("CONNECTION_SHORT_CODE").getAsString());
                        }
                        if(!resultObject.get("NEW_USN_NO").isJsonNull())
                        {
                            object.setNEWUSNNO(resultObject.get("NEW_USN_NO").getAsString());
                            tv_new_usn_value.setText(String.valueOf(object.getNEWUSNNO()));
                        }
                        if(!resultObject.get("PAID_STATUS").isJsonNull())
                        {
                            object.setPAIDSTATUS(resultObject.get("PAID_STATUS").getAsInt());
                        }
                        if(!resultObject.get("CONSUMER_ICON").isJsonNull())
                        {
                            object.setCONSUMERICON(resultObject.get("CONSUMER_ICON").getAsInt());
                        }
                        if(!resultObject.get("PREV_BALANCE_UNITS").isJsonNull())
                        {
                            object.setPREVBALANCEUNITS(resultObject.get("PREV_BALANCE_UNITS").getAsInt());
                        }
                        if(!resultObject.get("CONSUMER_TYPE_ID").isJsonNull())
                        {
                            object.setCONSUMERTYPEID(resultObject.get("CONSUMER_TYPE_ID").getAsInt());
                        }
                        if(!resultObject.get("OLD_DEVICE_ID").isJsonNull())
                        {
                            object.setOLDDEVICEID(resultObject.get("OLD_DEVICE_ID").getAsString());
                            tv_old_meter_num.setText(object.getOLDDEVICEID());
                        }
                        if(!resultObject.get("MIGRATED_DATE").isJsonNull())
                        {
                            object.setMIGRATEDDATE(resultObject.get("MIGRATED_DATE").getAsString());
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
                            Date date = null;
                            String dateToStr="";
                            try {
                                date = sdf.parse(object.getMIGRATEDDATE());
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                dateToStr= format.format(date);
                            } catch (android.net.ParseException e) {
                                e.printStackTrace();
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                            tv_migrated_on.setText(dateToStr);
                        }
                        if(!resultObject.get("CONNECTION_ICON").isJsonNull())
                        {
                            object.setCONNECTIONICON(resultObject.get("CONNECTION_ICON").getAsInt());
                        }
                        if(!resultObject.get("DESCRIPTION").isJsonNull())
                        {
                            object.setDESCRIPTION(resultObject.get("DESCRIPTION").getAsString());
                        }
                        if(!resultObject.get("TYPE_DESCRIPTION").isJsonNull())
                        {
                            object.setTYPEDESCRIPTION(resultObject.get("TYPE_DESCRIPTION").getAsString());
                            tv_consumer_type.setText(object.getTYPEDESCRIPTION());
                        }
                        if(!resultObject.get("DEVICE_MIGRATION_ID").isJsonNull())
                        {
                            object.setDEVICEMIGRATIONID(resultObject.get("DEVICE_MIGRATION_ID").getAsInt());
                        }
                        if(!resultObject.get("MODULE").isJsonNull())
                        {
                            object.setMODULE(resultObject.get("MODULE").getAsString());
                        }
                        if(!resultObject.get("MIGRATED_BY").isJsonNull())
                        {
                            object.setMIGRATEDBY(resultObject.get("MIGRATED_BY").getAsInt());
                        }
                        if(!resultObject.get("SHORT_TEXT").isJsonNull())
                        {
                            object.setSHORTTEXT(resultObject.get("SHORT_TEXT").getAsString());
                        }
                        if(!resultObject.get("OLD_USN_NO").isJsonNull())
                        {
                            object.setOLDUSNNO(resultObject.get("OLD_USN_NO").getAsString());
                            tv_old_connection_value.setText(object.getOLDUSNNO());
                        }
                        if(!resultObject.get("DESCRIPTION").isJsonNull())
                        {
                            object.setDESCRIPTION(resultObject.get("DESCRIPTION").getAsString());
                        }
                        if(!resultObject.get("PREV_BALANCE_AMT").isJsonNull())
                        {
                            object.setPREVBALANCEAMT(resultObject.get("PREV_BALANCE_AMT").getAsDouble());
                            tv_old_meter_balance.setText(String.valueOf(object.getPREVBALANCEAMT()));
                        }
                        if(!resultObject.get("CURRENT_BALANCE").isJsonNull())
                        {
                            object.setCURRENTBALANCE(resultObject.get("CURRENT_BALANCE").getAsDouble());
                            tv_balance_payable.setText(String.valueOf(object.getCURRENTBALANCE()));
                        }
                        if(!resultObject.get("CONNECTION_TYPE_DESC").isJsonNull())
                        {
                            object.setCONNECTIONTYPEDESC(resultObject.get("CONNECTION_TYPE_DESC").getAsString());
                            tv_connection_type.setText(object.getCONNECTIONTYPEDESC());
                        }
                        if(!resultObject.get("TYPE_SHORT_CODE").isJsonNull())
                        {
                            object.setTYPESHORTCODE(resultObject.get("TYPE_SHORT_CODE").getAsString());
                        }
                        if(!resultObject.get("MGR_PAYMENT_SCHEDULE").isJsonNull())
                        {
                            JsonArray jsonArray = resultObject.get("MGR_PAYMENT_SCHEDULE").getAsJsonArray();
                            if(jsonArray.size()>0)
                            {
                                for(int i=0; i<jsonArray.size(); i++)
                                {
                                    JsonObject obj = jsonArray.get(i).getAsJsonObject();
                                    MeterMigrationEMIDetailObject emiObject = new MeterMigrationEMIDetailObject();
                                   if(obj.has("PaidOn") && !obj.get("PaidOn").isJsonNull())
                                   {
                                       emiObject.setPaidOn(obj.get("PaidOn").getAsString());
                                   }
                                   if(obj.has("RECHARGE_AMOUNT") && !obj.get("RECHARGE_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setRECHARGEAMOUNT(obj.get("RECHARGE_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("SCHEDULE_DESCRIPTION").isJsonNull())
                                   {
                                       emiObject.setSCHEDULEDESCRIPTION(obj.get("SCHEDULE_DESCRIPTION").getAsString());
                                   }
                                   if(obj.has("PaymentReference") && !obj.get("PaymentReference").isJsonNull())
                                   {
                                       emiObject.setPaymentReference(obj.get("PaymentReference").getAsString());
                                   }
                                   if(!obj.get("SHORT_TEXT").isJsonNull())
                                   {
                                       emiObject.setSHORTTEXT(obj.get("SHORT_TEXT").getAsString());
                                   }
                                   if(!obj.get("PAID_STATUS").isJsonNull())
                                   {
                                       emiObject.setPAIDSTATUS(obj.get("PAID_STATUS").getAsString());
                                   }
                                   if(!obj.get("MGR_PAYMENT_ID").isJsonNull())
                                   {
                                       emiObject.setMGRPAYMENTID(obj.get("MGR_PAYMENT_ID").getAsInt());
                                   }
                                   if(obj.has("USABLE_AMOUNT") && !obj.get("USABLE_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setUSABLEAMOUNT(obj.get("USABLE_AMOUNT").getAsDouble());
                                   }
                                   if(obj.has("RECHARGE_DATE") && !obj.get("RECHARGE_DATE").isJsonNull())
                                   {
                                       emiObject.setRECHARGEDATE(obj.get("RECHARGE_DATE").getAsString());
                                   }
                                   if(obj.has("RECHARGE_PAYMENT_ID") && !obj.get("RECHARGE_PAYMENT_ID").isJsonNull())
                                   {
                                       emiObject.setRECHARGEPAYMENTID(obj.get("RECHARGE_PAYMENT_ID").getAsInt());
                                   }
                                   if(!obj.get("SCHEDULE_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setSCHEDULEAMOUNT(obj.get("SCHEDULE_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("SCHEDULE_DATE").isJsonNull())
                                   {
                                       emiObject.setSCHEDULEDATE(obj.get("SCHEDULE_DATE").getAsString());
                                   }
                                   emiList.add(emiObject);
                                   if(emiList.size()>0)
                                   {
                                       cv_emi_list.setVisibility(View.VISIBLE);
                                       tv_no_data.setVisibility(View.GONE);
                                       emiAdapter = new MeterMigrationInstallmentRcvAdapter(MigrationScheduleDetailsActivity.this, context, emiList);
                                       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                                       rcv_emi_list.setLayoutManager(linearLayoutManager);
                                       rcv_emi_list.setAdapter(emiAdapter);
                                   }

                                }
                            }
                        }



                    }
                    else if(errorCode==1)
                    {
                        hideloader();
                        ll_main.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                       /* Toast toast = Toast.makeText(context, infoObject.get("errorMessage").getAsString(), Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                        toast.show();*/
                    }
                }
                else {
                    hideloader();
                    Toast toast = Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG);
                    View view = toast.getView();
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
                Toast toast = Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                toast.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        /*MApplication.setBoolean(context, Constants.IsHomeSelected, true);
        intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);*/
        super.onBackPressed();
    }
}
