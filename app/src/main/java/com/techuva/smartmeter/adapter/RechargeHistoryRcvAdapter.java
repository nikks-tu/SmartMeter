package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.RechargeHistoryRCVHolder;
import com.techuva.smartmeter.responseModel.RechargeHistoryResultObject;
import com.techuva.smartmeter.utils.MApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RechargeHistoryRcvAdapter extends RecyclerView.Adapter<RechargeHistoryRCVHolder>
{
    private ArrayList<RechargeHistoryResultObject> arrayList;
    private Context context;
    private RechargeHistoryRCVHolder listHolder;
    private String UserName="";
    private int selectedPosition = -1;
    private int selectedBillId=-1;

    public RechargeHistoryRcvAdapter(Context context, ArrayList<RechargeHistoryResultObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(RechargeHistoryRCVHolder holder, int position) {
        //selectedPosition = position;
        RechargeHistoryResultObject model = arrayList.get(position);
        UserName = MApplication.getString(context, "UserName");
        holder.tv_balance_amt.setText(String.valueOf(model.getRECHARGEAMOUNT()));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        Date date = null;
        try {
           date = sdf.parse(model.getRECHARGEDATE());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
        holder.tv_date.setText(sdf1.format(date));
        holder.tv_mode.setText(model.getPAYMENTDESCRIPTION());
        holder.tv_status_recharge.setText(model.getTRANSDESCRIPTION());


        holder.ll_main.setOnClickListener(v -> {
            selectedPosition = position;
            selectedBillId = model.getPAYMENTID();
            notifyDataSetChanged();
        });
/*
        if(selectedPosition == position)
        {
            selectedBillId = model.getRECHARGEPAYMENTID();
        }
        else {
            selectedBillId =0;
        }*/

    }


    @Override
    public RechargeHistoryRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_recharge_history, viewGroup, false);
        viewGroup.setOnClickListener(v -> notifyAll());
        listHolder = new RechargeHistoryRCVHolder(mainGroup);
        return listHolder;

    }



    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
           return String.valueOf(selectedBillId);
        }
        return "";
    }

    public String getItem(int id) {
        return String.valueOf(arrayList.get(id).getPAYMENTID());
    }


}