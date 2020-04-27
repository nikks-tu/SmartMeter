package com.techuva.smartmeter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.activity.HomeActivity;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.holders.AccountsNameRCVHolder;
import com.techuva.smartmeter.responseModel.AccountListResultObject;
import com.techuva.smartmeter.utils.MApplication;

import java.util.ArrayList;


public class AccountListRcvAdapter extends RecyclerView.Adapter<AccountsNameRCVHolder>
{// Recyclerview will extend to
    // recyclerview adapter
    private ArrayList<AccountListResultObject> arrayList;
    private Context context;
    private AccountsNameRCVHolder listHolder;
    private CompoundButton lastCheckedRB = null;
    private String UserName="";
    private int selectedPosition = -1;
    private OnItemClicked listener;
    String usnNum, balance, state, connection_type;

    public AccountListRcvAdapter(Context context, ArrayList<AccountListResultObject> arrayList, OnItemClicked listener) {
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
       // return (null != arrayList ? arrayList.size() : 0);
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(AccountsNameRCVHolder holder, int position) {
        AccountListResultObject model = arrayList.get(position);
        UserName = MApplication.getString(context, "UserName");
        StringBuffer sb = new StringBuffer();
        sb.append(context.getResources().getString(R.string.usn));
        sb.append(" ");
        sb.append(model.getUSNNO());
        usnNum = sb.toString();
        holder.tv_usn_num.setText(usnNum);
        sb = new StringBuffer();
        sb.append(context.getResources().getString(R.string.balance));
        sb.append(" ");
        sb.append(String.format("%.2f", model.getBALANCE()));
        //sb.append(model.getBALANCE());
        balance = sb.toString();
        sb = new StringBuffer();
        sb.append(context.getResources().getString(R.string.district_cap));
        sb.append(" ");
        sb.append(model.getDISTRICTDESCRIPTION());
        state = sb.toString();
        sb = new StringBuffer();
        sb.append(context.getResources().getString(R.string.connection_type_caps));
        sb.append(" ");
        sb.append(model.getCONNECTIONTYPEDESC());
        connection_type = sb.toString();
        holder.tv_balance_amt.setText(balance);
        holder.tv_state.setText(state);
        holder.tv_connection_type.setText(connection_type);

        holder.ll_user_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MApplication.setString(context, Constants.UserID, String.valueOf(model.getUserId()));
                MApplication.setBoolean(context, Constants.IsHomeSelected, false);
                int UserId = Integer.parseInt(MApplication.getString(context, Constants.UserID));
                holder.rb_checked.setChecked(true);
                holder.rb_checked.setTag(position);
                holder.rb_checked.setChecked(position == selectedPosition);
            }
        });
         holder.rb_checked.setOnCheckedChangeListener(ls);
         holder.rb_checked.setTag(position);
         holder.rb_checked.setChecked(position == selectedPosition);

    }

    private CompoundButton.OnCheckedChangeListener ls = (new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int tag = (int) buttonView.getTag();
            if (lastCheckedRB == null) {
                lastCheckedRB = buttonView;
                AccountListRcvAdapter.this.itemCheckChanged(buttonView);
                MApplication.setBoolean(context, Constants.IsHomeSelected, false);
            } else if (tag != (int) lastCheckedRB.getTag()) {
                MApplication.setBoolean(context, Constants.IsHomeSelected, false);
                lastCheckedRB.setChecked(false);
                lastCheckedRB = buttonView;
                AccountListRcvAdapter.this.itemCheckChanged(buttonView);
            }

        }
    });


    public String firstTwo(String str) {
        return str.length() < 2 ? str : str.substring(0, 2);
    }

    @Override
    public AccountsNameRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_account_list, viewGroup, false);
        listHolder = new AccountsNameRCVHolder(mainGroup);
        return listHolder;

    }


    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v  .getTag();
        //Toast.makeText(context, "Changed" +selectedPosition, Toast.LENGTH_SHORT).show();

        if(!getSelectedItem().equals(""))
        {
            int i =  arrayList.get(Integer.parseInt(getSelectedItem())).getINVENTORYID();
            MApplication.setString(context, Constants.INVENTORY_ID, String.valueOf(i));
            MApplication.setBoolean(context, Constants.IsDefaultDeviceSaved, true);
            String balanceAmt = String.format("%.2f", arrayList.get(Integer.parseInt(getSelectedItem())).getBALANCE());
            MApplication.setString(context, Constants.USN_NUM, arrayList.get(Integer.parseInt(getSelectedItem())).getUSNNO());
            MApplication.setString(context, Constants.BALANCE, balanceAmt);
            MApplication.setString(context, Constants.CONNECTION_TYPE, arrayList.get(Integer.parseInt(getSelectedItem())).getCONNECTIONTYPEDESC());
            MApplication.setString(context, Constants.TARIFF, arrayList.get(Integer.parseInt(getSelectedItem())).getTYPEDESCRIPTION());
            MApplication.setString(context, Constants.ZONE, arrayList.get(Integer.parseInt(getSelectedItem())).getSTATEDESCRIPTION());
            MApplication.setString(context, Constants.InventoryName, arrayList.get(Integer.parseInt(getSelectedItem())).getINVENTORYNAME());
            MApplication.setString(context, Constants.Phase_Type, String.valueOf(arrayList.get(Integer.parseInt(getSelectedItem())).getPHASE()));
            MApplication.setString(context, Constants.District_Name, String.valueOf(arrayList.get(Integer.parseInt(getSelectedItem())).getDISTRICTDESCRIPTION()));
            boolean lifeSaver = arrayList.get(Integer.parseInt(getSelectedItem())).getLIFESAVER();
            //Toast.makeText(context, ""+lifeSaver, Toast.LENGTH_SHORT).show();
            MApplication.setBoolean(context, Constants.LIFE_SAVER, lifeSaver);
            //Next Page navigation
            MApplication.setBoolean(context, Constants.IsHomeSelected, true);
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
        else
            Toast.makeText(context, "Please select account to proceed", Toast.LENGTH_SHORT).show();
        /*
        Intent intent = new Intent(context, Dashboard.class);
        context.startActivity(intent);*/
        //notifyDataSetChanged();
    }

    public interface OnItemClicked {
        void onItemClick(View view, int position);
    }

    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
           // Toast.makeText(context, "Selected Item : " + arrayList.get(selectedPosition).getCompanyName(), Toast.LENGTH_SHORT).show();
            return String.valueOf(selectedPosition);
        }
        return "";
    }

}