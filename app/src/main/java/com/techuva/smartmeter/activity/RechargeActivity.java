package com.techuva.smartmeter.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.DenominationAdapter;
import com.techuva.smartmeter.adapter.LifeSaverBreakupRcvAdapter;
import com.techuva.smartmeter.adapter.LifeSaverTaxesRcvAdapter;
import com.techuva.smartmeter.adapter.LifesaverListAdapter;
import com.techuva.smartmeter.adapter.RechargeBreakupRcvAdapter;
import com.techuva.smartmeter.adapter.RechargeTaxesRcvAdapter;
import com.techuva.smartmeter.api_interface.GetDenominationDataInterface;
import com.techuva.smartmeter.api_interface.LifeSaverRechargeActivationInterface;
import com.techuva.smartmeter.api_interface.LifeSaverRechargeCalculationInterface;
import com.techuva.smartmeter.api_interface.PostpaidBillDataCalInterface;
import com.techuva.smartmeter.api_interface.RechargeCalculationInterface;
import com.techuva.smartmeter.api_interface.RechargeInsertDataInterface;
import com.techuva.smartmeter.api_interface.RechargeUpdateDataInterface;
import com.techuva.smartmeter.api_interface.ScratchCardCalculationInterface;
import com.techuva.smartmeter.api_interface.ScratchCardInsertInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.LifeSaverCalPostParameters;
import com.techuva.smartmeter.postParameters.LifeSaverInsertPostParameters;
import com.techuva.smartmeter.postParameters.PostpaidBillAmountPostParameters;
import com.techuva.smartmeter.postParameters.RechargeCalPostParameters;
import com.techuva.smartmeter.postParameters.RechargeInsertPostParameter;
import com.techuva.smartmeter.postParameters.ScrachCardCalPostParameters;
import com.techuva.smartmeter.postParameters.ScrachCardInsertPostParameters;
import com.techuva.smartmeter.responseModel.DenominationMainObject;
import com.techuva.smartmeter.responseModel.DenominationResultObject;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationBreakup;
import com.techuva.smartmeter.responseModel.LifeSaverInsertInfoObject;
import com.techuva.smartmeter.responseModel.LifeSaverInsertMainObject;
import com.techuva.smartmeter.responseModel.LifesaverDropDownResultObject;
import com.techuva.smartmeter.responseModel.PostpaidBillBreakupCal;
import com.techuva.smartmeter.responseModel.PostpaidBillDataMainObject;
import com.techuva.smartmeter.responseModel.PostpaidBillDataResultObject;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;
import com.techuva.smartmeter.responseModel.RechargeInsertMainObject;
import com.techuva.smartmeter.responseModel.RechargeInsertResultObject;
import com.techuva.smartmeter.responseModel.RechargeUpdateMainObject;
import com.techuva.smartmeter.responseModel.ScratchCardInsertMainObject;
import com.techuva.smartmeter.utils.MApplication;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RechargeActivity extends AppCompatActivity {

    LinearLayout ll_layout_recharge, ll_button_recharge, ll_denominations, ll_no_postpaid_bill, ll_back_btn, ll_lifesaver_dropdown, ll_paid_by;
    ScrollView sv_layout_recharge;
    EditText edt_usn_num, edt_amount;
    RadioGroup  rg_connection_type, rg_payment_option;
    RadioButton rb_postpaid, rb_prepaid, rb_card_type, rb_recharge_card, rb_life_saver;
    Context context;
    String usn_num="";
    String amount;
    String userId;
    LinearLayout ll_review_recharge, ll_usable_amt, ll_fixed_charges, ll_vat, ll_total_amt, ll_button_cancel, ll_button_pay;
    TextView tv_usn_text, tv_usn_num_confirm, tv_paid_by_text, tv_comments_text, tv_total_amt_txt, tv_total_amt, tv_usn_heading;
    TextView tv_usable_amt_txt, tv_usable_amt, tv_fixed_charges_txt, tv_fixed_charges, tv_vat_txt, tv_vat, tv_pay, tv_amount_heading;
    TextView tv_heading_recharge, tv_btn_recharge_text, tv_chooseLifeSaverType;
    EditText edt_paid_by, edt_comments;
    RecyclerView rcv_payment_breakup, rcv_denominations, rcv_payment_taxes;
    View view_amt, view_paid_by, view_lifesaver_dropdown;
    String authorityKey="";
    ArrayList<RechargeCalBreakupObject> rechargeBreakupList;
    ArrayList<RechargeCalBreakupObject> rechargeTaxesList;
    ArrayList<PostpaidBillBreakupCal> postpaidBreakupList;
    ArrayList<LifeSaverCalculationBreakup> lifeSaverBreakupList;
    ArrayList<LifeSaverCalculationBreakup> lifeSaverTaxesList;
    ArrayList<LifesaverDropDownResultObject> lifesaverList;
    DenominationAdapter denominationAdapter;
    LifeSaverBreakupRcvAdapter lifeSaverBreakupRcvAdapter;
    LifeSaverTaxesRcvAdapter lifeSaverTaxesRcvAdapter;
    RechargeTaxesRcvAdapter taxesRcvAdapter;
    RechargeBreakupRcvAdapter breakupRcvAdapter;
    LifesaverListAdapter lifesaverListAdapter;
    double usable_amt;
    double total_recharge_amt;
    String paid_by = "";
    String remarks = "";
    Activity activity;
    String connectionType="";
    Boolean fromLifeSaver= false;
    double base_amount;
    JSONObject jsonUpdateObject;
    JsonObject objectForUpdate;
    int billingId;
    public AlertDialog alertDialog;
    String rechargePlanId="";
    String rechargeCode ="";
    Boolean isFromScrachCard= false;
    String actualRechargeAmount ="";
    Spinner spinLifeSaverAmount;
    public AlertDialog.Builder alertBuilder;
    String lifeSaverAmount;
    int lifeSaverId;


    public Dialog dialog;

    private AnimationDrawable animationDrawable;

    PostpaidBillDataResultObject postpaidResultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        init();
        rg_connection_type.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.rb_postpaid:
                    connectionType = Constants.Postpaid_Connection;
                    rb_recharge_card.setVisibility(View.GONE);
                    ll_denominations.setVisibility(View.GONE);
                    tv_amount_heading.setText(getResources().getString(R.string.bill_amount));
                    edt_amount.setHint(getResources().getString(R.string.amount));
                    ll_denominations.setVisibility(View.GONE);
                    view_lifesaver_dropdown.setVisibility(View.GONE);
                    if(!usn_num.equals("")){
                        showLoaderNew();
                        serviceCallForGettingPostpaidBill();
                        edt_amount.setEnabled(false);
                    }

                    else  Toast.makeText(context, "Please enter USN number", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_prepaid:
                    tv_amount_heading.setVisibility(View.VISIBLE);
                    edt_amount.setVisibility(View.VISIBLE);
                    tv_amount_heading.setText(getResources().getString(R.string.amount_to_recharge));
                    connectionType = Constants.Prepaid_Connection;
                    rb_card_type.setChecked(true);
                    rb_recharge_card.setVisibility(View.VISIBLE);
                    edt_amount.setVisibility(View.VISIBLE);
                    view_amt.setVisibility(View.VISIBLE);
                    edt_amount.setEnabled(true);
                    break;
            }
        });

        rcv_denominations.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv_denominations, new RecyclerItemClickListener.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int position) {
                String i = String.valueOf(denominationAdapter.getItem(position));
                rechargePlanId = i;
                //notifyAll();
                //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        ll_button_cancel.setOnClickListener(v -> finish());


        ll_back_btn.setOnClickListener(v -> {
            MApplication.setBoolean(context, Constants.IsHomeSelected, true);
            Intent intent = new Intent(context, UserAccountsActivity.class);
            startActivity(intent);
        });

        rg_payment_option.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.rb_card_type:
                    edt_amount.setEnabled(true);
                    edt_amount.setHint(getResources().getString(R.string.amount));
                    isFromScrachCard = false;
                    MApplication.setBoolean(context, Constants.IsFromRechargeCard, isFromScrachCard);
                    ll_denominations.setVisibility(View.GONE);

                    if(rb_postpaid.isChecked()){
                        tv_amount_heading.setText(getResources().getString(R.string.bill_amount));
                    }
                    else if(rb_prepaid.isChecked()){
                        tv_amount_heading.setText(getResources().getString(R.string.amount_to_recharge));
                    }
                    usn_num = edt_usn_num.getText().toString();
                       if(!usn_num.equals(""))
                       {
                           if (rb_postpaid.isChecked() && edt_amount.getText().equals(""))
                           {
                               serviceCallForGettingPostpaidBill();
                           }
                       }
                       else  Toast.makeText(context, "Please enter USN number", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_recharge_card:
                    isFromScrachCard = true;
                    MApplication.setBoolean(context, Constants.IsFromRechargeCard, isFromScrachCard);
                    tv_amount_heading.setText(getResources().getString(R.string.rechage_code_enter));
                    edt_amount.setEnabled(true);
                    serviceCallForDenominations();
                    break;
            }
        });


        spinLifeSaverAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lifeSaverId = lifesaverListAdapter.getItem(position).getLIFESAVERID();
                lifeSaverAmount = String.valueOf(lifesaverListAdapter.getItem(position).getAMOUNT());
                serviceCallforLifeSaverCalculation(lifeSaverAmount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ll_button_recharge.setOnClickListener(v -> {
            usn_num = edt_usn_num.getText().toString();
            amount = edt_amount.getText().toString();
            if(MApplication.isNetConnected(context)){
                if(!usn_num.equals(""))
                {

                    if (connectionType.equals(Constants.Postpaid_Connection) || connectionType.equals("Postpaid"))
                    {
                        if(amount.equals(""))
                        {
                            serviceCallForGettingPostpaidBill();
                        }
                        else {
                            {
                                reviewPostpaidBillData(postpaidResultData);
                            }
                        }

                        //serviceCallForPostpaidInsert();
             /*     else {
                       if(!amount.equals(""))
                       {
                           reviewPostpaidBillData(postpaidResultData);
                       }
                       else
                       {
                           Toast.makeText(context, "Please enter amount", Toast.LENGTH_SHORT).show();
                       }
                   }*/
                    }
                    else if(connectionType.equals(Constants.Prepaid_Connection)  || connectionType.equals("Prepaid"))
                    {
                        if(isFromScrachCard)
                        {
                            rechargeCode = edt_amount.getText().toString();
                            if(!rechargeCode.equals("") )
                            {
                                if(!rechargePlanId.equals(""))
                                {
                                    serviceCallForScratchCardCalculate();
                                }
                                else {
                                    Toast.makeText(context, "Please select recharge plan", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(context, "Please enter recharge code", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            if (!amount.equals("") && amount.matches("[0-9].+") && amount.length()<=10)
                            {
                                serviceCallForRechargeCalculationPrepaid();
                            }
                            else
                            {
                                Toast.makeText(context, "Please enter valid amount", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(context, "Please enter valid USN num", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        });

        edt_usn_num.setOnClickListener(v -> edt_amount.setEnabled(true));

        ll_button_pay.setOnClickListener(v -> {
            usn_num = tv_usn_num_confirm.getText().toString();
            paid_by = edt_paid_by.getText().toString();
            remarks = edt_comments.getText().toString();
            if(tv_pay.getText().equals(getResources().getString(R.string.activate_txt)))
            {
                if(MApplication.isNetConnected(context)){
                    serviceCallforLifeSaverInsert();
                }else {
                    Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if(MApplication.isNetConnected(context)){

                    if(connectionType.equals("Prepaid") || connectionType.equals(Constants.Prepaid_Connection))
                    {

                        if(isFromScrachCard) {
                            if(remarks.equals(""))
                            {
                                remarks = "";
                                serviceCallForScratchCardInsert();
                            }
                            else {
                                // Toast.makeText(context, "Please enter remarks!", Toast.LENGTH_SHORT).show();
                                serviceCallForScratchCardInsert();
                            }
                        }

                        else if(!remarks.equals(""))
                        {
                            MApplication.setBoolean(context, Constants.IsFromRechargeCard, false);
                            serviceCallForRechargeInsert();
                        }
                        else {
                            Toast.makeText(context, "Please enter remarks!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if(connectionType.equals("Postpaid") || connectionType.equals(Constants.Postpaid_Connection))
                    {
                        if(!remarks.equals(""))
                        {
                            MApplication.setBoolean(context, Constants.IsFromRechargeCard, false);
                            serviceCallForPostpaidInsert();
                        }
                        else {
                            Toast.makeText(context, "Please enter remarks!", Toast.LENGTH_SHORT).show();
                        }
                    }                }else {
                    Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        context = RechargeActivity.this;
        activity = RechargeActivity.this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        fromLifeSaver = MApplication.getBoolean(context, Constants.FROM_LIFESAVER);
        ll_layout_recharge = findViewById(R.id.ll_layout_recharge);
        ll_button_recharge = findViewById(R.id.ll_button_recharge);
        ll_denominations = findViewById(R.id.ll_denominations);
        ll_no_postpaid_bill = findViewById(R.id.ll_no_postpaid_bill);
        ll_back_btn = findViewById(R.id.ll_back_btn);
        ll_lifesaver_dropdown = findViewById(R.id.ll_lifesaver_dropdown);
        ll_paid_by = findViewById(R.id.ll_paid_by);
        // sv_layout_recharge, sv_review_recharge;
        sv_layout_recharge = findViewById(R.id.sv_layout_recharge);
        //sv_review_recharge = findViewById(R.id.sv_review_recharge);
        edt_usn_num = findViewById(R.id.edt_usn_num);
        edt_amount = findViewById(R.id.edt_amount);
        rg_connection_type = findViewById(R.id.rg_connection_type);
        rg_payment_option = findViewById(R.id.rg_payment_option);
        rb_postpaid = findViewById(R.id.rb_postpaid);
        rb_prepaid = findViewById(R.id.rb_prepaid);
        rb_card_type = findViewById(R.id.rb_card_type);
        rb_recharge_card = findViewById(R.id.rb_recharge_card);
        rb_life_saver = findViewById(R.id.rb_life_saver);
        userId = MApplication.getString(context, Constants.UserID);
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        usn_num = MApplication.getString(context, Constants.USN_NUM);
        tv_usn_heading = findViewById(R.id.tv_usn_heading);
        tv_amount_heading = findViewById(R.id.tv_amount_heading);
        tv_heading_recharge = findViewById(R.id.tv_heading_recharge);
        tv_btn_recharge_text = findViewById(R.id.tv_btn_recharge_text);
        tv_chooseLifeSaverType = findViewById(R.id.tv_chooseLifeSaverType);
        //usn_num = tv_usn_num_confirm.getText().toString();
        spinLifeSaverAmount = findViewById(R.id.spinLifeSaverAmount);
        if(MApplication.getBoolean(context, Constants.IsUSNSelected))
        {
            if(MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Prepaid"))
            {
                rb_prepaid.setChecked(true);
                edt_amount.setEnabled(true);
                rb_card_type.setChecked(true);
                tv_amount_heading.setText(getResources().getString(R.string.amount_to_recharge));
                rb_recharge_card.setVisibility(View.VISIBLE);
            }
            else {
                rb_postpaid.setChecked(true);
                rb_card_type.setChecked(true);
                edt_amount.setEnabled(false);
                tv_amount_heading.setText(getResources().getString(R.string.bill_amount));
                if(!usn_num.equals(""))
                {
                    MApplication.setBoolean(context, Constants.IsFromRechargeCard, false);
                    serviceCallForGettingPostpaidBill();
                }

            }
            edt_usn_num.setText(MApplication.getString(context, Constants.SelectedUSN));
        }
        else
        {
            edt_usn_num.setText("");
        }

        usn_num = edt_usn_num.getText().toString();
        amount = edt_amount.getText().toString();
        //Layout Review Recharge
        ll_review_recharge = findViewById(R.id.ll_review_recharge);
        ll_total_amt = findViewById(R.id.ll_total_amt);
        ll_button_cancel = findViewById(R.id.ll_button_cancel);
        ll_button_pay = findViewById(R.id.ll_button_pay);
        tv_usn_text = findViewById(R.id.tv_usn_text);
        tv_usn_num_confirm = findViewById(R.id.tv_usn_num_confirm);
        tv_paid_by_text = findViewById(R.id.tv_paid_by_text);
        tv_comments_text = findViewById(R.id.tv_comments_text);
        tv_total_amt_txt = findViewById(R.id.tv_total_amt_txt);
        tv_total_amt = findViewById(R.id.tv_total_amt);
        tv_usable_amt_txt = findViewById(R.id.tv_usable_amt_txt);
        tv_usable_amt = findViewById(R.id.tv_usable_amt);
        tv_fixed_charges_txt = findViewById(R.id.tv_fixed_charges_txt);
        tv_fixed_charges = findViewById(R.id.tv_fixed_charges);
        tv_vat_txt = findViewById(R.id.tv_vat_txt);
        tv_vat = findViewById(R.id.tv_vat);
        tv_pay = findViewById(R.id.tv_pay);
        edt_paid_by = findViewById(R.id.edt_paid_by);
        edt_comments = findViewById(R.id.edt_comments);
        rcv_payment_breakup = findViewById(R.id.rcv_payment_breakup);
        rcv_payment_taxes = findViewById(R.id.rcv_payment_taxes);
        rcv_denominations = findViewById(R.id.rcv_denominations);
        view_amt = findViewById(R.id.view_amt);
        view_paid_by = findViewById(R.id.view_paid_by);
        view_lifesaver_dropdown = findViewById(R.id.view_lifesaver_dropdown);
        setTypeFace();

        if(fromLifeSaver)
        {
            usn_num = MApplication.getString(context, Constants.USN_NUM);
            //Added by Nikita
            ll_lifesaver_dropdown.setVisibility(View.VISIBLE);
            ll_review_recharge.setVisibility(View.VISIBLE);
            //sv_review_recharge.setVisibility(View.VISIBLE);
            ll_layout_recharge.setVisibility(View.GONE);
            sv_layout_recharge.setVisibility(View.GONE);
            ll_paid_by.setVisibility(View.GONE);
            view_paid_by.setVisibility(View.GONE);
            rcv_payment_taxes.setVisibility(View.VISIBLE);
            showLoaderNew();
            serviceCallForGettingLifesaverType();
       }
        else {
            ll_review_recharge.setVisibility(View.GONE);
            //sv_review_recharge.setVisibility(View.GONE);
            rcv_payment_taxes.setVisibility(View.VISIBLE);
            ll_lifesaver_dropdown.setVisibility(View.GONE);
            ll_layout_recharge.setVisibility(View.VISIBLE);
            sv_layout_recharge.setVisibility(View.VISIBLE);
        }
        connectionType = MApplication.getString(context, Constants.CONNECTION_TYPE);
    }

    private void setTypeFace() {
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Light.otf");

        edt_paid_by.setTypeface(faceLight);
        edt_comments.setTypeface(faceLight);
        tv_heading_recharge.setTypeface(faceLight);
        tv_btn_recharge_text.setTypeface(faceLight);
       /* //tv_usable_amt_txt.setTypeface(faceLight);
        tv_usable_amt.setTypeface(faceLight);
        tv_fixed_charges_txt.setTypeface(faceLight);
        tv_fixed_charges.setTypeface(faceLight);
        tv_vat_txt.setTypeface(faceLight);
        tv_vat.setTypeface(faceLight);*/
        tv_pay.setTypeface(faceLight);
        tv_amount_heading.setTypeface(faceLight);
        tv_usn_text.setTypeface(faceLight);
        tv_usn_num_confirm.setTypeface(faceLight);
        tv_paid_by_text.setTypeface(faceLight);
        tv_comments_text.setTypeface(faceLight);
        tv_total_amt_txt.setTypeface(faceLight);
        tv_total_amt.setTypeface(faceLight);
        tv_usn_heading.setTypeface(faceLight);
    }

    private void serviceCallForRechargeCalculationPrepaid() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NumberFormat nf = NumberFormat.getInstance();
        // base_amount = nf.parse(amount).doubleValue();
        base_amount = Double.parseDouble(amount);
        //----
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String baseAmount = df.format(base_amount);

        RechargeCalculationInterface service = retrofit.create(RechargeCalculationInterface.class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new RechargeCalPostParameters(usn_num , baseAmount
        ));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){

                    JsonObject jsonObject = response.body().getAsJsonObject();
                    if(jsonObject!=null)
                    {
                        JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                        int errorCode = infoObject.get("errorCode").getAsInt();
                        String errorMsg = infoObject.get("errorMessage").getAsString();
                        if (errorCode == 0)
                        {
                            JsonObject result = jsonObject.get("result").getAsJsonObject();
                            dataResponse(result);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), errorMsg,Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void dataResponse(JsonObject result) {
        ll_layout_recharge.setVisibility(View.GONE);
        sv_layout_recharge.setVisibility(View.GONE);
        ll_review_recharge.setVisibility(View.VISIBLE);
        //sv_review_recharge.setVisibility(View.VISIBLE);
        view_lifesaver_dropdown.setVisibility(View.GONE);
        ll_paid_by.setVisibility(View.GONE);
        view_paid_by.setVisibility(View.GONE);
        String usnNum = result.get("USN_No").getAsString();
        tv_usn_num_confirm.setText(usnNum);
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String amount = result.get("totalAmount").getAsString();
        total_recharge_amt = result.get("totalAmount").getAsDouble();
        tv_total_amt.setText(getResources().getString(R.string.currency)+" "+amount);
        edt_paid_by.setText(MApplication.getString(context, Constants.UserName));
        edt_paid_by.setEnabled(false);
        rechargeBreakupList = new ArrayList<>();
        rechargeTaxesList = new ArrayList<>();
        usable_amt = Double.valueOf(result.get("usableAmount").getAsString());
        JsonArray breakupArray = result.get("breakup").getAsJsonArray();
        for (int i=0; i<breakupArray.size(); i++)
        {
            JsonObject jsonObject = breakupArray.get(i).getAsJsonObject();
            RechargeCalBreakupObject object = new RechargeCalBreakupObject();
            object.setCalculationType(jsonObject.get("Calculation_Type").getAsInt());
            object.setBreakupRate(jsonObject.get("Breakup_Rate").getAsDouble());
            object.setBreakupDesc(jsonObject.get("Breakup_Desc").getAsString());
            object.setBreakupAmount(jsonObject.get("Breakup_Amount").getAsString());
            object.setTaxes(jsonObject.get("Taxes").getAsString());
            rechargeBreakupList.add(object);
        }
        if(rechargeBreakupList.size()>0)
        {
            breakupRcvAdapter = new RechargeBreakupRcvAdapter(context, rechargeBreakupList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcv_payment_breakup.setLayoutManager(linearLayoutManager);
            rcv_payment_breakup.setAdapter(breakupRcvAdapter);
        }


        JsonArray taxArray = result.get("tax").getAsJsonArray();
        for (int i=0; i<taxArray.size(); i++)
        {
            JsonObject resultObject = taxArray.get(i).getAsJsonObject();
            RechargeCalBreakupObject object = new RechargeCalBreakupObject();
            if(!resultObject.get("Calculation_Type").isJsonNull())
            {
                object.setBreakupRate(resultObject.get("Breakup_Rate").getAsDouble());
                object.setBreakupAmount(resultObject.get("Breakup_Amount").getAsString());
                object.setBreakupDesc(resultObject.get("Breakup_Desc").getAsString());
                object.setCalculationType(resultObject.get("Calculation_Type").getAsInt());
                rechargeTaxesList.add(object);
            }
        }
        if(rechargeTaxesList.size()>0)
        {
            taxesRcvAdapter = new RechargeTaxesRcvAdapter(context, rechargeTaxesList);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            rcv_payment_taxes.setLayoutManager(linearLayoutManager1);
            rcv_payment_taxes.setAdapter(taxesRcvAdapter);
            // usable_amt = result.get("usableAmount").getAsDouble();
        }


    }

    public void showLoaderNew() {
        runOnUiThread(new RechargeActivity.Runloader(getResources().getString(R.string.loading)));
    }

    class Runloader implements Runnable {
        private String strrMsg;

        public Runloader(String strMsg) {
            this.strrMsg = strMsg;
        }

        @SuppressWarnings("ResourceType")
        @Override
        public void run() {
            try {
                if (dialog == null)
                {
                    dialog = new Dialog(context,R.style.Theme_AppCompat_Light_DarkActionBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }
                dialog.setContentView(R.layout.loading);
                dialog.setCancelable(false);

                if (dialog != null && dialog.isShowing())
                {
                    dialog.dismiss();
                    dialog=null;
                }
                dialog.show();

                ImageView imgeView = (ImageView) dialog
                        .findViewById(R.id.imgeView);
                TextView tvLoading = (TextView) dialog
                        .findViewById(R.id.tvLoading);
                if (!strrMsg.equalsIgnoreCase(""))
                    tvLoading.setText(strrMsg);

                imgeView.setBackgroundResource(R.drawable.frame);

                animationDrawable = (AnimationDrawable) imgeView
                        .getBackground();
                imgeView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (animationDrawable != null)
                            animationDrawable.start();
                    }
                });
            } catch (Exception e)
            {

            }
        }
    }

    public void hideloader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); }

    private void serviceCallForRechargeInsert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RechargeInsertDataInterface service = retrofit.create(RechargeInsertDataInterface.class);

        Call<RechargeInsertMainObject> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new RechargeInsertPostParameter(usn_num, total_recharge_amt, paid_by, remarks));
        call.enqueue(new Callback<RechargeInsertMainObject>() {
            @Override
            public void onResponse(Call<RechargeInsertMainObject> call, Response<RechargeInsertMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        validateFields(response.body().getResult());
                    }
                }else {
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RechargeInsertMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

        }


    private void serviceCallForScratchCardCalculate() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScratchCardCalculationInterface service = retrofit.create(ScratchCardCalculationInterface.class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new ScrachCardCalPostParameters(usn_num, rechargePlanId, rechargeCode, "", ""));
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
                       /* JsonObject resultObject =jsonObject.get("result").getAsJsonObject();
                        ll_layout_recharge.setVisibility(View.GONE);
                        sv_layout_recharge.setVisibility(View.GONE);
                        ll_review_recharge.setVisibility(View.VISIBLE);
                        //sv_review_recharge.setVisibility(View.VISIBLE);
                        tv_usn_num_confirm.setText(resultObject.get("USN_No").getAsString());
                        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                        //double amount = df.format( resultObject.get("totalAmount").getAsString());
                        String amount =resultObject.get("totalAmount").getAsString();
                        tv_total_amt.setText(getResources().getString(R.string.currency)+" "+amount);
                        edt_paid_by.setText(MApplication.getString(context, Constants.UserName));
                        edt_paid_by.setEnabled(false);
                        rcv_payment_breakup.setVisibility(View.GONE);*/
                        JsonObject result = jsonObject.get("result").getAsJsonObject();
                        dataResponseForScratchCard(result);
                    }
                    if (errorCode==1)
                    {
                        Toast.makeText(context, infoObject.get("errorMessage").getAsString(),Toast.LENGTH_SHORT).show();

                    }
                }else {
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void dataResponseForScratchCard(JsonObject result) {
        ll_layout_recharge.setVisibility(View.GONE);
        sv_layout_recharge.setVisibility(View.GONE);
        ll_review_recharge.setVisibility(View.VISIBLE);
        //sv_review_recharge.setVisibility(View.VISIBLE);
        view_lifesaver_dropdown.setVisibility(View.GONE);
        ll_paid_by.setVisibility(View.GONE);
        view_paid_by.setVisibility(View.GONE);
        String usnNum = result.get("USN_No").getAsString();
        tv_usn_num_confirm.setText(usnNum);
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String amount = result.get("totalAmount").getAsString();
        total_recharge_amt = result.get("totalAmount").getAsDouble();
        tv_total_amt.setText(getResources().getString(R.string.currency)+" "+amount);
        edt_paid_by.setText(MApplication.getString(context, Constants.UserName));
        edt_paid_by.setEnabled(false);
        rechargeBreakupList = new ArrayList<>();
        rechargeTaxesList = new ArrayList<>();
        usable_amt = Double.valueOf(result.get("usableAmount").getAsString());
        JsonArray breakupArray = result.get("breakup").getAsJsonArray();
        for (int i=0; i<breakupArray.size(); i++)
        {
            JsonObject jsonObject = breakupArray.get(i).getAsJsonObject();
            RechargeCalBreakupObject object = new RechargeCalBreakupObject();
            object.setCalculationType(jsonObject.get("Calculation_Type").getAsInt());
            object.setBreakupRate(jsonObject.get("Breakup_Rate").getAsDouble());
            object.setBreakupDesc(jsonObject.get("Breakup_Desc").getAsString());
            object.setBreakupAmount(jsonObject.get("Breakup_Amount").getAsString());
            object.setTaxes(jsonObject.get("Taxes").getAsString());
            rechargeBreakupList.add(object);
        }
        if(rechargeBreakupList.size()>0)
        {
            breakupRcvAdapter = new RechargeBreakupRcvAdapter(context, rechargeBreakupList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcv_payment_breakup.setLayoutManager(linearLayoutManager);
            rcv_payment_breakup.setAdapter(breakupRcvAdapter);
        }


        JsonArray taxArray = result.get("tax").getAsJsonArray();
        for (int i=0; i<taxArray.size(); i++)
        {
            JsonObject resultObject = taxArray.get(i).getAsJsonObject();
            RechargeCalBreakupObject object = new RechargeCalBreakupObject();
            if(!resultObject.get("Calculation_Type").isJsonNull())
            {
                object.setBreakupRate(resultObject.get("Breakup_Rate").getAsDouble());
                object.setBreakupAmount(resultObject.get("Breakup_Amount").getAsString());
                object.setBreakupDesc(resultObject.get("Breakup_Desc").getAsString());
                object.setCalculationType(resultObject.get("Calculation_Type").getAsInt());
                rechargeTaxesList.add(object);
            }
        }
        if(rechargeTaxesList.size()>0)
        {
            taxesRcvAdapter = new RechargeTaxesRcvAdapter(context, rechargeTaxesList);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            rcv_payment_taxes.setLayoutManager(linearLayoutManager1);
            rcv_payment_taxes.setAdapter(taxesRcvAdapter);
            // usable_amt = result.get("usableAmount").getAsDouble();
        }

    }

    private void serviceCallForScratchCardInsert() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScratchCardInsertInterface service = retrofit.create(ScratchCardInsertInterface.class);

        Call<ScratchCardInsertMainObject> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new ScrachCardInsertPostParameters(usn_num, rechargePlanId, rechargeCode, "", ""));
        call.enqueue(new Callback<ScratchCardInsertMainObject>() {
            @Override
            public void onResponse(Call<ScratchCardInsertMainObject> call, Response<ScratchCardInsertMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        MApplication.setBoolean(context, Constants.IsFromRechargeCard, isFromScrachCard);
                        //Toast.makeText(getBaseContext(), "Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, true);
                        intent.putExtra(Constants.From_Card, true);
                        startActivity(intent);
                    }
                }else {
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ScratchCardInsertMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void serviceCallForPostpaidInsert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RechargeInsertDataInterface service = retrofit.create(RechargeInsertDataInterface.class);

        Call<RechargeInsertMainObject> call = service.insertPostpaidBillData(Integer.parseInt(userId), authorityKey, new RechargeInsertPostParameter(usn_num, paid_by, remarks, billingId));
        call.enqueue(new Callback<RechargeInsertMainObject>() {
            @Override
            public void onResponse(Call<RechargeInsertMainObject> call, Response<RechargeInsertMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        validateFields(response.body().getResult());
                    }
                }else {
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RechargeInsertMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void validateFields(RechargeInsertResultObject result) {
        String currency = result.getCurrency();
        String email = result.getGatewayInfo().getCustomerEmail();
        String fName = result.getGatewayInfo().getCustomerFirstname();
        String lName = result.getGatewayInfo().getCustomerLastname();
        String publicKey = result.getGatewayInfo().getPBFPubKey();
        String txRef = result.getGatewayInfo().getTxref();
        String country = "NG";
       // String amount = String.valueOf(result.getGatewayInfo().getAmount());
        String amount = String.valueOf(result.getTotalAmount());
        actualRechargeAmount = String.valueOf(result.getTotalAmount());
        MApplication.setString(context, Constants.Actual_Recharge_Amount, actualRechargeAmount);
        new RavePayManager(this).setAmount(Double.parseDouble(amount))
                .setCountry(country)
                .setCurrency(currency)
                .setEmail(email)
                .setfName(fName)
                .setlName(lName)
                .setPublicKey(publicKey)
                .setTxRef(txRef)
                .setEncryptionKey(Constants.EncryptionKey)
                .initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {

            String message = data.getStringExtra("response");
            if(message == null) {
                //Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show();
                //Log.d("rave response", message);
                }
            else{
                //jsonUpdateObject = new JSONObject();
                objectForUpdate = new JsonObject();
                //JSONObject jsonObject = new JSONObject(message);
                JsonObject jsonObject1 = (new JsonParser()).parse(message).getAsJsonObject();
                //Toast.makeText(context, ""+jsonObject1, Toast.LENGTH_SHORT).show();
                if(MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Prepaid"))
                {
                    //jsonUpdateObject = jsonObject.getJSONObject("data");
                    objectForUpdate = jsonObject1.get("data").getAsJsonObject();
                    serviceCallForRechargeUpdate();
                }
                else if(MApplication.getString(context, Constants.CONNECTION_TYPE).equals("Postpaid"))
                {
                /* jsonUpdateObject = jsonObject.getJSONObject("data");
                jsonUpdateObject.put(Constants.POSTPAID_BILLING_ID, billingId);*/
                    objectForUpdate = jsonObject1.get("data").getAsJsonObject();
                    objectForUpdate.addProperty(Constants.POSTPAID_BILLING_ID, billingId);
                    serviceCallForPostpaidRechargeUpdate();
                }

            }
            /*if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }*/
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void serviceCallForDenominations() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDenominationDataInterface service = retrofit.create(GetDenominationDataInterface.class);

        Call<DenominationMainObject> call = service.getDenominations();
        call.enqueue(new Callback<DenominationMainObject>() {
            @Override
            public void onResponse(Call<DenominationMainObject> call, Response<DenominationMainObject> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){

                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                        denomiantionResponse(response.body().getResult());
                    }

                }else {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DenominationMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void denomiantionResponse(List<DenominationResultObject> result) {
        ll_denominations.setVisibility(View.VISIBLE);
        if(result.size()>0)
        {
            edt_amount.setHint(getResources().getString(R.string.recharge_code));
            denominationAdapter = new DenominationAdapter(context, result);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rcv_denominations.setLayoutManager(layoutManager);
            rcv_denominations.setAdapter(denominationAdapter);
        }
    }

    private void serviceCallforLifeSaverCalculation(String amount){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LifeSaverRechargeCalculationInterface service = retrofit.create(LifeSaverRechargeCalculationInterface .class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey,new
                LifeSaverCalPostParameters(String.valueOf(usn_num), amount));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    if(jsonObject!=null)
                    {
                        JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                        int errorCode = infoObject.get("errorCode").getAsInt();
                        if (errorCode == 0)
                        {
                            JsonObject result = jsonObject.get("result").getAsJsonObject();
                            dataResponseForLifeSaverCalculation(result);
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
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
                //  Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void serviceCallForGettingLifesaverType(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LifeSaverRechargeCalculationInterface service = retrofit.create(LifeSaverRechargeCalculationInterface.class);

        Call<JsonElement> call = service.getLifesaverTypes(Integer.parseInt(userId), authorityKey);
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
                    if(errorCode==0)
                    {
                        JsonArray resultArray = jsonObject.get("result").getAsJsonArray();
                        if(resultArray.size()>0)
                        {
                            lifesaverList = new ArrayList<>();
                            for (int i=0; i<resultArray.size(); i++)
                            { 
                                JsonObject result = resultArray.get(i).getAsJsonObject();
                               LifesaverDropDownResultObject object = new LifesaverDropDownResultObject();
                               object.setLIFESAVERID(result.get("LIFESAVER_ID").getAsInt());
                               object.setAMOUNT(result.get("AMOUNT").getAsInt());
                               lifesaverList.add(object);
                            }
                        }
                        if(lifesaverList.size()>0)
                        {
                            setLifeSaverDropdownData(lifesaverList);
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
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
                //  Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void setLifeSaverDropdownData(ArrayList<LifesaverDropDownResultObject> lifesaverList) {
       lifesaverListAdapter = new LifesaverListAdapter(RechargeActivity.this, R.layout.item_lifesaver_list, R.id.tv_state_name, lifesaverList);
        spinLifeSaverAmount.setAdapter(lifesaverListAdapter);
    }

    private void dataResponseForLifeSaverCalculation(JsonObject result) {
        ll_review_recharge.setVisibility(View.VISIBLE);
        //sv_review_recharge.setVisibility(View.VISIBLE);
        ll_layout_recharge.setVisibility(View.GONE);
        sv_layout_recharge.setVisibility(View.GONE);
        tv_pay.setText(getResources().getText(R.string.activate_txt));
        String usnNum =result.get("USN_No").getAsString();
        tv_usn_num_confirm.setText(usnNum);
       /* DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS*/
        String amount = result.get("totalAmount").getAsString();
        tv_total_amt.setText(getResources().getString(R.string.currency)+" "+amount);
        edt_paid_by.setText(MApplication.getString(context, Constants.UserName));
        edt_paid_by.setEnabled(false);
        lifeSaverBreakupList = new ArrayList<>();
        lifeSaverTaxesList = new ArrayList<>();
        JsonArray jsonArray = result.get("breakup").getAsJsonArray();
        for (int i=0; i<jsonArray.size(); i++)
        {
            JsonObject resultObject = jsonArray.get(i).getAsJsonObject();
            LifeSaverCalculationBreakup object = new LifeSaverCalculationBreakup();
            object.setBreakupRate(resultObject.get("Breakup_Rate").getAsDouble());
            object.setBreakupAmount(resultObject.get("Breakup_Amount").getAsString());
            object.setBreakupDesc(resultObject.get("Breakup_Desc").getAsString());
            lifeSaverBreakupList.add(object);
        }
        lifeSaverBreakupRcvAdapter = new LifeSaverBreakupRcvAdapter(context, lifeSaverBreakupList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_payment_breakup.setLayoutManager(linearLayoutManager);
        rcv_payment_breakup.setAdapter(lifeSaverBreakupRcvAdapter);
        usable_amt = result.get("usableAmount").getAsDouble();

        JsonArray taxArray = result.get("tax").getAsJsonArray();
        for (int i=0; i<taxArray.size(); i++)
        {
            JsonObject resultObject = taxArray.get(i).getAsJsonObject();
            LifeSaverCalculationBreakup object = new LifeSaverCalculationBreakup();

            if(!resultObject.get("Calculation_Type").isJsonNull())
            {
                object.setBreakupRate(resultObject.get("Breakup_Rate").getAsDouble());
                object.setBreakupAmount(resultObject.get("Breakup_Amount").getAsString());
                object.setBreakupDesc(resultObject.get("Breakup_Desc").getAsString());
                object.setCalculationType(resultObject.get("Calculation_Type").getAsInt());
                lifeSaverTaxesList.add(object);
            }
        }
        if(lifeSaverTaxesList.size()>0)
        {
            lifeSaverTaxesRcvAdapter = new LifeSaverTaxesRcvAdapter(context, lifeSaverTaxesList);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            rcv_payment_taxes.setLayoutManager(linearLayoutManager1);
            rcv_payment_taxes.setAdapter(lifeSaverTaxesRcvAdapter);
           // usable_amt = result.get("usableAmount").getAsDouble();
        }
    }

    private void serviceCallForRechargeUpdate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RechargeUpdateDataInterface service = retrofit.create(RechargeUpdateDataInterface.class);


        Call<RechargeUpdateMainObject> call = service.getStringScalarforPrepaid(Integer.parseInt(userId),authorityKey, objectForUpdate);
        call.enqueue(new Callback<RechargeUpdateMainObject>() {
            @Override
            public void onResponse(Call<RechargeUpdateMainObject> call, Response<RechargeUpdateMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                       // Toast.makeText(getBaseContext(), "Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, true);
                        intent.putExtra(Constants.RECHARGE_AMOUNT, String.valueOf(response.body().getResult().getAmount()));
                        intent.putExtra(Constants.Actual_Recharge_Amount, actualRechargeAmount);
                        intent.putExtra(Constants.CURRENCY_OF_RECHARGE, response.body().getResult().getCurrency());
                        intent.putExtra(Constants.PAYMENT_REF_NUM, response.body().getResult().getTxRef());
                        intent.putExtra(Constants.DATE_OF_RECHARGE, response.body().getResult().getCreatedAt());
                        intent.putExtra(Constants.PAYMENT_METHOD, response.body().getResult().getPaymentType());
                        startActivity(intent);
                    }
                    else if (response.body().getInfo().getErrorCode()==1){
                        Toast.makeText(getBaseContext(), "Failed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, false);
                        startActivity(intent);
                    }
                }else {

                }

            }

            @Override
            public void onFailure(Call<RechargeUpdateMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void serviceCallForPostpaidRechargeUpdate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RechargeUpdateDataInterface service = retrofit.create(RechargeUpdateDataInterface.class);


        Call<RechargeUpdateMainObject> call = service.getStringScalarforPostpaid(Integer.parseInt(userId), authorityKey, objectForUpdate);
        call.enqueue(new Callback<RechargeUpdateMainObject>() {
            @Override
            public void onResponse(Call<RechargeUpdateMainObject> call, Response<RechargeUpdateMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        //Toast.makeText(getBaseContext(), "Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, true);
                        intent.putExtra(Constants.Actual_Recharge_Amount, actualRechargeAmount);
                        intent.putExtra(Constants.RECHARGE_AMOUNT, String.valueOf(response.body().getResult().getAmount()));
                        intent.putExtra(Constants.CURRENCY_OF_RECHARGE, response.body().getResult().getCurrency());
                        intent.putExtra(Constants.PAYMENT_REF_NUM, response.body().getResult().getTxRef());
                        intent.putExtra(Constants.DATE_OF_RECHARGE, response.body().getResult().getCreatedAt());
                        intent.putExtra(Constants.PAYMENT_METHOD, response.body().getResult().getPaymentType());
                        startActivity(intent);
                    }
                    else if (response.body().getInfo().getErrorCode()==1){
                        Toast.makeText(getBaseContext(), "Failed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, false);
                        startActivity(intent);
                    }
                }else {

                }

            }

            @Override
            public void onFailure(Call<RechargeUpdateMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void serviceCallForGettingPostpaidBill() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostpaidBillDataCalInterface service = retrofit.create(PostpaidBillDataCalInterface.class);

        Call<PostpaidBillDataMainObject> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new PostpaidBillAmountPostParameters(usn_num));
        call.enqueue(new Callback<PostpaidBillDataMainObject>() {
            @Override
            public void onResponse(Call<PostpaidBillDataMainObject> call, Response<PostpaidBillDataMainObject> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){

                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                        dataResponseForPostpaidBillAmount(response.body().getResult());
                    }
                    else if(response.body().getInfo().getErrorCode()==1)
                    {
                        if (response.body().getInfo().getErrorMessage().equals("Not a Post-Paid Meter"))
                        {
                            Toast.makeText(getBaseContext(), ""+response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                        }
                        else if (response.body().getInfo().getErrorMessage().equals("Invalid USN_NO..!!"))
                        {
                            Toast.makeText(getBaseContext(), ""+response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                        }
                       else {
                            /*ll_layout_recharge.setVisibility(View.GONE);
                            sv_layout_recharge.setVisibility(View.GONE);
                            ll_review_recharge.setVisibility(View.GONE);
                            sv_review_recharge.setVisibility(View.GONE);
                            ll_no_postpaid_bill.setVisibility(View.VISIBLE);*/
                            Toast.makeText(getBaseContext(), ""+response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                            tv_amount_heading.setVisibility(View.GONE);
                            edt_amount.setVisibility(View.GONE);
                        }

                    }

                }else {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostpaidBillDataMainObject> call, Throwable t) {
                hideloader();
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void dataResponseForPostpaidBillAmount(PostpaidBillDataResultObject result) {
        edt_amount.setVisibility(View.VISIBLE);
        tv_amount_heading.setVisibility(View.VISIBLE);
        edt_amount.setText(String.valueOf(result.getTotalAmount()));
        billingId = result.getPostpaidBillingId();
        edt_amount.setEnabled(false);
        postpaidResultData = result;
    }

    private void serviceCallforLifeSaverInsert(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LifeSaverRechargeActivationInterface service = retrofit.create(LifeSaverRechargeActivationInterface .class);

        Call<LifeSaverInsertMainObject> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey,new
                LifeSaverInsertPostParameters(String.valueOf(usn_num), lifeSaverAmount ));
        call.enqueue(new Callback<LifeSaverInsertMainObject>() {
            @Override
            public void onResponse(Call<LifeSaverInsertMainObject> call, Response<LifeSaverInsertMainObject> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if(response.body().getResult()!=null)
                    {
                        dataResponseForLifeSaverInsert(response.body().getInfo());
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    //  Toast.makeText(getBaseContext(), "Data Error",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LifeSaverInsertMainObject> call, Throwable t) {
                hideloader();
                //  Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void dataResponseForLifeSaverInsert(LifeSaverInsertInfoObject result) {
        Toast.makeText(context, result.getErrorMessage(), Toast.LENGTH_SHORT).show();
        MApplication.setBoolean(context, Constants.IsHomeSelected, true);
        Intent intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);
    }



    private void reviewPostpaidBillData(PostpaidBillDataResultObject result)
    {
        if(result==null)
        {
            serviceCallForGettingPostpaidBill();
        }
        else {
            DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
            String amount = df.format(result.getTotalAmount());
            tv_total_amt.setText(getResources().getString(R.string.currency)+" "+amount);
            ll_layout_recharge.setVisibility(View.GONE);
            sv_layout_recharge.setVisibility(View.GONE);
            view_lifesaver_dropdown.setVisibility(View.GONE);
            ll_review_recharge.setVisibility(View.VISIBLE);
            //sv_review_recharge.setVisibility(View.VISIBLE);
            tv_usn_num_confirm.setText(result.getUSNNo());
            //tv_total_amt.setText(String.valueOf(result.getTotalAmount()));
            edt_paid_by.setText(MApplication.getString(context, Constants.UserName));
            edt_paid_by.setEnabled(false);
            rechargeBreakupList = new ArrayList<>();
            rechargeBreakupList.addAll(result.getBreakup());
            breakupRcvAdapter = new RechargeBreakupRcvAdapter(context, rechargeBreakupList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcv_payment_breakup.setLayoutManager(linearLayoutManager);
            rcv_payment_breakup.setAdapter(breakupRcvAdapter);
        }

        //usable_amt = result.getUsableAmount();
    }

}
