package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.holders.ConsumedDataDayRCVHolder;
import com.techuva.smartmeter.holders.DenominationHolder;
import com.techuva.smartmeter.holders.DenominationRCVHolder;
import com.techuva.smartmeter.responseModel.DenominationResultObject;
import java.util.List;


public class DenominationAdapter extends RecyclerView.Adapter<DenominationHolder> {

    private List<DenominationResultObject> mData;
    private LayoutInflater mInflater;
    Context context;
    private int selectedPosition = -1;
    private int denominationId=-1;
    private String denominationAmount="";
    DenominationHolder holder;

    // data is passed into the constructor
    public DenominationAdapter(Context context, List<DenominationResultObject> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public DenominationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_denominations, parent, false);


        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyAll();
            }
        });
        holder = new DenominationHolder(parent);
        return new DenominationHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(DenominationHolder holder, int position) {
        DenominationResultObject data = mData.get(position);
        holder.tv_denomination_amt.setTag(data.getDENOMINATIONID());
        holder.tv_denomination_amt.setText(data.getDENOMINATION().toString());
        Typeface faceLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        holder.tv_denomination_amt.setTypeface(faceLight);
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                denominationId = data.getDENOMINATIONID();
                denominationAmount = String.valueOf(data.getDENOMINATION());
                notifyDataSetChanged();

            }
        });
        if(selectedPosition==position){
            holder.cv_data.setCardBackgroundColor(context.getResources().getColor(R.color.dark_blue));
            holder.tv_denomination_amt.setTextColor(context.getResources().getColor(R.color.white));
        }
        else
        {
            holder.cv_data.setCardBackgroundColor(context.getResources().getColor(R.color.text_color_light_op));
            holder.tv_denomination_amt.setTextColor(context.getResources().getColor(R.color.black));
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_denomination_amt;
        LinearLayout ll_main;
        CardView cv_data;

        ViewHolder(View itemView) {
            super(itemView);
            tv_denomination_amt = itemView.findViewById(R.id.tv_denomination_amt);
            ll_main = itemView.findViewById(R.id.ll_main);
            cv_data = itemView.findViewById(R.id.cv_data);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(context, tv_denomination_amt.getText().toString(), Toast.LENGTH_SHORT).show();
            // notifyAll();
        }
    }
    // stores and recycles views as they are scrolled off screen

    // convenience method for getting data at click position
    public String getItem(int id) {
        return String.valueOf(mData.get(id).getDENOMINATION());
    }

    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
            // Toast.makeText(context, "Selected Item : " + arrayList.get(selectedPosition).getCompanyName(), Toast.LENGTH_SHORT).show();
        return denominationAmount;

        }
        return "";
    }
}
