package com.techuva.smartmeter.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.ConsumedDataHistoryAdapter;
import com.techuva.smartmeter.adapter.PostpaidBreakupRcvAdapter;
import com.techuva.smartmeter.adapter.PostpaidDataHistoryAdapter;
import com.techuva.smartmeter.adapter.RechargeBreakupRcvAdapter;
import com.techuva.smartmeter.api_interface.GetPostpaidBillDetailsInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PostpaidBillDetailPostParameters;
import com.techuva.smartmeter.responseModel.ConsumedDataHistoryObject;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;
import com.techuva.smartmeter.utils.FileDownloader;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.UIUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class PostpaidBillDetailsViewActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    String userId="";
    String authorityKey ="";
    LinearLayout ll_back_btn, ll_download, ll_history, ll_down, ll_units, ll_payment, ll_no_units;
    TextView tv_bill_heading, tv_bill_id_txt, tv_bill_id, tv_bill_date, tv_bill_from, tv_download, tv_no_units;
    TextView tv_bill_amt, tv_bill_status_txt, tv_bill_status, tv_toolbar_heading;
    TextView tv_bill_due_txt, tv_bill_due, tv_bill_total_units_txt, tv_bill_total_units;
    TextView tv_bill_payment_ref_txt, tv_bill_payment_ref, tv_bill_payment_mode_txt, tv_bill_payment_mode;
    TextView tv_bill_breakup, tv_date, tv_units_history, tv_consumed_units, tv_amount, tv_kwh, tv_unit_rate, tv_nodata;
    TextView tv_bill_payment_mode_by_txt, tv_bill_payment_mode_by;
    TextView tv_bill_payment_billing_address, tv_bill_payment_billing_address_txt;
    TextView tv_usn_txt, tv_usn_num;
    HorizontalScrollView hv_units;
    RecyclerView rcv_payment_breakup;
    ListView lv_consumed_data;
    String recharge_payment_id ="", currency;
    PostpaidBreakupRcvAdapter breakupRcvAdapter;
    ArrayList<RechargeCalBreakupObject> rechargeBreakupList;
    RechargeCalBreakupObject breakupObject;
    String billing_id, billAmount, status, unitsConsumed, paymentRef, paymentMode;
    Date billedFrom, billed_to, dueDate, billedDate;
    PostpaidDataHistoryAdapter historyAdapter;
    ArrayList<ConsumedDataHistoryObject> historyList;
    ConsumedDataHistoryObject historyObject;
    String transactionDate, amount, unitRate, transType, kwh, unitConsume, billUrl="", billingAddress, paymentType;
    Boolean isListShowing = false;
    Boolean isFirstClick = false;
    public Dialog dialog;
    private AnimationDrawable animationDrawable;
    private ProgressDialog pDialog;
    public  String  path = Environment.getExternalStorageDirectory().getAbsolutePath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postpaid_bill_details_view);
        init();
        ll_back_btn.setOnClickListener(v -> finish());
      /*  ll_down.setOnClickListener(v -> {

            if (isFirstClick)
            {
                isListShowing = true;
            }
            if(isListShowing)
            {
                hv_units.setVisibility(View.VISIBLE);
            }
            else {
                hv_units.setVisibility(View.GONE);
            }
        });*/
        tv_download.setOnClickListener(v -> {
         // Toast.makeText(context, "Click "+billUrl, Toast.LENGTH_SHORT).show();
          if(!billUrl.equals(""))
          {
              pDialog = new ProgressDialog(this);
              pDialog.setMessage("Please wait...");
              pDialog.setCancelable(false);
              downloadPDF(billUrl);
              //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(billUrl)));
          }
      });
    }

    private void init() {
        context = PostpaidBillDetailsViewActivity.this;
        userId = MApplication.getString(context, Constants.UserID);
        authorityKey = "Bearer " +MApplication.getString(context, Constants.AccessToken);
        isFirstClick = true;
        ll_back_btn = findViewById(R.id.ll_back_btn);
        ll_download = findViewById(R.id.ll_download);
        ll_history = findViewById(R.id.ll_history);
        ll_down = findViewById(R.id.ll_down);
        tv_toolbar_heading = findViewById(R.id.tv_toolbar_heading);
        tv_bill_heading = findViewById(R.id.tv_bill_heading);
        tv_bill_id_txt = findViewById(R.id.tv_bill_id_txt);
        tv_bill_id = findViewById(R.id.tv_bill_id);
        tv_bill_date = findViewById(R.id.tv_bill_date);
        tv_bill_amt = findViewById(R.id.tv_bill_amt);
        tv_bill_from = findViewById(R.id.tv_bill_from);
        tv_download = findViewById(R.id.tv_download);
        tv_no_units = findViewById(R.id.tv_no_units);
        tv_bill_status_txt = findViewById(R.id.tv_bill_status_txt);
        tv_bill_status = findViewById(R.id.tv_bill_status);
        tv_bill_due_txt = findViewById(R.id.tv_bill_due_txt);
        tv_bill_due = findViewById(R.id.tv_bill_due);
        tv_bill_total_units_txt = findViewById(R.id.tv_bill_total_units_txt);
        tv_bill_total_units = findViewById(R.id.tv_bill_total_units);
        tv_bill_payment_ref_txt = findViewById(R.id.tv_bill_payment_ref_txt);
        tv_bill_payment_ref = findViewById(R.id.tv_bill_payment_ref);
        tv_bill_payment_mode_txt = findViewById(R.id.tv_bill_payment_mode_txt);
        tv_bill_payment_mode = findViewById(R.id.tv_bill_payment_mode);
        tv_bill_breakup = findViewById(R.id.tv_bill_breakup);
        tv_bill_payment_mode_by_txt = findViewById(R.id.tv_bill_payment_mode_by_txt);
        tv_bill_payment_mode_by = findViewById(R.id.tv_bill_payment_mode_by);
        tv_bill_payment_billing_address = findViewById(R.id.tv_bill_payment_billing_address);
        tv_bill_payment_billing_address_txt = findViewById(R.id.tv_bill_payment_billing_address_txt);
        tv_date = findViewById(R.id.tv_date);
        tv_units_history = findViewById(R.id.tv_units_history);
        tv_consumed_units = findViewById(R.id.tv_consumed_units);
        tv_amount = findViewById(R.id.tv_amount);
        tv_kwh = findViewById(R.id.tv_kwh);
        tv_unit_rate = findViewById(R.id.tv_unit_rate);
        tv_nodata = findViewById(R.id.tv_nodata);
        tv_usn_txt = findViewById(R.id.tv_usn_txt);
        tv_usn_num = findViewById(R.id.tv_usn_num);
        hv_units = findViewById(R.id.hv_units);
        ll_units = findViewById(R.id.ll_units);
        ll_payment = findViewById(R.id.ll_payment);
        ll_no_units = findViewById(R.id.ll_no_units);
        rcv_payment_breakup = findViewById(R.id.rcv_payment_breakup);
        lv_consumed_data = findViewById(R.id.lv_consumed_data);
        // Set Fonts
        Typeface faceLight = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceMedium = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        tv_bill_heading.setTypeface(faceLight);
        tv_bill_id_txt.setTypeface(faceMedium);
        tv_bill_id.setTypeface(faceLight);
        tv_bill_date.setTypeface(faceLight);
        tv_bill_from.setTypeface(faceLight);
        tv_bill_amt.setTypeface(faceMedium);
        tv_bill_status_txt.setTypeface(faceMedium);
        tv_download.setTypeface(faceLight);
        tv_bill_status.setTypeface(faceLight);
        tv_bill_due_txt.setTypeface(faceMedium);
        tv_bill_due.setTypeface(faceLight);
        tv_bill_total_units_txt.setTypeface(faceMedium);
        tv_bill_total_units.setTypeface(faceLight);
        tv_bill_payment_ref_txt.setTypeface(faceMedium);
        tv_bill_payment_ref.setTypeface(faceLight);
        tv_no_units.setTypeface(faceLight);
        tv_bill_payment_mode_txt.setTypeface(faceMedium);
        tv_bill_payment_mode.setTypeface(faceLight);
        tv_bill_breakup.setTypeface(faceLight);
        tv_bill_payment_mode_by.setTypeface(faceLight);
        tv_bill_payment_mode_by_txt.setTypeface(faceMedium);
        tv_bill_payment_billing_address_txt.setTypeface(faceMedium);
        tv_bill_payment_billing_address.setTypeface(faceLight);
        tv_date.setTypeface(faceLight);
        tv_units_history.setTypeface(faceLight);
        tv_consumed_units.setTypeface(faceLight);
        tv_amount.setTypeface(faceLight);
        tv_kwh.setTypeface(faceLight);
        tv_unit_rate.setTypeface(faceLight);
        tv_usn_txt.setTypeface(faceMedium);
        tv_usn_num.setTypeface(faceLight);
        tv_nodata.setTypeface(faceLight);
        tv_nodata.setVisibility(View.GONE);
        recharge_payment_id = MApplication.getString(context, Constants.POSTPAID_BILLING_ID);

        if(MApplication.isNetConnected(context)){
            serviceCallForGettingBillDetails();
        }else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
       // serviceCallForGettingBillDetails();
    }


    private void serviceCallForGettingBillDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPostpaidBillDetailsInterface service = retrofit.create(GetPostpaidBillDetailsInterface.class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new PostpaidBillDetailPostParameters(Integer.parseInt(recharge_payment_id)));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){

                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();

                    if (errorCode==0)
                    {
                        JsonObject result = jsonObject.get("result").getAsJsonObject();
                        // Toast.makeText(context, response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();
                        dataResponseBillDetails(result);
                    }
                    else if(errorCode==1)
                    {
                        Toast.makeText(getBaseContext(), infoObject.get("errorMessage").getAsString(),Toast.LENGTH_SHORT).show();
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

    private void dataResponseBillDetails(JsonObject resultObject) {

        JsonObject result = resultObject.get("PostPaidBillData").getAsJsonObject();
        JsonObject paymentObject = resultObject.get("PaymentDetails").getAsJsonObject();
       if(resultObject.has("UnitsConsumptionHistory"))
       {
           JsonArray unitsArray = resultObject.get("UnitsConsumptionHistory").getAsJsonArray();
           //Toast.makeText(context, ""+unitsArray.size(), Toast.LENGTH_SHORT).show();
           if(unitsArray.size()>0)
           {
               historyList = new ArrayList<>();
               for (int a=0; a<unitsArray.size(); a++)
               {
                   JsonObject unitsObject = unitsArray.get(a).getAsJsonObject();
                   transactionDate= unitsObject.get("TRANSACTION_DATE").getAsString();
                   amount =unitsObject.get("AMOUNT").getAsString();
                   unitRate= unitsObject.get("UNIT_RATE").getAsString();
                   transType = unitsObject.get("TRANSACTION_TYPE").getAsString();
                   kwh = unitsObject.get("KWH").getAsString();
                   unitConsume= unitsObject.get("UNITS_CONSUMED").getAsString();

                   if(!transactionDate.equals(""))
                   {
                       historyObject = new ConsumedDataHistoryObject();
                       MApplication.setBoolean(context, Constants.From_Bill_History, true);
                       //hv_units.setVisibility(View.VISIBLE);
                       ll_units.setVisibility(View.VISIBLE);
                       historyObject.setTRANSACTIONDATE(transactionDate);
                       historyObject.setAMOUNT(amount);
                       historyObject.setUNITRATE(unitRate);
                       historyObject.setTRANSACTIONTYPE(transType);
                       historyObject.setKWH(kwh);
                       historyObject.setUNITSCONSUMED(unitConsume);
                       historyList.add(historyObject);
                   }
               }
               if(historyList.size()>0)
               {
                   ll_units.setVisibility(View.VISIBLE);
                   ll_no_units.setVisibility(View.GONE);
                   historyAdapter = new PostpaidDataHistoryAdapter(R.layout.item_consumed_units_values, context, historyList);
                   lv_consumed_data.setAdapter(historyAdapter);
                   UIUtils.setListViewHeightBasedOnItems(lv_consumed_data);
               }
               else {
                   ll_units.setVisibility(View.GONE);
                   ll_no_units.setVisibility(View.VISIBLE);
               }
           }
       }
       //2019-04-22 00:00:00.0



        if (result.has("BILLING_ADDRESS"))
        {
            billingAddress = result.get("BILLING_ADDRESS").getAsString();
            tv_bill_payment_billing_address.setText(billingAddress);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.s");
        Date date = null;
        try {

            billedDate = sdf.parse(result.get("BILLING_DATE").getAsString());
            billedFrom = sdf.parse(result.get("FROM_DATE").getAsString());
            billed_to = sdf.parse(result.get("TO_DATE").getAsString());
            dueDate = sdf.parse(result.get("DUE_DATE").getAsString());
           // date = sdf.parse(model.getBILLINGDATE());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat full_month = new SimpleDateFormat("MMMM", Locale.US);
        billing_id = result.get("POSTPAID_BILLING_ID").getAsString();
        if(billing_id.length()!=6)
        {
            int length = 6-billing_id.length();

            for (int i=0; i<length; i++)
            {
                billing_id = "0"+billing_id;
            }
        }


        tv_bill_id.setText("# "+billing_id);
        tv_bill_date.setText("Your Bill was generated on "+sdf1.format(billedDate));
        tv_toolbar_heading.setText(full_month.format(billedFrom)+" Bill");
        tv_bill_from.setText(sdf1.format(billedFrom) +" to "+ sdf1.format(billed_to));
        billAmount =context.getResources().getString(R.string.currency)+" "+ result.get("BILLING_AMOUNT").getAsString();
        tv_bill_amt.setText(billAmount);
        tv_usn_num.setText(result.get("USN_NO").getAsString());
        String units = result.get("UNITS_CONSUMED").getAsString();
        status = result.get("DESCRIPTION").getAsString();
        if(status.equals("Paid") && !units.equals("0"))
        {
            ll_payment.setVisibility(View.VISIBLE);
        }
        else {
            ll_payment.setVisibility(View.GONE);
        }
        tv_bill_status.setText(status);
        tv_bill_due.setText(sdf1.format(dueDate));
        unitsConsumed = result.get("UNITS_CONSUMED").getAsString()+" "+ context.getResources().getString(R.string.units);
        tv_bill_total_units.setText(unitsConsumed);
        billUrl = result.get("BILL_LINK").getAsString();

        if(paymentObject!=null)
        {
            if(paymentObject.has("CURRENCY"))
            {
                currency = paymentObject.get("CURRENCY").getAsString();
            }
            if (paymentObject.has("TRANS_REF"))
            {
                paymentRef = paymentObject.get("TRANS_REF").getAsString();
                tv_bill_payment_ref.setText("# "+paymentRef);
            }
            if(paymentObject.has("RECHARGE_DATE"))
            {

                try {
                    Date paid_on = sdf.parse(paymentObject.get("RECHARGE_DATE").getAsString());
                    tv_bill_payment_mode.setText(sdf1.format(paid_on));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            if (paymentObject.has("PAYMENT_DESCRIPTION"))
            {
                paymentType = paymentObject.get("PAYMENT_DESCRIPTION").getAsString();
                tv_bill_payment_mode_by.setText(paymentType);
            }
        }

        rechargeBreakupList = new ArrayList<>();
        JsonArray jsonArray = result.get("breakup").getAsJsonArray();
        for (int i=0 ; i<jsonArray.size(); i++)
        {
            breakupObject = new RechargeCalBreakupObject();

            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            breakupObject.setBreakupDesc(jsonObject.get("BREAKUP_DESC").getAsString());
            breakupObject.setBreakupAmount(jsonObject.get("BREAKUP_AMOUNT").getAsString());
            breakupObject.setBreakupRate(jsonObject.get("BREAKUP_RATE").getAsDouble());
            rechargeBreakupList.add(breakupObject);
        }

        breakupRcvAdapter = new PostpaidBreakupRcvAdapter(context, rechargeBreakupList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_payment_breakup.setLayoutManager(linearLayoutManager);
        rcv_payment_breakup.setAdapter(breakupRcvAdapter);


      /*  else {
            historyAdapter.notifyDataSetChanged();
        }*/
    }


    public void showLoaderNew() {
        runOnUiThread(new PostpaidBillDetailsViewActivity.Runloader(getResources().getString(R.string.loading)));
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


    public void downloadPDF(String url)
    {
        new DownloadFile().execute(url, "TSM_Postpaid_Bill.pdf");

    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];    String fileName = strings[1];
            File folder = new File(path, "TSM");
            if(isFileExist())
            {
                File file = new File(path+"/TSM",  "/TSM_Postpaid_Bill.pdf");
                if(file.exists()){
                    file.delete();
                }
                File pdfFile = new File(folder, fileName);

                try{
                    pdfFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                FileDownloader.downloadFile(fileUrl, pdfFile);
                return null;
            }else
            {
                folder.mkdir();

                File pdfFile = new File(folder, fileName);

                try{
                    pdfFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                FileDownloader.downloadFile(fileUrl, pdfFile);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hidepDialog();
            //Toast.makeText(getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();
            if(Build.VERSION.SDK_INT>=24){
                try{
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);
                    File filePath = new File(path+"/TSM" +
                            ""+"/TSM_Postpaid_Bill.pdf");
                    Uri path = Uri.fromFile(filePath);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(path , "application/pdf");
                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    try
                    {
                        startActivity(pdfIntent ); }
                    catch (ActivityNotFoundException e)
                    {
                        Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Download complete", "----------");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else {
                File filePath = new File(path+"/TSM" +
                        ""+"/TSM_Postpaid_Bill.pdf");
                Uri path = Uri.fromFile(filePath);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path , "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try
                {
                    startActivity(pdfIntent ); }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                }
                Log.d("Download complete", "----------");
            }

        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public boolean isFileExist()
    {
        File Dir = new File(path+"/TSM");
        //  File file = new File(Dir,"nahk.txt");

        File file = new File(Dir,  "/TSM_Postpaid_Bill.pdf");

        if(file.exists()){
            return true;
        }
        else {
            return false;
        }
    }

}
