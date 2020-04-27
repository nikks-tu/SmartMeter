package com.techuva.smartmeter.holders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class RechargeHistoryRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView tv_date;
public TextView tv_mode;
public TextView tv_balance_amt;
public TextView tv_status_recharge;
public LinearLayout ll_main;
public int mSelectedItem;
public RechargeHistoryRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.tv_date =  view.findViewById(R.id.tv_date);
        this.tv_mode =  view.findViewById(R.id.tv_mode);
        this.tv_balance_amt =  view.findViewById(R.id.tv_balance_amt);
        this.tv_status_recharge = view.findViewById(R.id.tv_status_recharge);
        this.ll_main = view.findViewById(R.id.ll_main);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        this.tv_date.setTypeface(faceLight);
        this.tv_status_recharge.setTypeface(faceLight);
        this.tv_mode.setTypeface(faceLight);
        this.tv_balance_amt.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}