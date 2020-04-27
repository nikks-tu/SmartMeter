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
import com.techuva.smartmeter.adapter.RechargeBreakupRcvAdapter;
import com.techuva.smartmeter.adapter.RechargeTaxesRcvAdapter;
import com.techuva.smartmeter.api_interface.InstallmentsRechargeInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.InstallmentRechargePostParameters;
import com.techuva.smartmeter.responseModel.PostpaidBillDataResultObject;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;
import com.techuva.smartmeter.responseModel.RechargeInsertMainObject;
import com.techuva.smartmeter.responseModel.RechargeInsertResultObject;
import com.techuva.smartmeter.responseModel.RechargeUpdateMainObject;
import com.techuva.smartmeter.utils.MApplication;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PayInstallmentsActivity extends AppCompatActivity {

    LinearLayout ll_layout_recharge, ll_button_recharge, ll_denominations, ll_no_postpaid_bill, ll_back_btn, ll_lifesaver_dropdown, ll_paid_by;
    ScrollView sv_layout_recharge;
    EditText edt_usn_num, edt_amount;
    RadioGroup  rg_connection_type, rg_payment_option;
    RadioButton rb_postpaid, rb_prepaid, rb_card_type, rb_recharge_card, rb_life_saver;
    Context context;
    String usn_num="";
    String amount;
    String userId;
    LinearLayout ll_review_recharge, ll_total_amt, ll_button_cancel, ll_button_pay;
    TextView tv_usn_text, tv_usn_num_confirm, tv_paid_by_text, tv_comments_text, tv_total_amt_txt, tv_total_amt, tv_usn_heading;
    TextView tv_usable_amt_txt, tv_usable_amt, tv_fixed_charges_txt, tv_fixed_charges, tv_vat_txt, tv_vat, tv_pay, tv_amount_heading;
    TextView tv_heading_recharge, tv_btn_recharge_text, tv_chooseLifeSaverType;
    EditText edt_paid_by, edt_comments;
    RecyclerView rcv_payment_breakup, rcv_denominations, rcv_payment_taxes;
    View view_amt, view_paid_by, view_lifesaver_dropdown;
    String authorityKey="";
    ArrayList<RechargeCalBreakupObject> rechargeBreakupList;
    ArrayList<RechargeCalBreakupObject> rechargeTaxesList;
    DenominationAdapter denominationAdapter;
    RechargeTaxesRcvAdapter taxesRcvAdapter;
    RechargeBreakupRcvAdapter breakupRcvAdapter;
    double usable_amt;
    double total_recharge_amt;
    String paid_by = "";
    String remarks = "";
    Activity activity;
    String connectionType="";
    Boolean fromLifeSaver= false;
    double base_amount;
    JsonObject objectForUpdate;
    int billingId;
    String rechargePlanId="";
    String actualRechargeAmount ="";
    Spinner spinLifeSaverAmount;
    String lifeSaverAmount;
    String mgrPayemntId;
    String loanEmiId;
    String typeOfInstallment="";


    public Dialog dialog;

    private AnimationDrawable animationDrawable;

    PostpaidBillDataResultObject postpaidResultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_installment);
        init();
        getInput();

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

        ll_button_recharge.setOnClickListener(v -> {
            usn_num = edt_usn_num.getText().toString();
            amount = edt_amount.getText().toString();
            if(MApplication.isNetConnected(context)){
                if(!usn_num.equals(""))
                {

                   if(connectionType.equals(Constants.Prepaid_Connection)  || connectionType.equals("Prepaid"))
                    {
                            if (!amount.equals("") && amount.matches("[0-9].+") && amount.length()<=10)
                            {
                                //serviceCallForRechargeCalculationPrepaid();
                            }
                            else
                            {
                                Toast.makeText(context, "Please enter valid amount", Toast.LENGTH_SHORT).show();
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
                if(MApplication.isNetConnected(context)){

                    if(connectionType.equals("Prepaid") || connectionType.equals(Constants.Prepaid_Connection))
                    { if(!remarks.equals(""))
                        {
                            if(typeOfInstallment.equals(Constants.MIGRATION))
                            {
                                serviceCallForRechargeInsert();
                            }
                            else {
                              JsonObject jsonObject = new JsonObject();
                              jsonObject.addProperty("USN_No", usn_num);
                              jsonObject.addProperty("LOAN_EMI_ID", loanEmiId);
                              jsonObject.addProperty("Remarks", remarks);
                              jsonObject.addProperty("PaidBy", paid_by);
                              serviceCallForMeterLoanInsert(jsonObject);
                            }

                        }
                        else {
                            Toast.makeText(context, "Please enter remarks!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
        });
    }

    private void getInput() {
        typeOfInstallment = getIntent().getStringExtra(Constants.INSTALLMENT_TYPE);
        if(typeOfInstallment.equals(Constants.MIGRATION))
        {
            mgrPayemntId = String.valueOf(getIntent().getStringExtra(Constants.MGR_PAYMENT_ID));
            usn_num = MApplication.getString(context, Constants.USN_NUM);

           // Toast.makeText(context, usn_num, Toast.LENGTH_SHORT).show();
            if(!mgrPayemntId.equals("null") && !mgrPayemntId.equals("")){
                serviceCallMigrationInstallmentCalculation();
            }
        }
        else if(typeOfInstallment.equals(Constants.METER_LOAN))
        {
            loanEmiId = String.valueOf(getIntent().getStringExtra(Constants.LOAN_EMI_ID));
            usn_num = MApplication.getString(context, Constants.USN_NUM);
            //Toast.makeText(context, usn_num, Toast.LENGTH_SHORT).show();
            if(!loanEmiId.equals("null") && !loanEmiId.equals("")){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("USN_No", usn_num);
                jsonObject.addProperty("LOAN_EMI_ID", loanEmiId);
                serviceCallMeterLoanCalculation(jsonObject);
            }
        }

    }

    private void init() {
        context = PayInstallmentsActivity.this;
        activity = PayInstallmentsActivity.this;
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
        sv_layout_recharge.setVisibility(View.GONE);
        //sv_review_recharge = findViewById(R.id.sv_review_recharge);
        edt_usn_num = findViewById(R.id.edt_usn_num);
        edt_amount = findViewById(R.id.edt_amount);
        rg_connection_type = findViewById(R.id.rg_connection_type);
        rg_payment_option = findViewById(R.id.rg_payment_option);
        rg_payment_option.setVisibility(View.GONE);
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
        connectionType = MApplication.getString(context, Constants.CONNECTION_TYPE);
    }

    private void setTypeFace() {
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Light.otf");
        edt_paid_by.setTypeface(faceLight);
        edt_comments.setTypeface(faceLight);
        tv_heading_recharge.setTypeface(faceLight);
        tv_btn_recharge_text.setTypeface(faceLight);
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

    private void serviceCallMigrationInstallmentCalculation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);

        Call<JsonElement> call = service.getMeterMigrationCalculation(Integer.parseInt(userId), authorityKey, new InstallmentRechargePostParameters(usn_num , mgrPayemntId));
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

    private void serviceCallMeterLoanCalculation(JsonObject jsonObject) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);

        Call<JsonElement> call = service.getMeterLoanCalculation(Integer.parseInt(userId), authorityKey, jsonObject);
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
            //object.setTaxes(jsonObject.get("Taxes").getAsString());
            rechargeBreakupList.add(object);
        }
        if(rechargeBreakupList.size()>0)
        {
            breakupRcvAdapter = new RechargeBreakupRcvAdapter(context, rechargeBreakupList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcv_payment_breakup.setLayoutManager(linearLayoutManager);
            rcv_payment_breakup.setAdapter(breakupRcvAdapter);
        }

    }

    public void showLoaderNew() {
        runOnUiThread(new PayInstallmentsActivity.Runloader(getResources().getString(R.string.loading)));
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

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);

        Call<RechargeInsertMainObject> call = service.meterMigrationInsert(Integer.parseInt(userId), authorityKey, new InstallmentRechargePostParameters(usn_num, mgrPayemntId, paid_by, remarks));
        call.enqueue(new Callback<RechargeInsertMainObject>() {
            @Override
            public void onResponse(Call<RechargeInsertMainObject> call, Response<RechargeInsertMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                       // Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
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

    private void serviceCallForMeterLoanInsert(JsonObject jsonObject) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);

        Call<RechargeInsertMainObject> call = service.meterLoanInsert(Integer.parseInt(userId), authorityKey, jsonObject);
        call.enqueue(new Callback<RechargeInsertMainObject>() {
            @Override
            public void onResponse(Call<RechargeInsertMainObject> call, Response<RechargeInsertMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
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
        actualRechargeAmount = String.valueOf(result.getUsableAmount());
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
                    if(typeOfInstallment.equals(Constants.MIGRATION))
                    {
                        objectForUpdate.addProperty("mgrPaymentId", mgrPayemntId);
                        serviceCallForRechargeUpdate();
                    }
                    else {
                        objectForUpdate.addProperty("loanEmiId", loanEmiId);
                        serviceCallForMeterLoanUpdate();


                    }
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

    private void serviceCallForRechargeUpdate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);


        Call<RechargeUpdateMainObject> call = service.meterMigrationUpdate(Integer.parseInt(userId),authorityKey, objectForUpdate);
        call.enqueue(new Callback<RechargeUpdateMainObject>() {
            @Override
            public void onResponse(Call<RechargeUpdateMainObject> call, Response<RechargeUpdateMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                     //   Toast.makeText(getBaseContext(), "Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, InstallmentRechargeCompleteActivity.class);
                        intent.putExtra(Constants.RECHARGE_STATUS, true);
                        intent.putExtra(Constants.INSTALLMENT_TYPE, Constants.MIGRATION);
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
                        Intent intent = new Intent(context, InstallmentRechargeCompleteActivity.class);
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

    private void serviceCallForMeterLoanUpdate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstallmentsRechargeInterface service = retrofit.create(InstallmentsRechargeInterface.class);


        Call<RechargeUpdateMainObject> call = service.meterLoanUpdate(Integer.parseInt(userId),authorityKey, objectForUpdate);
        call.enqueue(new Callback<RechargeUpdateMainObject>() {
            @Override
            public void onResponse(Call<RechargeUpdateMainObject> call, Response<RechargeUpdateMainObject> response) {
                hideloader();
                if(response.body()!=null){
                    //Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    if (response.body().getInfo().getErrorCode()==0)
                    {
                       // Toast.makeText(getBaseContext(), "Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, InstallmentRechargeCompleteActivity.class);
                        intent.putExtra(Constants.INSTALLMENT_TYPE, Constants.METER_LOAN);
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
                        Intent intent = new Intent(context, InstallmentRechargeCompleteActivity.class);
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
}
