package com.techuva.smartmeter.holders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class PostpaidRechargeHistoryRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView tv_bill_date_to, tv_bill_date_from, tv_unit_consumed, tv_bill_amt, tv_due_date, tv_status;
public TextView tv_to, tv_unit_consumed_txt, tv_bill_amt_txt, tv_due_date_txt, tv_status_txt;
public int mSelectedItem;
public PostpaidRechargeHistoryRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.tv_bill_date_to =  view.findViewById(R.id.tv_bill_date_to);
        this.tv_bill_date_from =  view.findViewById(R.id.tv_bill_date_from);
        this.tv_unit_consumed =  view.findViewById(R.id.tv_unit_consumed);
        this.tv_bill_amt =  view.findViewById(R.id.tv_bill_amt);
        this.tv_due_date = view.findViewById(R.id.tv_due_date);
        this.tv_status = view.findViewById(R.id.tv_status);
        this.tv_to = view.findViewById(R.id.tv_to);
        this.tv_unit_consumed_txt = view.findViewById(R.id.tv_unit_consumed_txt);
        this.tv_bill_amt_txt = view.findViewById(R.id.tv_bill_amt_txt);
        this.tv_due_date_txt = view.findViewById(R.id.tv_due_date_txt);
        this.tv_status_txt = view.findViewById(R.id.tv_status_txt);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        Typeface faceMedium = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Medium.otf");
        this.tv_bill_date_to.setTypeface(faceLight);
        this.tv_bill_date_from.setTypeface(faceLight);
        this.tv_to.setTypeface(faceLight);
        this.tv_unit_consumed.setTypeface(faceLight);
        this.tv_bill_amt.setTypeface(faceLight);
        this.tv_due_date.setTypeface(faceLight);
        this.tv_status.setTypeface(faceLight);
        this.tv_unit_consumed_txt.setTypeface(faceLight);
        this.tv_bill_amt_txt.setTypeface(faceLight);
        this.tv_due_date_txt.setTypeface(faceLight);
        this.tv_status_txt.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}