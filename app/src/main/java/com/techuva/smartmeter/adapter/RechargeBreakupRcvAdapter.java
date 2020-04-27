package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.RechargeBreakupRCVHolder;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;


public class RechargeBreakupRcvAdapter extends RecyclerView.Adapter<RechargeBreakupRCVHolder>
{

    private ArrayList<RechargeCalBreakupObject> arrayList;
    private Context context;
    private RechargeBreakupRCVHolder listHolder;
    private String UserName="";
    private int selectedPosition = -1;

    public RechargeBreakupRcvAdapter(Context context, ArrayList<RechargeCalBreakupObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(RechargeBreakupRCVHolder holder, int position) {
        RechargeCalBreakupObject model = arrayList.get(position);

        holder.tv_breakup_desc.setText(model.getBreakupDesc()+" "+context.getResources().getString(R.string.colon));

       /* DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String amount = df.format(model.getBreakupAmount());*/
       String amount = model.getBreakupAmount();
        holder.tv_breakup_amt.setText(amount);
        holder.tv_taxes.setVisibility(View.GONE);
        holder.tv_taxes.setText(model.getTaxes());
    }


    @Override
    public RechargeBreakupRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_recharge_breakup, viewGroup, false);
        listHolder = new RechargeBreakupRCVHolder(mainGroup);
        return listHolder;

    }


    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
            return String.valueOf(selectedPosition);
        }
        return "";
    }

}