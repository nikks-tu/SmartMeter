package com.techuva.smartmeter.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.PostpaidBreakupRcvAdapter;
import com.techuva.smartmeter.api_interface.GetPrepaidBillDetailsInterface;
import com.techuva.smartmeter.api_interface.PrepaidRechargeInvoiceDownloadInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.PrepaidBillDetailPostParameters;
import com.techuva.smartmeter.postParameters.PrepaidInvoiceDownloadPostParamter;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;
import com.techuva.smartmeter.utils.MApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PrepaidBillDetailsViewActivity extends AppCompatActivity {

    Context context;
    LinearLayout ll_back_btn, ll_download, ll_details, ll_recharge_date;
    TextView tv_recharge_date, tv_recharge_date_txt, tv_bill_amt, tv_tx_details , tv_usable_amt_txt, tv_usable_amt;
    TextView tv_bill_num_txt, tv_bill_num, tv_mode_txt, tv_mode, tv_status_txt, tv_status, tv_fixed_charges_txt;
    TextView tv_fixed_charges, tv_vat_txt, tv_vat, tv_paid_amt_txt, tv_paid_amt, tv_download;
    TextView tv_paid_by_txt, tv_paid_by, tv_payment_ref_txt, tv_payment_ref,  tv_usn_txt, tv_usn;
    String userId="";
    String authorityKey ="";
    String recharge_payment_id ="";
    String rechargeAmount, fixedCharges, status, paymentDescription, rechargeDate, usableAmount, serviceCharge;
    String payment_ref_num, usn_num, paid_by;
    String vat, lifeSaver, tranRef, rechargeUsableAmt;
    String DOWNLOADS_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName="" ;
    RecyclerView rcv_payment_breakup;
    ArrayList<RechargeCalBreakupObject> rechargeBreakupList;
    RechargeCalBreakupObject breakupObject;
    PostpaidBreakupRcvAdapter breakupRcvAdapter;
    public  String  path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/TSM";
    public Dialog dialog;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepaid_bill_details_view);
        init();

        ll_back_btn.setOnClickListener(v -> {
            MApplication.setString(context, Constants.PREPAID_RECHARGE_ID, "");
            onBackPressed();
        });

        tv_download.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               if(MApplication.isNetConnected(context)){
                                                   serviceCallforInvoiceDownload();
                                               }else {
                                                   Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });

    }

    private void serviceCallforInvoiceDownload() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PrepaidRechargeInvoiceDownloadInterface service = retrofit.create(PrepaidRechargeInvoiceDownloadInterface.class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new PrepaidInvoiceDownloadPostParamter(tranRef));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                hideloader();
                if(response.body()!=null){
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    if (errorCode==0)
                    {
                        //Toast.makeText(getBaseContext(), infoObject.get("errorMessage").getAsString(),Toast.LENGTH_SHORT).show();
                        JsonObject resultObject = jsonObject.get("result").getAsJsonObject();
                        if (resultObject.has("invoice"))
                        {
                            String invoice_url= resultObject.get("invoice").getAsString();
                            File mFolder = new File(path);
                            if (!mFolder.exists()) {
                                mFolder.mkdir();

                                if(!isFileExist())
                                {
                                    File filePath = new File(path+"/TSM_Recharge_Invoice.pdf");
                                    byte[] pdfAsBytes = Base64.decode(invoice_url,  0);

                                    FileOutputStream os = null;
                                    try {
                                        os = new FileOutputStream(filePath, true);
                                        os.write(pdfAsBytes);
                                        os.flush();
                                        os.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //Toast.makeText(getBaseContext(), "Your Invoice has been downloaded",Toast.LENGTH_SHORT).show();
                                    Uri path = Uri.fromFile(filePath);
                                    if(Build.VERSION.SDK_INT>=24){
                                        try{
                                            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                                            m.invoke(null);
                                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                            pdfIntent.setDataAndType(path , "application/pdf");
                                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            try
                                            {
                                                startActivity(pdfIntent ); }
                                            catch (ActivityNotFoundException e)
                                            {
                                                Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                            }
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        pdfIntent.setDataAndType(path , "application/pdf");
                                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        try
                                        {
                                            startActivity(pdfIntent ); }
                                        catch (ActivityNotFoundException e)
                                        {
                                            Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                }
                            }else {
                                File Dir = new File(path);
                                File file = new File(Dir,  "/TSM_Recharge_Invoice.pdf");
                                if(file.exists()){
                                    file.delete();
                                    byte[] pdfAsBytes = Base64.decode(invoice_url,  0);
                                    File filePath = new File(path+"/TSM_Recharge_Invoice.pdf");
                                    FileOutputStream os = null;
                                    try {
                                        os = new FileOutputStream(filePath, true);
                                        os.write(pdfAsBytes);
                                        os.flush();
                                        os.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    // Toast.makeText(getBaseContext(), "Your Invoice has been downloaded",Toast.LENGTH_SHORT).show();
                                    Uri path = Uri.fromFile(filePath);
                                    if(Build.VERSION.SDK_INT>=24){
                                        try{
                                            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                                            m.invoke(null);
                                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                            pdfIntent.setDataAndType(path , "application/pdf");
                                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            try
                                            {
                                                startActivity(pdfIntent ); }
                                            catch (ActivityNotFoundException e)
                                            {
                                                Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                            }
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        pdfIntent.setDataAndType(path , "application/pdf");
                                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        try
                                        {
                                            startActivity(pdfIntent ); }
                                        catch (ActivityNotFoundException e)
                                        {
                                            Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                else
                                {
                                    file = new File(Dir,  "/TSM_Recharge_Invoice.pdf");
                                    byte[] pdfAsBytes = Base64.decode(invoice_url,  0);
                                    File filePath = new File(path+"/TSM_Recharge_Invoice.pdf");
                                    FileOutputStream os = null;
                                    try {
                                        os = new FileOutputStream(filePath, true);
                                        os.write(pdfAsBytes);
                                        os.flush();
                                        os.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //Toast.makeText(getBaseContext(), "Your Invoice has been downloaded",Toast.LENGTH_SHORT).show();

                               /* Uri path = Uri.fromFile(file);
                                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                pdfIntent.setDataAndType(path , "application/pdf");
                                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                try
                                {
                                    startActivity(pdfIntent ); }
                                catch (ActivityNotFoundException e)
                                {
                                    Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                }*/

                                    Uri path = Uri.fromFile(filePath);
                                    if(Build.VERSION.SDK_INT>=24){
                                        try{
                                            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                                            m.invoke(null);
                                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                            pdfIntent.setDataAndType(path , "application/pdf");
                                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            try
                                            {
                                                startActivity(pdfIntent ); }
                                            catch (ActivityNotFoundException e)
                                            {
                                                Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                            }
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        pdfIntent.setDataAndType(path , "application/pdf");
                                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        try
                                        {
                                            startActivity(pdfIntent ); }
                                        catch (ActivityNotFoundException e)
                                        {
                                            Toast.makeText(context,"No Application available to viewPDF", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }

                        else {
                            Toast.makeText(context, "File not found!", Toast.LENGTH_SHORT).show();
                        }

                         /*try {
                             FileOutputStream fos = null;
                                fos = new FileOutputStream(DOWNLOADS_FOLDER);
                                fos.write(Base64.decode(invoice_url, Base64.DEFAULT));
                                fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/


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

    private void init() {
        context = PrepaidBillDetailsViewActivity.this;
        userId = MApplication.getString(context, Constants.UserID);
        authorityKey = "Bearer " +MApplication.getString(context, Constants.AccessToken);
        ll_back_btn = findViewById(R.id.ll_back_btn);
        ll_details = findViewById(R.id.ll_details);
        ll_recharge_date = findViewById(R.id.ll_recharge_date);
        tv_recharge_date = findViewById(R.id.tv_recharge_date);
        tv_recharge_date_txt = findViewById(R.id.tv_recharge_date_txt);
        tv_bill_amt = findViewById(R.id.tv_bill_amt);
        tv_tx_details = findViewById(R.id.tv_tx_details);
        tv_usable_amt_txt = findViewById(R.id.tv_usable_amt_txt);
        tv_usable_amt = findViewById(R.id.tv_usable_amt);
        tv_bill_num_txt = findViewById(R.id.tv_bill_num_txt);
        tv_bill_num = findViewById(R.id.tv_bill_num);
        tv_mode_txt = findViewById(R.id.tv_mode_txt);
        tv_mode = findViewById(R.id.tv_mode);
        tv_status_txt = findViewById(R.id.tv_status_txt);
        tv_status = findViewById(R.id.tv_status);
        tv_fixed_charges_txt = findViewById(R.id.tv_fixed_charges_txt);
        tv_fixed_charges = findViewById(R.id.tv_fixed_charges);
        tv_vat_txt = findViewById(R.id.tv_vat_txt);
        tv_vat = findViewById(R.id.tv_vat);
        tv_paid_amt_txt = findViewById(R.id.tv_paid_amt_txt);
        tv_paid_amt = findViewById(R.id.tv_paid_amt);
        tv_download = findViewById(R.id.tv_download);
        tv_paid_by_txt = findViewById(R.id.tv_paid_by_txt);
        tv_paid_by = findViewById(R.id.tv_paid_by);
        tv_payment_ref_txt = findViewById(R.id.tv_payment_ref_txt);
        tv_payment_ref = findViewById(R.id.tv_payment_ref);
        tv_usn_txt = findViewById(R.id.tv_usn_txt);
        tv_usn = findViewById(R.id.tv_usn);

        rcv_payment_breakup = findViewById(R.id.rcv_payment_breakup);
        recharge_payment_id = MApplication.getString(context, Constants.PREPAID_RECHARGE_ID);
        //Set fonts
        Typeface faceLight = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceMedium = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        tv_recharge_date.setTypeface(faceLight);
        tv_recharge_date_txt.setTypeface(faceMedium);
        tv_download.setTypeface(faceMedium);
        tv_bill_amt.setTypeface(faceLight);
        tv_tx_details.setTypeface(faceMedium);
        tv_usable_amt_txt.setTypeface(faceMedium);
        tv_usable_amt.setTypeface(faceLight);
        tv_bill_num_txt.setTypeface(faceMedium);
        tv_bill_num.setTypeface(faceLight);
        tv_mode_txt.setTypeface(faceMedium);
        tv_mode.setTypeface(faceLight);
        tv_status_txt.setTypeface(faceMedium);
        tv_status.setTypeface(faceLight);
        tv_fixed_charges_txt.setTypeface(faceMedium);
        tv_fixed_charges.setTypeface(faceLight);
        tv_vat_txt.setTypeface(faceMedium);
        tv_vat.setTypeface(faceLight);
        tv_paid_amt_txt.setTypeface(faceMedium);
        tv_paid_amt.setTypeface(faceLight);
        tv_paid_by_txt.setTypeface(faceMedium);
        tv_paid_by.setTypeface(faceLight);
        tv_payment_ref_txt.setTypeface(faceMedium);
        tv_payment_ref.setTypeface(faceLight);
        tv_usn_txt.setTypeface(faceMedium);
        tv_usn.setTypeface(faceLight);;
        if(MApplication.isNetConnected(context)){
            showLoaderNew();
            serviceCallForGettingBillDetails();
        }else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        rcv_payment_breakup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }


    private void serviceCallForGettingBillDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPrepaidBillDetailsInterface service = retrofit.create(GetPrepaidBillDetailsInterface.class);

        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new PrepaidBillDetailPostParameters(recharge_payment_id));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
               hideloader();
                if(response.body()!=null){

                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();

                    int errorCode = infoObject.get("errorCode").getAsInt();


                    if (errorCode==0)
                    {
                        JsonObject resultObject = jsonObject.get("result").getAsJsonObject();

                       dataResponseBillDetails(resultObject);
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

    private void dataResponseBillDetails(JsonObject result) {

        rechargeAmount = result.get("RECHARGE_AMOUNT").getAsString();
        //fixedCharges = result.get("Fixed_Charge").getAsString();
        status = result.get("TRANS_STATUS").getAsString();
        paymentDescription = result.get("PAYMENT_DESCRIPTION").getAsString();
        rechargeDate = result.get("RECHARGE_DATE").getAsString();
        payment_ref_num = result.get("ORDER_REF_NO").getAsString();
        usn_num = result.get("USN_NO").getAsString();
        paid_by = result.get("PAID_BY").getAsString();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        Date date = null;
        try {
            date = sdf.parse(rechargeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm");


        usableAmount = result.get("USABLE_AMOUNT").getAsString();
        //serviceCharge = result.get("Service_Charge").getAsString();
        //vat = result.get("VAT").getAsString();
        //lifeSaver = result.get("Life_Saver").getAsString();
        tranRef = result.get("TRANS_REF").getAsString();
        rechargeUsableAmt = result.get("RECHARGE_AMOUNT").getAsString();
        //fixedCharges = result.get("Fixed_Charge").getAsString();

        tv_bill_amt.setText(getResources().getString(R.string.currency)+" "+ rechargeAmount);
        tv_bill_num.setText(tranRef);
        tv_mode.setText(String.valueOf(paymentDescription));
        tv_status.setText(status);
        tv_payment_ref.setText(payment_ref_num);
        tv_paid_by.setText(paid_by);
        tv_usn.setText(usn_num);
        if(!usableAmount.equals(""))
        {
         tv_usable_amt.setText(getResources().getString(R.string.currency)+" "+rechargeAmount);
        }
        else tv_usable_amt.setText(rechargeAmount);
        /*if(!fixedCharges.equals(""))
        {
          tv_fixed_charges.setText(getResources().getString(R.string.currency)+" "+fixedCharges);
        }
        else tv_fixed_charges.setText(fixedCharges);
        if(!vat.equals(""))
        {
          tv_vat.setText(getResources().getString(R.string.currency)+" "+vat);
        }
        else tv_vat.setText(vat);
*/
        if(!rechargeAmount.equals(""))
        {
            tv_paid_amt.setText(getResources().getString(R.string.currency)+" "+rechargeAmount);
        }
        else tv_paid_amt.setText(rechargeAmount);
        tv_recharge_date.setText("Recharged on "+sdf1.format(date));

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

    }
    public void showLoaderNew() {
        runOnUiThread(new PrepaidBillDetailsViewActivity.Runloader(getResources().getString(R.string.loading)));
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


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public boolean isFileExist()
    {
        File Dir = new File(path);
        //  File file = new File(Dir,"nahk.txt");

        File file = new File(Dir,  "/TSM_Recharge_Invoice");

        if(file.exists()){
            return true;
        }
        else {
            return false;
        }
    }



}
