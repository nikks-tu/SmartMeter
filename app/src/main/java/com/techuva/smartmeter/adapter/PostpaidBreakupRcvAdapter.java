package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.PostpaidBreakupRCVHolder;
import com.techuva.smartmeter.responseModel.RechargeCalBreakupObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;


public class PostpaidBreakupRcvAdapter extends RecyclerView.Adapter<PostpaidBreakupRCVHolder>
{

    private ArrayList<RechargeCalBreakupObject> arrayList;
    private Context context;
    private PostpaidBreakupRCVHolder listHolder;
    private String UserName="";
    private int selectedPosition = -1;

    public PostpaidBreakupRcvAdapter(Context context, ArrayList<RechargeCalBreakupObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(PostpaidBreakupRCVHolder holder, int position) {
        RechargeCalBreakupObject model = arrayList.get(position);

        Double breakupRate =  Double.parseDouble(String.valueOf(model.getBreakupRate()));
        if(breakupRate<=0){
            holder.tv_breakup_desc.setText(model.getBreakupDesc());
        }
        else {
            holder.tv_breakup_desc.setText(model.getBreakupDesc()+"("+String.valueOf(breakupRate)+"%)");
        }

      /*  DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
        String amount = df.format(model.getBreakupAmount());*/
       String amount = model.getBreakupAmount();
        //holder.tv_breakup_amt.setText(context.getResources().getString(R.string.currency)+" "+ amount);
        holder.tv_breakup_amt.setText(context.getResources().getString(R.string.currency)+" "+amount);
    }

    @Override
    public PostpaidBreakupRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_postpaid_breakup, viewGroup, false);
        listHolder = new PostpaidBreakupRCVHolder(mainGroup);
        return listHolder;

    }

    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
            return String.valueOf(selectedPosition);
        }
        return "";
    }

}