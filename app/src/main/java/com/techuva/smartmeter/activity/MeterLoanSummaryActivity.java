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
import com.techuva.smartmeter.adapter.MeterLoanInstallmentRcvAdapter;
import com.techuva.smartmeter.api_interface.DuePaymentDetailsInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.DuePaymentDetailsPostParameter;
import com.techuva.smartmeter.responseModel.MeterInstallmentEMIDetailObject;
import com.techuva.smartmeter.responseModel.MeterInstallmentResultObject;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MeterLoanSummaryActivity extends BaseActivity  {

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
    TextView tv_loan_ref_txt, tv_loan_ref_value, tv_meter_model_txt, tv_meter_model_value, tv_loan_amount, tv_loan_amount_value;
    TextView tv_down_payment, tv_down_payment_value, tv_bank_financier, tv_bank_financier_value, tv_loan_tenure, tv_loan_tenure_value;
    TextView tv_interest_rate, tv_interest_rate_value, tv_current_balance, tv_current_balance_value;
    TextView tv_loan_status, tv_loan_status_value, tv_no_data;
    CardView cv_meter_install_details, cv_emi_list;
    RecyclerView rcv_emi_list;
    LinearLayout ll_details, ll_main;

    ArrayList<MeterInstallmentEMIDetailObject> emiList;
    MeterLoanInstallmentRcvAdapter emiAdapter;
    @Override
    public void initialize() {
     init();
    }
    Intent intent;

    private void init() {
        context = MeterLoanSummaryActivity.this;
        mPreferences = new MPreferences(context);
        llContent.addView(inflater.inflate(R.layout.activity_meter_loan, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decorView = getWindow().getDecorView();
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        usnNum = MApplication.getString(context, Constants.USN_NUM);
        initialize_views();
        showLoaderNew();
        serviceCall();

    }

    private void setTypeface() {
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
         tv_loan_ref_txt.setTypeface(faceLight);
         tv_loan_ref_value.setTypeface(faceLight);
         tv_meter_model_txt.setTypeface(faceLight);
         tv_meter_model_value.setTypeface(faceLight);
         tv_loan_amount.setTypeface(faceLight);
         tv_loan_amount_value.setTypeface(faceLight);
         tv_down_payment.setTypeface(faceLight);
         tv_down_payment_value.setTypeface(faceLight);
         tv_bank_financier.setTypeface(faceLight);
         tv_bank_financier_value.setTypeface(faceLight);
         tv_loan_tenure.setTypeface(faceLight);
         tv_loan_tenure_value.setTypeface(faceLight);
         tv_interest_rate.setTypeface(faceLight);
         tv_interest_rate_value.setTypeface(faceLight);
         tv_current_balance.setTypeface(faceLight);
         tv_current_balance_value.setTypeface(faceLight);
         tv_loan_status.setTypeface(faceLight);
         tv_loan_status_value.setTypeface(faceLight);
    }

    private void initialize_views() {
        UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));
        tv_loan_status_value = findViewById(R.id.tv_loan_status_value);
        tv_loan_status = findViewById(R.id.tv_loan_status);
        tv_current_balance_value = findViewById(R.id.tv_current_balance_value);
        tv_current_balance = findViewById(R.id.tv_current_balance);
        tv_interest_rate_value = findViewById(R.id.tv_interest_rate_value);
        tv_interest_rate = findViewById(R.id.tv_interest_rate);
        tv_down_payment = findViewById(R.id.tv_down_payment);
        tv_down_payment_value = findViewById(R.id.tv_down_payment_value);
        tv_bank_financier = findViewById(R.id.tv_bank_financier);
        tv_bank_financier_value = findViewById(R.id.tv_bank_financier_value);
        tv_loan_tenure = findViewById(R.id.tv_loan_tenure);
        tv_loan_tenure_value = findViewById(R.id.tv_loan_tenure_value);
        tv_loan_ref_txt = findViewById(R.id.tv_loan_ref_txt);
        tv_loan_ref_value = findViewById(R.id.tv_loan_ref_value);
        tv_meter_model_txt = findViewById(R.id.tv_meter_model_txt);
        tv_meter_model_value = findViewById(R.id.tv_meter_model_value);
        tv_loan_amount = findViewById(R.id.tv_loan_amount);
        tv_loan_amount_value = findViewById(R.id.tv_loan_amount_value);
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

    private void serviceCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DuePaymentDetailsInterface service = retrofit.create(DuePaymentDetailsInterface.class);
        Call<JsonElement> call = service.getStringScalarWithSession(UserId, authorityKey, new DuePaymentDetailsPostParameter(usnNum));
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
                        JsonObject resultObject = jsonObject.get("result").getAsJsonObject();
                        MeterInstallmentResultObject object = new MeterInstallmentResultObject();
                        //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        if(!resultObject.get("METER_LOAN_ID").isJsonNull())
                        {
                            object.setMETERLOANID(resultObject.get("METER_LOAN_ID").getAsInt());
                            String meterLoanId = String.valueOf(object.getMETERLOANID());
                            for (int a=0; a<meterLoanId.length(); a++)
                            {
                                if(meterLoanId.length()<6)
                                {
                                   meterLoanId = "0"+meterLoanId;
                                }
                            }
                            tv_loan_ref_value.setText("#"+meterLoanId);
                        }
                        if(!resultObject.get("METER_CONNECTION_ID").isJsonNull())
                        {
                            object.setMETERCONNECTIONID(resultObject.get("METER_CONNECTION_ID").getAsInt());
                        }
                        if(!resultObject.get("TOTAL_LOAN").isJsonNull())
                        {
                            object.setTOTALLOAN(resultObject.get("TOTAL_LOAN").getAsDouble());
                            tv_loan_amount_value.setText(String.valueOf(object.getTOTALLOAN()));
                        }
                        if(!resultObject.get("INTEREST_RATE").isJsonNull())
                        {
                            object.setINTERESTRATE(resultObject.get("INTEREST_RATE").getAsDouble());
                            tv_interest_rate_value.setText(object.getINTERESTRATE()+" %");
                        }
                        if(!resultObject.get("TOTAL_TENURE").isJsonNull())
                        {
                            object.setTOTALTENURE(resultObject.get("TOTAL_TENURE").getAsInt());
                            tv_loan_tenure_value.setText(object.getTOTALTENURE()+" Months");
                        }
                        if(!resultObject.get("FINANCIER_ID").isJsonNull())
                        {
                            object.setFINANCIERID(resultObject.get("FINANCIER_ID").getAsInt());
                        }
                        if(!resultObject.get("INTEREST_AMOUNT").isJsonNull())
                        {
                            object.setINTERESTAMOUNT(resultObject.get("INTEREST_AMOUNT").getAsDouble());
                        }
                        if(!resultObject.get("CURRENT_BALANCE").isJsonNull())
                        {
                            object.setCURRENTBALANCE(resultObject.get("CURRENT_BALANCE").getAsDouble());
                            tv_current_balance_value.setText(String.valueOf(object.getCURRENTBALANCE()));
                        }
                        if(!resultObject.get("DOWN_PAYMENT").isJsonNull())
                        {
                            object.setDOWNPAYMENT(resultObject.get("DOWN_PAYMENT").getAsDouble());
                            tv_down_payment_value.setText(String.valueOf(object.getDOWNPAYMENT()));
                        }
                        if(!resultObject.get("STATUS_ID").isJsonNull())
                        {
                            object.setSTATUSID(resultObject.get("STATUS_ID").getAsInt());
                        }
                        if(!resultObject.get("DESCRIPTION").isJsonNull())
                        {
                            object.setDESCRIPTION(resultObject.get("DESCRIPTION").getAsString());
                        }
                        if(!resultObject.get("SHORT_TEXT").isJsonNull())
                        {
                            object.setSHORTTEXT(resultObject.get("SHORT_TEXT").getAsString());
                            tv_loan_status_value.setText(object.getSHORTTEXT());
                        }
                        if(!resultObject.get("FINANCIER_SHORT_CODE").isJsonNull())
                        {
                            object.setFINANCIERSHORTCODE(resultObject.get("FINANCIER_SHORT_CODE").getAsString());
                        }
                        if(!resultObject.get("FINANCIER_NAME").isJsonNull())
                        {
                            object.setFINANCIERNAME(resultObject.get("FINANCIER_NAME").getAsString());
                            tv_bank_financier_value.setText(object.getFINANCIERNAME());
                        }
                        if(!resultObject.get("METER_COST").isJsonNull())
                        {
                            object.setMETERCOST(resultObject.get("METER_COST").getAsDouble());
                        }
                        if(!resultObject.get("INVENTORY_MODEL").isJsonNull())
                        {
                            object.setINVENTORYMODEL(resultObject.get("INVENTORY_MODEL").getAsString());
                            tv_meter_model_value.setText(object.getINVENTORYMODEL());
                        }
                        if(!resultObject.get("EMI_DETAILS").isJsonNull())
                        {
                            JsonArray jsonArray = resultObject.get("EMI_DETAILS").getAsJsonArray();
                            if(jsonArray.size()>0)
                            {
                                for(int i=0; i<jsonArray.size(); i++)
                                {
                                    JsonObject obj = jsonArray.get(i).getAsJsonObject();
                                    MeterInstallmentEMIDetailObject emiObject = new MeterInstallmentEMIDetailObject();
                                   if(!obj.get("LOAN_EMI_ID").isJsonNull())
                                   {
                                       emiObject.setlOANEMIID(obj.get("LOAN_EMI_ID").getAsInt());
                                   }
                                   if(!obj.get("METER_LOAN_ID").isJsonNull())
                                   {
                                       emiObject.setmETERLOANID(obj.get("METER_LOAN_ID").getAsInt());
                                   }
                                   if(!obj.get("INSTALLMENT_NAME").isJsonNull())
                                   {
                                       emiObject.setiNSTALLMENTNAME(obj.get("INSTALLMENT_NAME").getAsString());
                                   }
                                   if(!obj.get("EMI_AMOUNT").isJsonNull())
                                   {
                                       emiObject.seteMIAMOUNT(obj.get("EMI_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("PRINCIPAL_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setpRINCIPALAMOUNT(obj.get("PRINCIPAL_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("INTEREST_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setiNTERESTAMOUNT(obj.get("INTEREST_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("EMI_DUE_DATE").isJsonNull())
                                   {
                                       emiObject.seteMIDUEDATE(obj.get("EMI_DUE_DATE").getAsString());
                                   }
                                   if(!obj.get("STATUS_ID").isJsonNull())
                                   {
                                       emiObject.setsTATUSID(obj.get("STATUS_ID").getAsInt());
                                   }
                                   if(!obj.get("PAID_AMOUNT").isJsonNull())
                                   {
                                       emiObject.setpAIDAMOUNT(obj.get("PAID_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("TAX_AMOUNT").isJsonNull())
                                   {
                                       emiObject.settAXAMOUNT(obj.get("TAX_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("PAID_ON").isJsonNull())
                                   {
                                       emiObject.setpAIDON(obj.get("PAID_ON").getAsString());
                                   }
                                   if(!obj.get("PAID_BY").isJsonNull())
                                   {
                                       emiObject.setpAIDBY(obj.get("PAID_BY").getAsString());
                                   }
                                   if(!obj.get("PAYMENT_MODE_ID").isJsonNull())
                                   {
                                       emiObject.setpAYMENTMODEID(obj.get("PAYMENT_MODE_ID").getAsInt());
                                   }
                                   if(!obj.get("IS_ACTIVE").isJsonNull())
                                   {
                                       emiObject.setiSACTIVE(obj.get("IS_ACTIVE").getAsBoolean());
                                   }
                                   if(!obj.get("PAYMENT_ID").isJsonNull())
                                   {
                                       emiObject.setpAYMENTID(obj.get("PAYMENT_ID").getAsInt());
                                   }
                                   if(!obj.get("CREATED_ON").isJsonNull())
                                   {
                                       emiObject.setcREATEDON(obj.get("CREATED_ON").getAsString());
                                   }
                                   if(!obj.get("LAST_MODIFIED_ON").isJsonNull())
                                   {
                                       emiObject.setlASTMODIFIEDON(obj.get("LAST_MODIFIED_ON").getAsString());
                                   }
                                   if(!obj.get("DESCRIPTION").isJsonNull())
                                   {
                                       emiObject.setdESCRIPTION(obj.get("DESCRIPTION").getAsString());
                                   }
                                   if(!obj.get("SHORT_TEXT").isJsonNull())
                                   {
                                       emiObject.setsHORTTEXT(obj.get("SHORT_TEXT").getAsString());
                                   }
                                   if(!obj.get("PAID_BY_NAME").isJsonNull())
                                   {
                                       emiObject.setpAIDBY(obj.get("PAID_BY_NAME").getAsString());
                                   }
                                   if(!obj.get("PAYMENT_DESCRIPTION").isJsonNull())
                                   {
                                       emiObject.setpAYMENTDESCRIPTION(obj.get("PAYMENT_DESCRIPTION").getAsString());
                                   }
                                   if(!obj.get("USN_NO").isJsonNull())
                                   {
                                       emiObject.setuSNNO(obj.get("USN_NO").getAsString());
                                   }
                                   if(!obj.get("RECHARGE_AMOUNT").isJsonNull())
                                   {
                                        emiObject.setrECHARGEAMOUNT(obj.get("RECHARGE_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("USABLE_AMOUNT").isJsonNull())
                                   {
                                        emiObject.setuSABLEAMOUNT(obj.get("USABLE_AMOUNT").getAsDouble());
                                   }
                                   if(!obj.get("RECHARGE_DATE").isJsonNull())
                                   {
                                        emiObject.setrECHARGEDATE(obj.get("RECHARGE_DATE").getAsString());
                                   }
                                   if(!obj.get("TRANS_REF").isJsonNull())
                                   {
                                        emiObject.settRANSREF(obj.get("TRANS_REF").getAsString());
                                   }
                                   if(!obj.get("TRANS_STATUS").isJsonNull())
                                   {
                                        emiObject.settRANSREF(obj.get("TRANS_STATUS").getAsString());
                                   }
                                   if(!obj.get("REMARKS").isJsonNull())
                                   {
                                        emiObject.setrEMARKS(obj.get("REMARKS").getAsString());
                                   }
                                   if(!obj.get("CURRENCY").isJsonNull())
                                   {
                                        emiObject.setcURRENCY(obj.get("CURRENCY").getAsString());
                                   }
                                   if(!obj.get("PAYMENT_STATUS").isJsonNull())
                                   {
                                        emiObject.setpAYMENTSTATUS(obj.get("PAYMENT_STATUS").getAsString());
                                   }

                                   emiList.add(emiObject);
                                   if(emiList.size()>0)
                                   {
                                       cv_emi_list.setVisibility(View.VISIBLE);
                                       tv_no_data.setVisibility(View.GONE);
                                       emiAdapter = new MeterLoanInstallmentRcvAdapter(MeterLoanSummaryActivity.this, context, emiList);
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
                        ll_main.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);
                       /* Toast toast = Toast.makeText(context, infoObject.get("errorMessage").getAsString(), Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                        toast.show();*/
                    }
                }
                else {
                    Toast toast = Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG);
                    View view = toast.getView();
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast toast = Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                toast.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
       /* intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);*/
        super.onBackPressed();
    }
}
