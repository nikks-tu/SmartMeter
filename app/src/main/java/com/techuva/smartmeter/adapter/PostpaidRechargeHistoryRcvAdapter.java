package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.PostpaidRechargeHistoryRCVHolder;
import com.techuva.smartmeter.holders.RechargeHistoryRCVHolder;
import com.techuva.smartmeter.responseModel.PostpaidHistoryResultObject;
import com.techuva.smartmeter.responseModel.RechargeHistoryResultObject;
import com.techuva.smartmeter.utils.MApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PostpaidRechargeHistoryRcvAdapter extends RecyclerView.Adapter<PostpaidRechargeHistoryRCVHolder>
{
    private ArrayList<PostpaidHistoryResultObject> arrayList;
    private Context context;
    private PostpaidRechargeHistoryRCVHolder listHolder;
    private CompoundButton lastCheckedRB = null;
    private int selectedPosition = -1;
    private int selectedBillId=0;
    private Date to_date, from_date;

    public PostpaidRechargeHistoryRcvAdapter(Context context, ArrayList<PostpaidHistoryResultObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(PostpaidRechargeHistoryRCVHolder holder, int position) {
        selectedPosition = position;
        PostpaidHistoryResultObject model = arrayList.get(position);
        holder.tv_bill_amt.setText(context.getResources().getString(R.string.currency)+" "+String.valueOf(model.getBILLINGAMOUNT()));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        Date date = null;
        try {
           date = sdf.parse(model.getBILLINGDATE());
           if (!model.getTODATE().equals("")){
               to_date = sdf.parse(model.getTODATE());
           }

           if (!model.getFROMDATE().equals(""))
           {
               from_date = sdf.parse(model.getFROMDATE());
           }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date date2 = null;
        try {
            date2 = sdf.parse(model.getDUEDATE());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!model.getFROMDATE().equals("")){

            holder.tv_bill_date_from.setText(sdf1.format(from_date));
        }
        else {
            holder.tv_bill_date_from.setText("");
        }
        if (!model.getTODATE().equals(""))  {
            holder.tv_bill_date_to.setText(sdf1.format(to_date));
        }
        else {
            holder.tv_bill_date_to.setText("");
        }
        holder.tv_unit_consumed.setText(model.getUNITSCONSUMED().toString()+" "+context.getResources().getString(R.string.units));
        holder.tv_due_date.setText("Due by "+sdf1.format(date2));
        String billing_id = String.valueOf(model.getPOSTPAIDBILLINGID());
        if(billing_id.length()!=6)
        {
            int length = 6-billing_id.length();

            for (int i=0; i<length; i++)
            {
                billing_id = "0"+billing_id;
            }
        }
        holder.tv_unit_consumed_txt.setText("# "+billing_id);
        holder.tv_status.setText(String.valueOf(model.getPAIDSTATUS()));
        if(selectedPosition == position)
        {
            selectedBillId = model.getPOSTPAIDBILLINGID();
        }
        else {
            selectedBillId =0;
        }

    }


    @Override
    public PostpaidRechargeHistoryRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_postpaid_history, viewGroup, false);
        listHolder = new PostpaidRechargeHistoryRCVHolder(mainGroup);
        return listHolder;

    }



    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
           // Toast.makeText(context, "Selected Item : " + arrayList.get(selectedPosition).getCompanyName(), Toast.LENGTH_SHORT).show();
            return String.valueOf(selectedBillId);
        }
        return "";
    }

    public String getItem(int id) {
        return String.valueOf(arrayList.get(id).getPOSTPAIDBILLINGID());
    }


}