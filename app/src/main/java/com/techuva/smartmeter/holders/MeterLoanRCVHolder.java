package com.techuva.smartmeter.holders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class MeterLoanRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public LinearLayout ll_main;
public  TextView tv_bill_month;
public  TextView tv_principal_amount;
public  TextView tv_interest_amount;
public  TextView tv_due_date;
public  TextView tv_total_installment;
public  TextView tv_status;
public  TextView tv_pay_btn;
public MeterLoanRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.ll_main =  view.findViewById(R.id.ll_main);
        this.tv_bill_month =  view.findViewById(R.id.tv_bill_month);
        this.tv_principal_amount =  view.findViewById(R.id.tv_principal_amount);
        this.tv_interest_amount =  view.findViewById(R.id.tv_interest_amount);
        this.tv_due_date =  view.findViewById(R.id.tv_due_date);
        this.tv_total_installment =  view.findViewById(R.id.tv_total_installment);
        this.tv_status =  view.findViewById(R.id.tv_status);
        this.tv_pay_btn =  view.findViewById(R.id.tv_pay_btn);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        this.tv_bill_month.setTypeface(faceLight);
        this.tv_principal_amount.setTypeface(faceLight);
        this.tv_interest_amount.setTypeface(faceLight);
        this.tv_due_date.setTypeface(faceLight);
        this.tv_total_installment.setTypeface(faceLight);
        this.tv_status.setTypeface(faceLight);
        this.tv_pay_btn.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        //mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}