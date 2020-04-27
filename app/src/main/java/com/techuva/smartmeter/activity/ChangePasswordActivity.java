package com.techuva.smartmeter.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.api_interface.ChangePasswordDataInterface;
import com.techuva.smartmeter.api_interface.ForgotPasswordDataInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.ChangePasswordPostParameters;
import com.techuva.smartmeter.postParameters.ForgotPassPostParameters;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChangePasswordActivity extends BaseActivity  {

    Context context;
    MPreferences mPreferences;
    private View decorView;
    String authorityKey="";
    EditText edt_old_password, edt_new_Password, edt_confirmPassword;
    LinearLayout ll_button_update;
    String oldPassword ="";
    String newPassword="";
    String confirmPassword="";
    String EmailId="";
    TextView tv_heading, tv_btn_update;
    private int UserId;

    @Override
    public void initialize() {
     init();
    }
    Intent intent;

    private void init() {
        context = ChangePasswordActivity.this;
        mPreferences = new MPreferences(context);
        llContent.addView(inflater.inflate(R.layout.activity_change_password, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decorView = getWindow().getDecorView();
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        ll_button_update = findViewById(R.id.ll_button_update);
        edt_old_password = findViewById(R.id.edt_old_password);
        edt_confirmPassword = findViewById(R.id.edt_confirmPassword);
        edt_new_Password = findViewById(R.id.edt_new_Password);
        tv_heading = findViewById(R.id.tv_heading);
        tv_btn_update = findViewById(R.id.tv_btn_update);
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        edt_old_password.setTypeface(faceLight);
        edt_confirmPassword.setTypeface(faceLight);
        edt_new_Password.setTypeface(faceLight);
        tv_heading.setTypeface(faceLight);
        tv_btn_update.setTypeface(faceLight);
        UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));



        ll_button_update.setOnClickListener(v -> {
            getAllInputs();
           if(MApplication.isNetConnected(context)){
               if(!oldPassword.equals(""))
               {
                   if(!newPassword.equals(""))
                   {
                       if(!confirmPassword.equals(""))
                       {
                           if(newPassword.equals(confirmPassword))
                           {
                               if(newPassword.equals(confirmPassword) && newPassword.equals(oldPassword))
                               {
                                   Toast.makeText(context, "Old password and new password shouldn't be same.", Toast.LENGTH_SHORT).show();

                               }
                               else {
                                   serviceCall();
                               }
                           }
                           else {
                               Toast.makeText(context, "New password and confirm password doesn't match.", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(context, "Please confirm your password.", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else {
                       Toast.makeText(context, "Please enter new password.", Toast.LENGTH_SHORT).show();
                   }
               }
               else {
                   Toast.makeText(context, "Please enter old password.", Toast.LENGTH_SHORT).show();
               }
           }
           else {
               Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
           }
        });
    }


    private void getAllInputs() {
        EmailId = MApplication.getString(context, Constants.UserMailId);
        oldPassword = edt_old_password.getText().toString();
        newPassword = edt_new_Password.getText().toString();
        confirmPassword = edt_confirmPassword.getText().toString();
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
            MApplication.setBoolean(context, Constants.IsLoggedIn, false);
            MApplication.setString(context, Constants.UserID, "");
            MApplication.setString(context, Constants.UserMailId, "");
            MApplication.setString(context, Constants.USN_NUM, "");
            MApplication.setString(context, Constants.SelectedUSN, "");
            MApplication.setString(context, Constants.UserMailId, "");
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void serviceCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                //.baseUrl("http://182.18.177.27/TUUserManagement/api/user/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChangePasswordDataInterface service = retrofit.create(ChangePasswordDataInterface.class);

        Call<JsonElement> call = service.getStringScalar(UserId, authorityKey, new ChangePasswordPostParameters(EmailId, oldPassword, newPassword));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.body()!=null){
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    JsonObject infoObject = jsonObject.get("info").getAsJsonObject();
                    int errorCode = infoObject.get("errorCode").getAsInt();
                    if(errorCode==0)
                    {
                        showCustomDialog(infoObject.get("errorMessage").getAsString());
                    }
                    else if(errorCode==1)
                    {
                        Toast toast = Toast.makeText(context, infoObject.get("errorMessage").getAsString(), Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.toast_back_red));
                        toast.show();
                        // Toast.makeText(loginContext, response.body().getInfo().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else {
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
        MApplication.setBoolean(context, Constants.IsHomeSelected, true);
        intent = new Intent(context, UserAccountsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
