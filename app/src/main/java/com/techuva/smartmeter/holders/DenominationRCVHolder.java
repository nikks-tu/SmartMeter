package com.techuva.smartmeter.holders;

import android.graphics.Typeface;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class DenominationRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public LinearLayout ll_main;
public  TextView tv_denomination_amt;
public DenominationRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.ll_main =  view.findViewById(R.id.ll_main);
        this.tv_denomination_amt =  view.findViewById(R.id.tv_denomination_amt);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        this.tv_denomination_amt.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        //mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}