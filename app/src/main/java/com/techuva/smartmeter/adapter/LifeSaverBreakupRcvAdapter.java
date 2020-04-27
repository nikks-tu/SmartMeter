package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.LifeSaverBreakupRCVHolder;
import com.techuva.smartmeter.responseModel.LifeSaverCalculationBreakup;

import java.util.ArrayList;


public class LifeSaverBreakupRcvAdapter extends RecyclerView.Adapter<LifeSaverBreakupRCVHolder>
{

    private ArrayList<LifeSaverCalculationBreakup> arrayList;
    private Context context;
    private LifeSaverBreakupRCVHolder listHolder;
    private String UserName="";
    private int selectedPosition = -1;

    public LifeSaverBreakupRcvAdapter(Context context, ArrayList<LifeSaverCalculationBreakup> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(LifeSaverBreakupRCVHolder holder, int position) {
        LifeSaverCalculationBreakup model = arrayList.get(position);

        holder.tv_breakup_desc.setText(model.getBreakupDesc()+" "+context.getResources().getString(R.string.colon));
        holder.tv_breakup_amt.setText( model.getBreakupAmount());
    }


    @Override
    public LifeSaverBreakupRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_recharge_breakup, viewGroup, false);
        listHolder = new LifeSaverBreakupRCVHolder(mainGroup);
        return listHolder;

    }



    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
           // Toast.makeText(context, "Selected Item : " + arrayList.get(selectedPosition).getCompanyName(), Toast.LENGTH_SHORT).show();
            return String.valueOf(selectedPosition);
        }
        return "";
    }

}