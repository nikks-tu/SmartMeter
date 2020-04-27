package com.techuva.smartmeter.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.AccountListRcvAdapter;
import com.techuva.smartmeter.api_interface.AccountDetailsInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.postParameters.AccountListPostParameters;
import com.techuva.smartmeter.responseModel.AccountListResultObject;
import com.techuva.smartmeter.responseModel.AccountsListMainObject;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserAccountsActivity extends BaseActivity {

    Toolbar toolbar;
    TextView tv_btn_continue, tv_success_txt;
    LinearLayout ll_header;
    RecyclerView rcv_user_accounts;
    Context mContext;
    MPreferences mPreferences;
    private View decorView;
    int UserId;
    AccountListRcvAdapter adapter;
    Toast exitToast;
    Boolean doubleBackToExitPressedOnce = true;
    ArrayList<AccountListResultObject> accountList;
    ListView lv_data_list;
    String authorityKey ="";
    String grantType = "";
    Intent intent;
    String accessToken="";

    @Override
    public void initialize() {
        init();
        exitToast = Toast.makeText(getApplicationContext(), R.string.exit_toast, Toast.LENGTH_SHORT);


        if(MApplication.isNetConnected(basecontext)){
            serviceCall();
        }else {
            Toast.makeText(basecontext, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }

        setTypeface();
    }

    @Override
    public void goto_home() {
        if(MApplication.getBoolean(mContext, Constants.IsHomeSelected))
        {

        }
        else
        {
            MApplication.setBoolean(mContext, Constants.IsHomeSelected, true);
            Intent intent = new Intent(mContext, UserAccountsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void goto_recharge_meter() {
        MApplication.setBoolean(mContext, Constants.IsUSNSelected, false);
        intent = new Intent(mContext, RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_consumed_units() {

    }

    @Override
    public void goto_recharge_history() {

    }

    @Override
    public void goto_change_password() {
        intent = new Intent(mContext, ChangePasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goto_about_app() {

    }

    @Override
    public void goto_terms_conditions() {

    }

    @Override
    public void goto_meter_loan_details() {
        intent = new Intent(mContext, MeterLoanSummaryActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_migration_schedule_details() {
        intent = new Intent(mContext, MigrationScheduleDetailsActivity.class);
        startActivity(intent);
    }
    @Override
    public void goto_logout_method() {
        MApplication.setBoolean(mContext, Constants.IsLoggedIn, false);
        MApplication.setString(mContext, Constants.UserID, "");
        MApplication.setString(mContext, Constants.UserMailId, "");
        MApplication.setString(mContext, Constants.USN_NUM, "");
        MApplication.setString(mContext, Constants.SelectedUSN, "");
        intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }


    private void setTypeface() {
        Typeface faceLight = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceBook = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Book.otf");
        Typeface faceMedium = Typeface.createFromAsset(getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        tv_btn_continue.setTypeface(faceMedium);
        tv_success_txt.setTypeface(faceMedium);

    }

    private void init() {
        mContext = UserAccountsActivity.this;
        mPreferences = new MPreferences(mContext);
        TextView textView = findViewById(R.id.page_heading);
        textView.setText(getResources().getString(R.string.your_active_onnection));
        llContent.addView(inflater.inflate(R.layout.activity_user_accounts, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        decorView = getWindow().getDecorView();
        accountList = new ArrayList<>();
        tv_btn_continue  = findViewById(R.id.tv_btn_continue);
        tv_success_txt  = findViewById(R.id.tv_success_txt);
        ll_header  = findViewById(R.id.ll_header);
        rcv_user_accounts  = findViewById(R.id.rcv_user_accounts);
        lv_data_list  = findViewById(R.id.lv_data_list);
        UserId = Integer.parseInt(MApplication.getString(mContext, Constants.UserID));
        authorityKey = Constants.AuthorizationKey;
        grantType = Constants.GrantType;
        accessToken = "Bearer "+MApplication.getString(mContext, Constants.AccessToken);
      // Toast.makeText(mContext, "UserId"+ UserId, Toast.LENGTH_SHORT).show();
    }

    private void serviceCall() {

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(Constants.USER_MGMT_URL)
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccountDetailsInterface service = retrofit.create(AccountDetailsInterface.class);

       // Call<AccountsListMainObject> call = service.getUserAccountDetails(UserId);
        Call<AccountsListMainObject> call = service.getStringScalarWithSession(UserId, accessToken, new AccountListPostParameters(String.valueOf(UserId), "",""));
        call.enqueue(new Callback<AccountsListMainObject>() {
            @Override
            public void onResponse(Call<AccountsListMainObject> call, Response<AccountsListMainObject> response) {
                if(response.body()!=null){
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();

                    if(response.body().getInfo().getErrorCode()==0)
                    {
                        /*Intent intent = new Intent(mContext, UserDevicesListActivity.class);
                        startActivity(intent);*/
                        setDataAdapterforList(response.body());
                    }
                    else if(response.body().getInfo().getErrorCode().equals(1))
                    {
                        Toast toast = Toast.makeText(mContext, response.body().getInfo().getErrorMessage(), Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.toast_back_red));
                        toast.show();
                   }
                }else {

                }
            }
            @Override
            public void onFailure(Call<AccountsListMainObject> call, Throwable t) {
                Toast toast = Toast.makeText(mContext, R.string.something_went_wrong, Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.toast_back_red));
                toast.show();

            }
        });
    }

    private void setDataAdapterforList(AccountsListMainObject result) {

        if(result!=null)
        {
            accountList.addAll(result.getResult());
        }
        if(accountList.size()==1)
        {
           // Toast.makeText(mContext, "Single", Toast.LENGTH_SHORT).show();
            MApplication.setBoolean(mContext, Constants.SingleAccount, true);
        }
        else
        {
            MApplication.setBoolean(mContext, Constants.SingleAccount, false);

        }
        adapter = new AccountListRcvAdapter(mContext, accountList, (view, position) -> {
          //  Toast.makeText(mContext, "HI"+ accountList.get(position), Toast.LENGTH_SHORT).show();
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_user_accounts.setLayoutManager(linearLayoutManager);
        rcv_user_accounts.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            exitToast.show();
            doubleBackToExitPressedOnce = false;
        } else{
            finishAffinity();
            finish();
            // Do exit app or back press here
            super.onBackPressed();
        }
    }


}
