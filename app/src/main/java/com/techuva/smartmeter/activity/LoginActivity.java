package com.techuva.smartmeter.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.api_interface.AccountDetailsInterface;
import com.techuva.smartmeter.api_interface.LoginDataInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.AccountListPostParameters;
import com.techuva.smartmeter.responseModel.AccountListResultObject;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.responseModel.LoginMainObject;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    LinearLayout ll_root_login, ll_button_login, ll_layout_forgot_password;
    EditText edt_userName, edt_userPassword;
    Context loginContext;
    TextView tv_forgotPassword, tv_copyrights;
    MPreferences preferences;
    List<AccountListResultObject> accountListResultObjects = new ArrayList<>();
    Toast exitToast;
    public Dialog dialog;
    private AnimationDrawable animationDrawable;
    Boolean doubleBackToExitPressedOnce = true;
    private String EmailId;
    private String Password;
    int UserId = 20;
    String authorityKey ="";
    String grantType = "";
    String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitViews();
        exitToast = Toast.makeText(getApplicationContext(), R.string.exit_toast, Toast.LENGTH_SHORT);
        edt_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ll_button_login.setEnabled(true);
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                ll_button_login.setEnabled(true);
                // TODO Auto-generated method stub
            }
        });

        edt_userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ll_button_login.setEnabled(true);
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                ll_button_login.setEnabled(true);
                // TODO Auto-generated method stub
            }
        });

        tv_forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(loginContext, ForgetPasswordActivity.class);
            startActivity(intent);
        });


        ll_button_login.setOnClickListener(v -> {
      /*  Intent intent = new Intent(loginContext, Dashboard.class);
        startActivity(intent);*/
            ll_button_login.setEnabled(false);
            LoginActivity.this.getTextInputs();

            if (MApplication.isNetConnected(loginContext)) {
                if (EmailId.length() > 0 || Password.length() > 0) {
                    if (EmailId.length() > 0) {

                        if (Password.length() > 0) {
                            showLoaderNew();
                            serviceCall();
                        } else {
                            ll_button_login.setEnabled(true);
                            MApplication.showCustomToast(getResources().getString(R.string.enter_password), loginContext);
                        }
                    } else {
                        ll_button_login.setEnabled(true);
                        MApplication.showCustomToast(getResources().getString(R.string.enter_email), loginContext);
                    }
                } else {
                    ll_button_login.setEnabled(true);
                    MApplication.showCustomToast(getResources().getString(R.string.enter_email_password), loginContext);
                }
            } else {
                ll_button_login.setEnabled(true);
                MApplication.showCustomToast(getResources().getString(R.string.no_internet), loginContext);
            }
        });

    }

    private void getTextInputs() {
        EmailId = edt_userName.getText().toString().trim();
        Password = edt_userPassword.getText().toString();
    }



    private void serviceCallForAccounts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AccountDetailsInterface service = retrofit.create(AccountDetailsInterface.class);

        Call<AccountsListMainObject> call = service.getStringScalarWithSession(UserId, accessToken , new AccountListPostParameters(String.valueOf(UserId),"",""));
        call.enqueue(new Callback<AccountsListMainObject>() {
            @Override
            public void onResponse(Call<AccountsListMainObject> call, Response<AccountsListMainObject> response) {
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();

                    if(response.body().getInfo().getErrorCode()==0)
                    { hideloader();
                       accountListResultObjects = response.body().getResult();
                       proceedToNextScreen();
                    }
                    else if(response.body().getInfo().getErrorCode().equals(1))
                    {
                        hideloader();
                        MApplication.showCustomToast(response.body().getInfo().getErrorMessage(), loginContext);
                    }
                }else {
                    hideloader();
                }
            }

            @Override
            public void onFailure(Call<AccountsListMainObject> call, Throwable t) {
                hideloader();
                MApplication.showCustomToast(getResources().getString( R.string.something_went_wrong), loginContext);
            }
        });
    }

    private void proceedToNextScreen() {

        if(accountListResultObjects.size()>0)
        {
            MApplication.setBoolean(loginContext, Constants.IsHomeSelected, true);
           Intent intent = new Intent(loginContext, UserAccountsActivity.class);
            startActivity(intent);
        }
    }


    private void serviceCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                //.baseUrl(Constants.USER_MGMT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginDataInterface service = retrofit.create(LoginDataInterface .class);

        Call<LoginMainObject> call = service.loginCall(authorityKey, EmailId, Password, grantType);
        call.enqueue(new Callback<LoginMainObject>() {
            @Override
            public void onResponse(Call<LoginMainObject> call, Response<LoginMainObject> response) {
                if(response.body()!=null){
                   // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();

                    if(response.body().getInfo().getErrorCode()==0)
                    {
                        hideloader();
                        MApplication.setString(loginContext, Constants.AccessToken, response.body().getResult().getAccessToken());
                        MApplication.setString(loginContext, Constants.RefreshToken, response.body().getResult().getRefreshToken());
                        MApplication.setBoolean(loginContext, Constants.IsLoggedIn, true);
                        MApplication.setString(loginContext, Constants.UserID, String.valueOf(response.body().getResult().getUserId()));
                        UserId = Integer.parseInt(MApplication.getString(loginContext, Constants.UserID));
                        MApplication.setString(loginContext, Constants.UserName, String.valueOf(response.body().getResult().getUserName()));
                        MApplication.setString(loginContext, Constants.DateToExpireToken, response.body().getResult().getAccessExpiresIn());
                        MApplication.setString(loginContext, Constants.UserMailId, response.body().getResult().getEmail());
                        accessToken = "Bearer "+ MApplication.getString(loginContext, Constants.AccessToken);
                        preferences.setLoginStatus(true);
                        checkAccountList();
                    }
                    else if(response.body().getInfo().getErrorCode().equals(1))
                    {
                        hideloader();
                        ll_button_login.setEnabled(true);
                        MApplication.showCustomToast(response.body().getInfo().getErrorMessage(), loginContext);
                       // Toast.makeText(loginContext, response.body().getInfo().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    hideloader();
                    ll_button_login.setEnabled(true);
                    MApplication.showCustomToast(getResources().getString( R.string.something_went_wrong), loginContext);
                }

            }

            @Override
            public void onFailure(Call<LoginMainObject> call, Throwable t) {
                hideloader();
                ll_button_login.setEnabled(true);
                MApplication.showCustomToast(getResources().getString( R.string.something_went_wrong), loginContext);
            }
        });

    }

    private void checkAccountList() {
        serviceCallForAccounts();
    }

    private void InitViews() {
        loginContext = LoginActivity.this;
        preferences = new MPreferences(loginContext);
        ll_root_login  = findViewById(R.id.ll_root_login);
        tv_forgotPassword  = findViewById(R.id.tv_forgotPassword);
        tv_copyrights  = findViewById(R.id.tv_copyrights);
        ll_button_login = findViewById(R.id.ll_button_login);
        ll_layout_forgot_password = findViewById(R.id.ll_layout_forgot_password);
        edt_userName = findViewById(R.id.edt_userName);
        edt_userPassword = findViewById(R.id.edt_userPassword);
        ll_button_login.setEnabled(true);
        //tv_copyrights.setText("2019 "+"\u00a9"+ " Techuva Solutions Pvt. Ltd.");
        authorityKey = Constants.AuthorizationKey;
        grantType = Constants.GrantType;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            // Do what ever you want
            exitToast.show();
            doubleBackToExitPressedOnce = false;
        } else{
            finishAffinity();
            finish();
            // Do exit app or back press here
            super.onBackPressed();
        }
    }

    public void showLoaderNew() {
        runOnUiThread(new LoginActivity.Runloader(getResources().getString(R.string.loading)));
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
                    dialog = new Dialog(loginContext,R.style.Theme_AppCompat_Light_DarkActionBar);
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

}
