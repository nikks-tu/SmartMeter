package com.techuva.smartmeter.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.RechargeHistoryRcvAdapter;
import com.techuva.smartmeter.api_interface.RechargeHistoryInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.RechargeHistoryPostParameters;
import com.techuva.smartmeter.responseModel.RechargeHistoryMainObject;
import com.techuva.smartmeter.responseModel.RechargeHistoryResultObject;
import com.techuva.smartmeter.utils.EightFoldsDatePickerDialog;
import com.techuva.smartmeter.utils.MApplication;
import com.techuva.smartmeter.utils.MPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RechargeHistoryActivity extends BaseActivity {

    Context context;
    MPreferences mPreferences;
    ImageView iv_from_date, iv_to_date;
    TextView tv_from_date, tv_to_date, tv_date, tv_mode, tv_amount, tv_no_data;
    RecyclerView rcv_recharge_history;
    LinearLayout ll_from_date, ll_to_date;
    String from_date_call, to_date_call;
    static String fromDate;
    static String toDate;
    int day, year, month;
    int to_day, to_year, to_month;
    String pagePerCount, pageNumber, usn, userId;
    String accessToken;
    RechargeHistoryRcvAdapter rechargeHistoryRcvAdapter;
    Intent intent;
    ArrayList<RechargeHistoryResultObject> resultObjectArrayList;
    private DatePickerDialog.OnDateSetListener myDateListener = (arg0, arg1, arg2, arg3) -> {
        // TODO Auto-generated method stub
        // arg1 = year
        // arg2 = month
        // arg3 = day
        showDate(arg1, arg2 + 1, arg3);
    };
    SimpleDateFormat sdf4;
    SimpleDateFormat sdf3;


    @Override
    public void initialize() {
        context = RechargeHistoryActivity.this;
        mPreferences = new MPreferences(context);
        TextView textView = findViewById(R.id.page_heading);
        textView.setText(getResources().getString(R.string.your_recharge_history));
        llContent.addView(inflater.inflate(R.layout.activity_recharge_history, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iv_from_date = findViewById(R.id.iv_from_date);
        iv_to_date = findViewById(R.id.iv_to_date);
        tv_from_date = findViewById(R.id.tv_from_date);
        tv_to_date = findViewById(R.id.tv_to_date);
        tv_date = findViewById(R.id.tv_date);
        tv_mode = findViewById(R.id.tv_mode);
        tv_amount = findViewById(R.id.tv_amount);
        tv_no_data = findViewById(R.id.tv_no_data);
        ll_from_date = findViewById(R.id.ll_from_date);
        ll_to_date = findViewById(R.id.ll_to_date);
        rcv_recharge_history = findViewById(R.id.rcv_recharge_history);
        Typeface faceLight = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceMedium = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        tv_from_date.setTypeface(faceMedium);
        tv_to_date.setTypeface(faceMedium);
        tv_date.setTypeface(faceMedium);
        tv_mode.setTypeface(faceMedium);
        tv_amount.setTypeface(faceMedium);
        sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        sdf3= new SimpleDateFormat("dd-MMM-yyyy");
        Calendar calendar = new GregorianCalendar();
        fromDate = sdf3.format(calendar.getTime());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        toDate = sdf3.format(cal.getTime());
        tv_to_date.setText(toDate);
        tv_from_date.setText(fromDate);
        to_date_call = sdf4.format(calendar.getTime());
        from_date_call = sdf4.format(cal.getTime());
        usn = MApplication.getString(context, Constants.USN_NUM);
        userId = MApplication.getString(context, Constants.UserID);
        accessToken = "Bearer "+MApplication.getString(context, Constants.AccessToken);
        pageNumber = "";
        pagePerCount="";

        ll_from_date.setOnClickListener(v -> {
            if (month < 10) {
                showtoDate(year, month + 1, day);
            } else
                showtoDate(year, month, day);
        });

        ll_to_date.setOnClickListener(v -> {
            if (month < 10) {
                showDate(year, month, day);
            } else
                showDate(year, month, day);
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, -7);
        to_day= calendar.get(Calendar.DAY_OF_MONTH);
        to_year = calendar.get(Calendar.YEAR);
        to_month = calendar.get(Calendar.MONTH);
        to_month = to_month+1;
        //getDate();
        if(MApplication.isNetConnected(context)){
            serviceCall();
        }else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }


        rcv_recharge_history.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv_recharge_history, new RecyclerItemClickListener.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int position) {
                String i = String.valueOf(rechargeHistoryRcvAdapter.getItem(position));
                //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
                MApplication.setString(context, Constants.PREPAID_RECHARGE_ID, i);
                Intent intent = new Intent(context, PrepaidBillDetailsViewActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private void getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        from_date_call = sdf.format(tv_from_date.getText().toString());
        to_date_call = sdf.format(tv_to_date.getText().toString());
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

    }

    @Override
    public void goto_recharge_history() {

    }

    @Override
    public void goto_change_password() {

        intent = new Intent(context, ChangePasswordActivity.class);
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
        intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }


    private void serviceCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RechargeHistoryInterface service = retrofit.create(RechargeHistoryInterface .class);
        Call<RechargeHistoryMainObject> call = service.getStringScalarWithSession(Integer.parseInt(userId), accessToken, new RechargeHistoryPostParameters(usn, pagePerCount, pageNumber, from_date_call, to_date_call));
        call.enqueue(new Callback<RechargeHistoryMainObject>() {
            @Override
            public void onResponse(Call<RechargeHistoryMainObject> call, Response<RechargeHistoryMainObject> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if(response.body()!=null){

                    if (response.body().getInfo().getErrorCode()==0)
                    {
                        if(response.body().getResult()!=null)
                        {
                            tv_no_data.setVisibility(View.GONE);
                            rcv_recharge_history.setVisibility(View.VISIBLE);
                            dataResponse(response.body().getResult());
                        }
                    }
                    // Toast.makeText(getBaseContext(),response.body().getInfo().getErrorMessage(),Toast.LENGTH_SHORT).show();

                    else
                    {
                        tv_no_data.setVisibility(View.VISIBLE);
                        tv_no_data.setText(response.body().getInfo().getErrorMessage());
                        rcv_recharge_history.setVisibility(View.GONE);
                    }

                }else {
                    tv_no_data.setVisibility(View.VISIBLE);
                    rcv_recharge_history.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<RechargeHistoryMainObject> call, Throwable t) {
                hideloader();
                 Toast.makeText(context, "Error connecting server" , Toast.LENGTH_SHORT).show();
                tv_no_data.setVisibility(View.VISIBLE);
                rcv_recharge_history.setVisibility(View.GONE);
            }

        });

    }

    private void dataResponse(List<RechargeHistoryResultObject> result) {
        resultObjectArrayList = new ArrayList<>();
        for (int i=0; i<result.size(); i++)
        {
            resultObjectArrayList.add(result.get(i));
        }

        rechargeHistoryRcvAdapter = new RechargeHistoryRcvAdapter(context, resultObjectArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_recharge_history.setLayoutManager(linearLayoutManager);
        rcv_recharge_history.setAdapter(rechargeHistoryRcvAdapter);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }



    private void showDate(int year, final int month, int day) {
        EightFoldsDatePickerDialog datePickerDialog = new EightFoldsDatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                String date = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                String date2 = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                Date date3 = null;
                try {
                    date3 = sdf4.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_from_date.setText(sdf3.format(date3));
                toDate = sdf4.format(date3);
                to_date_call = sdf4.format(date3);
                serviceCall();
            }

        }, year, month, day);
        //Toast.makeText(context, ""+to_year+"-"+to_month+"-"+to_day,  Toast.LENGTH_SHORT).show();
        datePickerDialog.setMinDate(to_year, to_month, to_day);
        datePickerDialog.setTodayAsMaxDate();/*
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());*/
        datePickerDialog.show();
    }

    private void showtoDate(int year, final int month, int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                String date = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                String date2 = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                Date date3 = null;
                try {
                    date3 = sdf4.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tv_to_date.setText(sdf3.format(date3));
                fromDate = sdf4.format(date3);
                from_date_call = sdf4.format(date3);
                to_day = dayOfMonth;
                to_year = year;
                to_month = monthOfYear+1;
                serviceCall();

            }
        }, year, month, day);
        //Toast.makeText(context, ""+to_year+"-"+to_month+"-"+to_day,  Toast.LENGTH_SHORT).show();
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    @Override
    public void onBackPressed() {
        MApplication.setBoolean(context, Constants.IsHomeSelected, false);
        intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
