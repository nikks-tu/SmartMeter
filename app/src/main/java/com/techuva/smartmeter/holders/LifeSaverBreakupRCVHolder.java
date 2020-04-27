package com.techuva.smartmeter.holders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class LifeSaverBreakupRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public LinearLayout ll_main;
public  TextView tv_breakup_desc;
public  TextView tv_breakup_amt;
public  TextView tv_taxes;
public LifeSaverBreakupRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.ll_main =  view.findViewById(R.id.ll_main);
        this.tv_breakup_desc =  view.findViewById(R.id.tv_breakup_desc);
        this.tv_breakup_amt =  view.findViewById(R.id.tv_breakup_amt);
        this.tv_taxes =  view.findViewById(R.id.tv_taxes);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        this.tv_breakup_desc.setTypeface(faceLight);
        this.tv_breakup_amt.setTypeface(faceLight);
        this.tv_taxes.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        //mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}