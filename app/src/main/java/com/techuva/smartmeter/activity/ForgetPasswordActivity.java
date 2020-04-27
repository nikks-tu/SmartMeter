package com.techuva.smartmeter.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.api_interface.ForgotPasswordDataInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ForgotPassPostParameters;
import com.techuva.smartmeter.utils.MApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ForgetPasswordActivity extends AppCompatActivity {

    LinearLayout ll_root_view_forgot, ll_layout_entry, ll_button_submit, ll_signin;
    ImageView iv_userNameforgot;
    EditText edt_username_forgot;
    String EmailId ="";
    Context context;
    int UserId;
    String authorityKey="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        InitViews();

        ll_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                ForgetPasswordActivity.this.startActivity(intent);
            }
        });

        ll_button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPasswordActivity.this.getInputData();
            }
        });

    }

    private void getInputData() {
        EmailId = edt_username_forgot.getText().toString();
        if(EmailId.length() > 0)
        {
            if(MApplication.isNetConnected(context)){
                serviceCall();
            }else {
                MApplication.showCustomToast(getResources().getString(R.string.no_internet), context);
            }
        } else
        {
            MApplication.showCustomToast(getResources().getString(R.string.enter_reg_email), context);
        }
    }


    private void serviceCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                //.baseUrl("http://182.18.177.27/TUUserManagement/api/user/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForgotPasswordDataInterface service = retrofit.create(ForgotPasswordDataInterface .class);

        Call<JsonElement> call = service.getStringScalar( new ForgotPassPostParameters(EmailId));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.body()!=null){
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    String errorMsg = infoObject.get("errorMessage").getAsString();
                    if(errorCode==0)
                        {
                            showCustomDialog(errorMsg);
                        }
                        else if(errorCode==1)
                        {
                        MApplication.showCustomToast(errorMsg, context);
                      }

                }else {
                    MApplication.showCustomToast(getResources().getString( R.string.something_went_wrong), context);
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                MApplication.showCustomToast(getResources().getString( R.string.something_went_wrong), context);
            }
        });

    }

    private void InitViews() {
        context = ForgetPasswordActivity.this;
        ll_root_view_forgot = findViewById(R.id.ll_root_view_forgot);
        ll_layout_entry = findViewById(R.id.ll_layout_entry);
        ll_button_submit = findViewById(R.id.ll_button_submit);
        ll_signin = findViewById(R.id.ll_signin);
        iv_userNameforgot = findViewById(R.id.iv_userNameforgot);
        edt_username_forgot = findViewById(R.id.edt_username_forgot);/*
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));*/
    }


    private void showCustomDialog(String text) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        TextView tv_alertText = dialogView.findViewById(R.id.tv_alertText);
        TextView button_ok = dialogView.findViewById(R.id.button_ok);
        tv_alertText.setText(text);

        button_ok.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent
            );
        });

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
