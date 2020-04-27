package com.techuva.smartmeter.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.utils.MApplication;

public class InstallmentRechargeCompleteActivity extends AppCompatActivity {

    TextView tv_heading, tv_recharge_status, tv_recharge_msg, tv_payemnt_ref, tv_amount, tv_recharge_date, tv_payment_method, tv_recharge_again;
    TextView tv_payemnt_ref_txt, tv_amount_txt, tv_recharge_date_txt, tv_payment_method_txt;
    ImageView iv_recharge_icon;
    LinearLayout ll_recharge_again, ll_success, ll_failed, ll_layout_main, ll_recharge_details, ll_back_btn;
    Context context;
    Boolean status;
    String typeOfInstallment="";
    String rechargeAmount="";
    String currency="";
    String paymentRefNum="";
    String rechargeDate="";
    String paymentMethod="";
    String rechargeStatusMsg ="";
    Boolean from_card;
    Boolean fromScratchCard=false;
    String connectionType="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_complete);
        init();
        status = getIntent().getBooleanExtra(Constants.RECHARGE_STATUS, false);
        from_card = getIntent().getBooleanExtra(Constants.From_Card, false);
        fromScratchCard = MApplication.getBoolean(context, Constants.IsFromRechargeCard);
        ll_recharge_again.setVisibility(View.GONE);
        if (status)
        {
            ll_failed.setVisibility(View.GONE);
            ll_success.setVisibility(View.VISIBLE);
            tv_heading.setText("Payment Complete");
        }
        else {
            ll_success.setVisibility(View.GONE);
            ll_failed.setVisibility(View.VISIBLE);
            tv_heading.setText("Payment Failed");
        }
        if(from_card)
        {
            ll_success.setVisibility(View.VISIBLE);
        }

        typeOfInstallment = getIntent().getStringExtra(Constants.INSTALLMENT_TYPE);
        rechargeAmount = getIntent().getStringExtra(Constants.Actual_Recharge_Amount);
        currency = getIntent().getStringExtra(Constants.CURRENCY_OF_RECHARGE);
        paymentRefNum  = getIntent().getStringExtra(Constants.PAYMENT_REF_NUM);
        rechargeDate = getIntent().getStringExtra(Constants.DATE_OF_RECHARGE);
        paymentMethod = getIntent().getStringExtra(Constants.PAYMENT_METHOD);

        if(status){
        /* if(fromScratchCard){
                rechargeStatusMsg = getResources().getString(R.string.recharge_success_card);
                ll_recharge_details.setVisibility(View.GONE);
            }
         else {
             rechargeStatusMsg = String.format(getResources().getString(R.string.recharge_success),currency, rechargeAmount);
         }*/
        if(typeOfInstallment.equals(Constants.MIGRATION))
        {
            rechargeStatusMsg = "Your Payment for Schedule is successful";
        }
        else {
            rechargeStatusMsg =  "Your Payment for Meter Installment is successful";
        }
        }

        else {
        //    rechargeStatusMsg = String.format(getResources().getString(R.string.recharge_failed),currency, rechargeAmount);

            if (typeOfInstallment.equals(Constants.MIGRATION))
            {
                rechargeStatusMsg = "Your Payment for Schedule Failed";
            }
            else {
                rechargeStatusMsg =  "Your Payment for Meter Installment Failed";
            }
        }

        tv_recharge_msg.setText(rechargeStatusMsg);
        tv_payemnt_ref.setText(paymentRefNum);
        tv_amount.setText(rechargeAmount);
        tv_recharge_date.setText(rechargeDate);
        tv_payment_method.setText(paymentMethod);

        ll_recharge_again.setOnClickListener(v -> {

            if(MApplication.isNetConnected(context)){
                clearRechargeData();
                Intent intent = new Intent(context, RechargeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else {
                Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }

        });

        ll_back_btn.setOnClickListener(v -> {
            MApplication.setBoolean(context, Constants.IsHomeSelected, true);
            Intent intent = new Intent(context, UserAccountsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void clearRechargeData()  {
        MApplication.setString(context, Constants.RECHARGE_AMOUNT, "");
        MApplication.setString(context, Constants.CURRENCY_OF_RECHARGE, "");
        MApplication.setString(context, Constants.PAYMENT_REF_NUM, "");
        MApplication.setString(context, Constants.DATE_OF_RECHARGE, "");
        MApplication.setString(context, Constants.PAYMENT_METHOD, "");
    }
    private void init() {
        context = InstallmentRechargeCompleteActivity.this;
        tv_heading = findViewById(R.id.tv_heading);
        tv_recharge_status = findViewById(R.id.tv_recharge_status);
        tv_recharge_msg = findViewById(R.id.tv_recharge_msg);
        tv_payemnt_ref_txt = findViewById(R.id.tv_payemnt_ref_txt);
        tv_amount_txt = findViewById(R.id.tv_amount_txt);
        tv_recharge_date_txt = findViewById(R.id.tv_recharge_date_txt);
        tv_payment_method_txt = findViewById(R.id.tv_payment_method_txt);
        tv_payemnt_ref = findViewById(R.id.tv_payemnt_ref);
        tv_amount = findViewById(R.id.tv_amount);
        tv_recharge_date = findViewById(R.id.tv_recharge_date);
        tv_payment_method = findViewById(R.id.tv_payment_method);
        tv_recharge_again = findViewById(R.id.tv_recharge_again);
        iv_recharge_icon = findViewById(R.id.iv_recharge_icon);
        ll_recharge_again = findViewById(R.id.ll_recharge_again);
        ll_success = findViewById(R.id.ll_success);
        ll_failed = findViewById(R.id.ll_failed);
        ll_layout_main = findViewById(R.id.ll_layout_main);
        ll_recharge_details = findViewById(R.id.ll_recharge_details);
        ll_back_btn = findViewById(R.id.ll_back_btn);
        connectionType = MApplication.getString(context, Constants.CONNECTION_TYPE);
        if (connectionType.equals(Constants.Postpaid_Connection) || connectionType.equals("Postpaid"))
        {
            ll_recharge_again.setVisibility(View.GONE);
        }
        else {
            ll_recharge_again.setVisibility(View.VISIBLE);
        }
            setTypeface();
    }


    private void setTypeface() {
        Typeface faceLight = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceMedium = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        tv_heading.setTypeface(faceLight);
        tv_recharge_status.setTypeface(faceLight);
        tv_recharge_msg.setTypeface(faceLight);
        tv_payemnt_ref.setTypeface(faceLight);
        tv_amount.setTypeface(faceLight);
        tv_recharge_date.setTypeface(faceLight);
        tv_payment_method.setTypeface(faceLight);
        tv_recharge_again.setTypeface(faceLight);
        tv_payemnt_ref_txt.setTypeface(faceLight);
        tv_amount_txt.setTypeface(faceLight);
        tv_recharge_date_txt.setTypeface(faceLight);
        tv_payment_method_txt.setTypeface(faceLight);

    }

    @Override
    public void onBackPressed() {
        clearRechargeData();
        MApplication.setBoolean(context, Constants.IsHomeSelected, false);
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
