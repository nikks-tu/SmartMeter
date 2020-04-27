package com.techuva.smartmeter.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.PostpaidRechargeHistoryRcvAdapter;
import com.techuva.smartmeter.adapter.RechargeHistoryRcvAdapter;
import com.techuva.smartmeter.api_interface.PostpaidRechargeHistoryInterface;
import com.techuva.smartmeter.api_interface.RechargeHistoryInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.PostpaidRechargeHistoryParameters;
import com.techuva.smartmeter.postParameters.RechargeHistoryPostParameters;
import com.techuva.smartmeter.responseModel.PostpaidHistoryMainObject;
import com.techuva.smartmeter.responseModel.PostpaidHistoryResultObject;
import com.techuva.smartmeter.responseModel.RechargeHistoryMainObject;
import com.techuva.smartmeter.utils.MApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PostpaidRechargeHistory extends AppCompatActivity {

    TextView tv_bill_date, tv_unit_consumed, tv_bill_amt, tv_due_date, tv_status, tv_no_data, tv_prev_btn, tv_next_btn;
    RecyclerView rcv_bill_history;
    Context context;
    LinearLayout ll_back_btn, ll_bottom_bar,ll_prev, ll_next;
    FrameLayout fl_bill_history;
    String pagePerCount="12";
    String usn, userId;
    int pageNumber=1;
    String accessToken;
    ArrayList<PostpaidHistoryResultObject> resultObjectArrayList;
    PostpaidRechargeHistoryRcvAdapter rechargeHistoryRcvAdapter;
    PostpaidHistoryResultObject postpaidHistoryResultObject;
    public Dialog dialog;
    int listCount, toRecords;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postpaid_recharge_history);
        init();

        ll_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rcv_bill_history.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv_bill_history, new RecyclerItemClickListener.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int position) {
                String i = String.valueOf(rechargeHistoryRcvAdapter.getItem(position));
                MApplication.setString(context, Constants.POSTPAID_BILLING_ID, i);
                //Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PostpaidBillDetailsViewActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        tv_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber>=1){
                    pageNumber = pageNumber+1;
                    //Toast.makeText(context, ""+pageNumber, Toast.LENGTH_SHORT).show();
                    serviceCall();
                }
            }
        });

        tv_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber>1)
                {
                    pageNumber = pageNumber-1;
                    //Toast.makeText(context, ""+pageNumber, Toast.LENGTH_SHORT).show();
                    serviceCall();
                }
            }
        });

    }

    private void init() {
        context = PostpaidRechargeHistory.this;/*
        TextView textView = findViewById(R.id.page_heading);
        textView.setText(getResources().getString(R.string.your_active_onnection));*/
        tv_bill_date = findViewById(R.id.tv_bill_date);
        tv_unit_consumed = findViewById(R.id.tv_unit_consumed);
        tv_bill_amt = findViewById(R.id.tv_bill_amt);
        tv_due_date = findViewById(R.id.tv_due_date);
        tv_status = findViewById(R.id.tv_status);
        tv_no_data = findViewById(R.id.tv_no_data);
        tv_prev_btn = findViewById(R.id.tv_prev_btn);
        tv_next_btn = findViewById(R.id.tv_next_btn);
        ll_prev = findViewById(R.id.ll_prev);
        ll_next = findViewById(R.id.ll_next);
        ll_prev.setVisibility(View.GONE);
        ll_next.setVisibility(View.GONE);
        ll_back_btn = findViewById(R.id.ll_back_btn);
        ll_bottom_bar = findViewById(R.id.ll_bottom_bar);
        rcv_bill_history = findViewById(R.id.rcv_bill_history);
        fl_bill_history = findViewById(R.id.fl_bill_history);
        usn = MApplication.getString(context, Constants.USN_NUM);
        userId = MApplication.getString(context, Constants.UserID);
        accessToken = "Bearer "+MApplication.getString(context, Constants.AccessToken);
        pageNumber = 1;
        pagePerCount="12";
       // tv_prev_btn.setBackgroundColor(getResources().getColor(R.color.App_Grey));
        if(MApplication.isNetConnected(context)){
            serviceCall();
        }else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
       // serviceCall();
    }


    private void serviceCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostpaidRechargeHistoryInterface service = retrofit.create(PostpaidRechargeHistoryInterface .class);
        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), accessToken ,new PostpaidRechargeHistoryParameters(usn, pagePerCount, String.valueOf(pageNumber)));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.body()!=null){
                   // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    String errorMsg = infoObject.get("errorMessage").getAsString();
                    if (errorCode==0)
                    {
                        JsonArray resultArray = jsonObject.get("result").getAsJsonArray();

                        listCount = infoObject.get("totalRecords").getAsInt();
                        toRecords = infoObject.get("toRecords").getAsInt();
                         if(toRecords<listCount)
                        {
                            ll_next.setVisibility(View.VISIBLE);
                        }
                        else {
                            ll_next.setVisibility(View.GONE);
                        }
                        if(pageNumber>1)
                        {
                            ll_prev.setVisibility(View.VISIBLE);
                        }
                        else {
                            ll_prev.setVisibility(View.GONE);
                        }
                        resultObjectArrayList = new ArrayList<>();
                        for (int i=0; i<resultArray.size(); i++)
                        {
                            postpaidHistoryResultObject = new PostpaidHistoryResultObject();
                            JsonObject resultObject = resultArray.get(i).getAsJsonObject();
                            postpaidHistoryResultObject.setPOSTPAIDBILLINGID(resultObject.get("POSTPAID_BILLING_ID").getAsInt());
                            postpaidHistoryResultObject.setUSNNO(resultObject.get("USN_NO").getAsString());
                            postpaidHistoryResultObject.setUNITSCONSUMED(resultObject.get("UNITS_CONSUMED").getAsInt());
                            postpaidHistoryResultObject.setBILLINGAMOUNT(resultObject.get("BILLING_AMOUNT").getAsDouble());
                            postpaidHistoryResultObject.setBILLINGDATE(resultObject.get("BILLING_DATE").getAsString());
                            postpaidHistoryResultObject.setDUEDATE(resultObject.get("DUE_DATE").getAsString());
                            postpaidHistoryResultObject.setPAIDSTATUS(resultObject.get("PAID_STATUS").getAsString());
                            postpaidHistoryResultObject.setISACTIVE(resultObject.get("IS_ACTIVE").getAsBoolean());
                            postpaidHistoryResultObject.setCREATEDON(resultObject.get("CREATED_ON").getAsString());
                            postpaidHistoryResultObject.setCREATEDBY(resultObject.get("CREATED_BY").getAsInt());
                            postpaidHistoryResultObject.setLASTMODIFIEDON(resultObject.get("LAST_MODIFIED_ON").getAsString());
                            postpaidHistoryResultObject.setLASTMODIFIEDBY(resultObject.get("LAST_MODIFIED_BY").getAsInt());
                            if(resultObject.has("BILL_LINK") && !resultObject.get("BILL_LINK").isJsonNull()){
                               postpaidHistoryResultObject.setBILL_LINK(resultObject.get("BILL_LINK").getAsString());
                            }
                            else{
                                postpaidHistoryResultObject.setBILL_LINK("");
                            }
                            if(resultObject.has("RECHARGE_PAYMENT_ID") && !resultObject.get("RECHARGE_PAYMENT_ID").isJsonNull()){
                                postpaidHistoryResultObject.setRECHARGEPAYMENTID(resultObject.get("RECHARGE_PAYMENT_ID").getAsInt());
                            }
                            else {
                                postpaidHistoryResultObject.setRECHARGEPAYMENTID(0);
                            }
                            if(resultObject.has("FROM_DATE") && !resultObject.get("FROM_DATE").isJsonNull()){
                                postpaidHistoryResultObject.setFROMDATE(resultObject.get("FROM_DATE").getAsString());
                            }
                            else {
                                postpaidHistoryResultObject.setFROMDATE("");
                            }
                            postpaidHistoryResultObject.setTODATE(resultObject.get("TO_DATE").getAsString());
                            postpaidHistoryResultObject.setEMAILSENT(resultObject.get("EMAIL_SENT").getAsInt());
                            resultObjectArrayList.add(postpaidHistoryResultObject);
                        }
                        tv_no_data.setVisibility(View.GONE);
                        rcv_bill_history.setVisibility(View.VISIBLE);
                        //fl_bill_history.setVisibility(View.VISIBLE);
                        dataResponse(resultObjectArrayList );
                        rechargeHistoryRcvAdapter = new PostpaidRechargeHistoryRcvAdapter(context, resultObjectArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        rcv_bill_history.setLayoutManager(linearLayoutManager);
                        rcv_bill_history.setAdapter(rechargeHistoryRcvAdapter);
                    }
                    else
                    {
                        tv_no_data.setVisibility(View.VISIBLE);
                        tv_no_data.setText(errorMsg);
                        rcv_bill_history.setVisibility(View.GONE);
                        //fl_bill_history.setVisibility(View.GONE);
                    }


                }else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    rcv_bill_history.setVisibility(View.GONE);
                    //fl_bill_history.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
                tv_no_data.setVisibility(View.VISIBLE);
                rcv_bill_history.setVisibility(View.GONE);
                //fl_bill_history.setVisibility(View.GONE);
            }

        });

    }

    private void dataResponse(ArrayList<PostpaidHistoryResultObject> resultObjectArrayList) {


    }


    public void showLoaderNew() {
        runOnUiThread(new PostpaidRechargeHistory.Runloader(getResources().getString(R.string.loading)));
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

                ImageView imgeView = dialog
                        .findViewById(R.id.imgeView);
                TextView tvLoading = dialog
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
        runOnUiThread(() -> {
            try {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }); }


    @Override
    public void onBackPressed() {
        MApplication.setBoolean(context, Constants.IsHomeSelected, false);
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
