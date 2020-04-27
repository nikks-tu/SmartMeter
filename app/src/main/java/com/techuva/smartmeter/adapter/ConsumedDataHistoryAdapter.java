package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.techuva.smartmeter.R;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.responseModel.ConsumedDataHistoryObject;
import com.techuva.smartmeter.utils.MApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConsumedDataHistoryAdapter extends BaseAdapter {
    private final int resource;
    private Context mContext;
    List<ConsumedDataHistoryObject> list;

    public ConsumedDataHistoryAdapter(int resource, Context mContext, List<ConsumedDataHistoryObject> list) {
        this.resource = resource;
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder = null;

        // Inflater for custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.item_hr_channel_values, parent, false);

            holder = new ViewHolder();

            holder.tv_date = view.findViewById(R.id.tv_date);
            holder.tv_consumed_units = view.findViewById(R.id.tv_consumed_units);
            holder.tv_amount = view.findViewById(R.id.tv_amount);
            holder.tv_kwh = view.findViewById(R.id.tv_kwh);
            holder.tv_unit_rate = view.findViewById(R.id.tv_unit_rate);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ConsumedDataHistoryObject model = list.get(position);

        String dateToParse = model.getTRANSACTIONDATE();

        Date date = null;
        if(MApplication.getBoolean(mContext, Constants.From_Bill_History))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(dateToParse);
                //holder.tv_date.setVisibility(View.GONE);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");

            try {
                date = sdf.parse(dateToParse);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                String sa = timeFormatter.format(date);
                holder.tv_date.setText(sa);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String dateToStr = format.format(date);
            //System.out.println(dateToStr);
            //holder.tv_date_value.setText(dateToStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       // String.format("%.2f", d)
        String amount, kwh, unitConsumed, unitRate;
        Double amount_d = 0.00, kwh_d=0.00, unitConsumed_d=0.00, unitRate_d=0.00;

        try {
            amount = String.valueOf(model.getAMOUNT());
            amount_d = Double.parseDouble(amount);
            kwh = String.valueOf(model.getKWH());
            kwh_d = Double.parseDouble(kwh);
            unitConsumed = String.valueOf(model.getUNITSCONSUMED());
            unitConsumed_d = Double.parseDouble(unitConsumed);
            unitRate = String.valueOf(model.getUNITRATE());
            unitRate_d = Double.parseDouble(unitRate);
        } catch (NumberFormatException e) {
            // p did not contain a valid double
        }


        holder.tv_amount.setText(String.format("%.2f", amount_d));
        holder.tv_consumed_units.setText(String.format("%.2f", unitConsumed_d));
        holder.tv_kwh.setText(String.format("%.2f", kwh_d));
        holder.tv_unit_rate.setText(String.format("%.2f", unitRate_d));
        Typeface faceLight = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        holder.tv_date.setTypeface(faceLight);
        holder.tv_amount.setTypeface(faceLight);
        holder.tv_consumed_units.setTypeface(faceLight);
        holder.tv_kwh.setTypeface(faceLight);
        holder.tv_unit_rate.setTypeface(faceLight);
        return view;
    }

    // View Holder
    private class ViewHolder {
        public TextView tv_date, tv_consumed_units, tv_amount, tv_kwh, tv_unit_rate;
    }

}
