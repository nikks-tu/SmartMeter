package com.techuva.smartmeter.holders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;


public class AccountsNameRCVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView tv_usn_num;
public TextView tv_balance_amt;
public TextView tv_state;
public TextView tv_connection_type;
public TextView nameInitials;
public RadioButton rb_checked;
public int mSelectedItem;
public LinearLayout ll_user_connection;
public AccountsNameRCVHolder(View view) {
        super(view);
        // Find all views ids
        this.tv_usn_num =  view.findViewById(R.id.tv_usn_num);
        this.tv_balance_amt =  view.findViewById(R.id.tv_balance_amt);
        this.tv_state =  view.findViewById(R.id.tv_state);
        this.tv_connection_type =  view.findViewById(R.id.tv_connection_type);
        this.nameInitials = view.findViewById(R.id.tv_name_initials);
        this.rb_checked = view.findViewById(R.id.rb_accountUsed);
        this.ll_user_connection = view.findViewById(R.id.ll_user_connection);
        Typeface faceLight = Typeface.createFromAsset(view.getResources().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        this.tv_usn_num.setTypeface(faceLight);
        this.nameInitials.setTypeface(faceLight);
        this.tv_balance_amt.setTypeface(faceLight);
        this.tv_state.setTypeface(faceLight);
        this.tv_connection_type.setTypeface(faceLight);
        }

        @Override
        public void onClick(View v) {

        mSelectedItem = getAdapterPosition();
        }

        private void setTypeface() {

        }
}