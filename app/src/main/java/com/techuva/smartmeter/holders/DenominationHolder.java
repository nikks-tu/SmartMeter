package com.techuva.smartmeter.holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;

public class DenominationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public LinearLayout ll_main;
    public CardView cv_data;
    public TextView tv_denomination_amt;

    public DenominationHolder(@NonNull View itemView) {
        super(itemView);
        tv_denomination_amt = itemView.findViewById(R.id.tv_denomination_amt);
        ll_main = itemView.findViewById(R.id.ll_main);
        cv_data = itemView.findViewById(R.id.cv_data);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
    }
}