package com.techuva.smartmeter.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.adapter.ConsumedDataDayRcvAdapter;
import com.techuva.smartmeter.adapter.ConsumedDataHistoryAdapter;
import com.techuva.smartmeter.api_interface.GetConsumedUnits;
import com.techuva.smartmeter.api_interface.GetConsumedUnitsListInterface;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.listener.EndlessScrollListener;
import com.techuva.smartmeter.listener.RecyclerItemClickListener;
import com.techuva.smartmeter.postParameters.GetConsumedDataPostParamter;
import com.techuva.smartmeter.postParameters.GetConsumedUnitListPostParamter;
import com.techuva.smartmeter.responseModel.ConsumedDataHistoryObject;
import com.techuva.smartmeter.responseModel.ConsumedDataValueObject;
import com.techuva.smartmeter.utils.MApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ConsumedUnitsActivity extends AppCompatActivity {

    Context context;
    String authorityKey ="";
    String toDate="";
    String fromDate="";
    String inventoryName, userId;
    String inventoryId ="";
    String usnNo ="";
    public Dialog dialog;
    private AnimationDrawable animationDrawable;
    HorizontalCalendar horizontalCalendar;
    ListView lv_consumed_data;
    HorizontalScrollView hv_units;
    TextView tv_date, tv_consumed_units, tv_amount, tv_kwh, tv_unit_rate;
    RecyclerView rcv_days_data;
    LinearLayout ll_back_btn;
    ArrayList<ConsumedDataValueObject> arrayList;
    ConsumedDataValueObject valueObject;
    ConsumedDataDayRcvAdapter dayDataAdapter;
    ArrayList<ConsumedDataHistoryObject> historyList;
    ConsumedDataHistoryObject historyObject;
    ConsumedDataHistoryAdapter historyAdapter;
    String pagePerCount= "20";
    int pageNumber= 1;
    String fromDateCall="";
    String toDateCall="";
    TextView tv_nodata;
    int listCount = 0;
    ImageView iv_back_btn;
    int toRecord =0;
    String type ="Dr";
    String dateForHistory="";
    private EndlessScrollListener scrollListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumed_units);
        init();
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -2);
        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 0);
        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .selectorColor(getResources().getColor(R.color.app_orange))
                .showBottomText(false)
                .end()
                //.defaultSelectedDate(defaultSelectedDate)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                fromDate = DateFormat.format("yyyy-MM-dd", date).toString();
                toDate = DateFormat.format("yyyy-MM-dd", date).toString();
                dateForHistory = DateFormat.format("yyyy-mm-dd", date).toString();
                historyList.clear();
                historyList = new ArrayList<>();
                showLoaderNew();
                serviceCallforConsumedData();
                serviceCallforChannelData();
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

        scrollListener = new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if (pageNumber < listCount) {
                    pageNumber = pageNumber + 1;
                    loadNextDataFromApi(pageNumber, 200);
                    //tabChanged= false;
                } else {
                    pageNumber = 1;
                }
                //Mistake
                return true;
            }
        };
        lv_consumed_data.setOnScrollListener(scrollListener);

        rcv_days_data.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv_days_data, new RecyclerItemClickListener.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int position) {

                String dateToParse = arrayList.get(position).getDATE();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

                Date date = null;
                String dateToStr="";
                try {
                    date = sdf.parse(dateToParse);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    dateToStr = format.format(date);
                    //System.out.println(dateToStr);
                    //holder.tv_date_value.setText(dateToStr);
                } catch (android.net.ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                dateForHistory = dateToStr;
                StringBuffer sb = new StringBuffer();
                sb.append(dateToStr);
                sb.append(" ");
                sb.append("00:00:00");
                fromDateCall = sb.toString();
                StringBuffer sb1 = new StringBuffer();
                sb1.append(dateToStr);
                sb1.append(" ");
                sb1.append("23:59:59");
                toDateCall = sb1.toString();
                historyList = new ArrayList<>();
                scrollListener.resetValues();
                pageNumber = 1;
                showLoaderNew();
                historyList.clear();
                historyList = new ArrayList<>();
                serviceCallforChannelData();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        ll_back_btn.setOnClickListener(v -> {
            MApplication.setBoolean(context, Constants.IsHomeSelected, false);
            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
        });

    }

    private void init() {
        context = ConsumedUnitsActivity.this;
       /* TextView textView = findViewById(R.id.page_heading);
        textView.setText(getResources().getString(R.string.units_history));
*/      hideloader();
        iv_back_btn = findViewById(R.id.iv_back_btn);
        authorityKey = "Bearer "+ MApplication.getString(context, Constants.AccessToken);
        userId = MApplication.getString(context, Constants.UserID);
        inventoryId = MApplication.getString(context, Constants.DeviceID);
        usnNo = MApplication.getString(context, Constants.USN_NUM);
        lv_consumed_data = findViewById(R.id.lv_consumed_data);
        hv_units = findViewById(R.id.hv_units);
        tv_date = findViewById(R.id.tv_date);
        tv_consumed_units = findViewById(R.id.tv_consumed_units);
        tv_amount = findViewById(R.id.tv_amount);
        tv_kwh = findViewById(R.id.tv_kwh);
        tv_unit_rate = findViewById(R.id.tv_unit_rate);
        rcv_days_data = findViewById(R.id.rcv_days_data);
        inventoryName = MApplication.getString(context, Constants.InventoryName);
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        fromDate = sdf4.format(calendar.getTime());
        dateForHistory = sdf4.format(calendar.getTime());
        toDate = sdf4.format(calendar.getTime());
        tv_nodata = findViewById(R.id.tv_nodata);
        ll_back_btn = findViewById(R.id.ll_back_btn);
        tv_nodata.setText(getResources().getString(R.string.no_unit_history));
        historyList = new ArrayList<>();
        setTypeFace();
        if(MApplication.isNetConnected(context)){
            showLoaderNew();
            serviceCallforConsumedData();
            serviceCallforChannelData();
        }
        else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        MApplication.setBoolean(context, Constants.From_Bill_History, false);

    }

    private void setTypeFace() {
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        tv_nodata.setTypeface(faceLight);
        tv_date.setTypeface(faceLight);
        tv_consumed_units.setTypeface(faceLight);
        tv_amount.setTypeface(faceLight);
        tv_kwh.setTypeface(faceLight);
        tv_unit_rate.setTypeface(faceLight);
    }

    private void serviceCallforConsumedData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetConsumedUnits service = retrofit.create(GetConsumedUnits.class);
        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new GetConsumedDataPostParamter(fromDate, toDate, inventoryName));
        call.enqueue(new Callback<JsonElement>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();
                if (response.code() == 401) {
                    MApplication.setBoolean(context, Constants.IsSessionExpired, true);
                    Intent intent = new Intent(context, TokenExpireActivity.class);
                    startActivity(intent);
                } else if (response.body() != null) {

                    JsonObject jsonObject = response.body().getAsJsonObject();
                    if (!jsonObject.isJsonNull()) {
                        JsonObject jObj = jsonObject.getAsJsonObject("info");
                        if (jObj.get("errorCode").getAsInt() == 0) {
                            JsonObject resultObject = jsonObject.getAsJsonObject("result");
                            JsonArray dataArray = resultObject.getAsJsonArray("DaywiseConsumption");
                            arrayList = new ArrayList<>();
                            for (int i=0; i<dataArray.size(); i++)
                            {
                                JsonObject unitObject = dataArray.get(i).getAsJsonObject();
                                valueObject = new ConsumedDataValueObject();
                                valueObject.setDATE(unitObject.get("DATE").getAsString());
                                valueObject.setSUM(unitObject.get("SUM").getAsString());
                                arrayList.add(valueObject);
                            }
                            dayDataAdapter = new ConsumedDataDayRcvAdapter(context, arrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
                            rcv_days_data.setLayoutManager(linearLayoutManager);
                            rcv_days_data.setAdapter(dayDataAdapter);
                            String dateToParse = arrayList.get(0).getDATE();
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                            Date date = null;
                            String dateToStr="";
                            try {
                                date = sdf.parse(dateToParse);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                dateToStr = format.format(date);
                                dateForHistory = dateToStr;
                                //System.out.println(dateToStr);
                                //holder.tv_date_value.setText(dateToStr);
                            } catch (android.net.ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                            historyList.clear();
                            pageNumber = 1;
                            //showLoaderNew();
                            scrollListener.resetValues();
                            serviceCallforChannelData();

                        } else if (jObj.get("ErrorCode").getAsInt() == 1) {

                        } else {

                        }

                    } else {
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();
            }
        });

    }

    public void showLoaderNew() {
        runOnUiThread(new ConsumedUnitsActivity.Runloader(getResources().getString(R.string.loading)));
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
                if (dialog == null) {
                    dialog = new Dialog(context, R.style.Theme_AppCompat_Light_DarkActionBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(
                            new ColorDrawable(Color.TRANSPARENT));
                }
                dialog.setContentView(R.layout.loading);
                dialog.setCancelable(false);

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
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
                imgeView.post(() -> {
                    if (animationDrawable != null)
                        animationDrawable.start();
                });
            } catch (Exception e) {

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
        });
    }

    private void serviceCallforChannelData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_TOKEN)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetConsumedUnitsListInterface service = retrofit.create(GetConsumedUnitsListInterface.class);
        Call<JsonElement> call = service.getStringScalarWithSession(Integer.parseInt(userId), authorityKey, new GetConsumedUnitListPostParamter(usnNo, type, dateForHistory, pagePerCount, String.valueOf(pageNumber)));
        call.enqueue(new Callback<JsonElement>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                hideloader();

                if (response.code() == 401) {
                    MApplication.setBoolean(context, Constants.IsSessionExpired, true);
                    Intent intent = new Intent(context, TokenExpireActivity.class);
                    startActivity(intent);
                } else if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    if (!jsonObject.isJsonNull()) {
                       if(jsonObject.has("info")){
                           JsonObject jObj = jsonObject.getAsJsonObject("info");
                           if(!jObj.isJsonNull()){
                               if (jObj.get("errorCode").getAsInt() == 0) {
                                   listCount = jObj.get("listCount").getAsInt();
                                   JsonArray resultArray = jsonObject.getAsJsonArray("result");
                                   //Toast.makeText(context, ""+resultArray.size(), Toast.LENGTH_SHORT).show();
                                   if (resultArray.size() > 0) {
                                       tv_nodata.setVisibility(View.GONE);
                                       lv_consumed_data.setVisibility(View.VISIBLE);
                                       hv_units.setVisibility(View.VISIBLE);
                                       lv_consumed_data.setSelectionAfterHeaderView();
                                       if(pageNumber==1)
                                       {
                                           historyList = new ArrayList<>();
                                       }
                                       dataResponseforChannelData(resultArray);

                                   } else {

                                   }
                               } else if (jObj.get("errorCode").getAsInt() == 1) {
                                   tv_nodata.setVisibility(View.VISIBLE);
                                   lv_consumed_data.setVisibility(View.GONE);
                                   hv_units.setVisibility(View.GONE);
                               } else {

                               }
                           }
                       }
                       else {
                          // Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
                       }

                    } else {
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                hideloader();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("WrongConstant")
    private void dataResponseforChannelData(JsonArray resultArray) {

        for (int i=0; i<resultArray.size(); i++)
        {
            JsonObject valueObject = resultArray.get(i).getAsJsonObject();
            historyObject = new ConsumedDataHistoryObject();
            historyObject.setTRANSACTIONDATE(valueObject.get("TRANSACTION_DATE").getAsString());
            historyObject.setUNITSCONSUMED(valueObject.get("UNITS_CONSUMED").getAsString());
            historyObject.setAMOUNT(valueObject.get("AMOUNT").getAsString());
            historyObject.setKWH(valueObject.get("KWH").getAsString());
            historyObject.setUNITRATE(valueObject.get("UNIT_RATE").getAsString());
            historyList.add(historyObject);
        }
        lv_consumed_data.setSelectionAfterHeaderView();
        lv_consumed_data.setSelection(0);
        if(pageNumber ==1)
        {
            historyAdapter = new ConsumedDataHistoryAdapter(R.layout.item_hr_channel_values, context, historyList);
            lv_consumed_data.setAdapter(historyAdapter);
        }
        else {
            historyAdapter.notifyDataSetChanged();
        }


    }


    private void loadNextDataFromApi(int page, int delay) {
        pageNumber = page;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                 //lv_consumed_data.setSelectionAfterHeaderView();
                 serviceCallforChannelData();
            }
        }, delay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
