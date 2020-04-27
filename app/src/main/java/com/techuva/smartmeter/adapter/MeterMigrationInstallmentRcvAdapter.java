package com.techuva.smartmeter.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.techuva.smartmeter.R;
import com.techuva.smartmeter.activity.PayInstallmentsActivity;
import com.techuva.smartmeter.constants.Constants;
import com.techuva.smartmeter.holders.MeterLoanRCVHolder;
import com.techuva.smartmeter.responseModel.MeterMigrationEMIDetailObject;
import com.techuva.smartmeter.utils.MApplication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MeterMigrationInstallmentRcvAdapter extends RecyclerView.Adapter<MeterLoanRCVHolder>
{
    private ArrayList<MeterMigrationEMIDetailObject> arrayList;
    private Context context;
    private MeterLoanRCVHolder listHolder;
    private String UserName="";
    private int selectedPosition = -1;
    Activity activity;

    public
    MeterMigrationInstallmentRcvAdapter(Activity activity, Context context, ArrayList<MeterMigrationEMIDetailObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
       return arrayList.size();
    }

    @Override
    public void onBindViewHolder(MeterLoanRCVHolder holder, int position) {
        MeterMigrationEMIDetailObject model = arrayList.get(position);
        String currency = MApplication.getString(context, Constants.CURRENCY);
        if(model.getSHORTTEXT().equals("PAID"))
        {
            holder.tv_pay_btn.setVisibility(View.GONE);
            holder.tv_interest_amount.setText(model.getPaymentReference());
            String dateToParse = model.getPaidOn();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
            Date date = null;
            String dateToStr="";
            try {
                date = sdf.parse(dateToParse);
                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                dateToStr= format.format(date);
                //System.out.println(dateToStr);
                //holder.tv_date_value.setText(dateToStr);
            } catch (android.net.ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            holder.tv_status.setText("Paid on: "+dateToStr);
            holder.tv_total_installment.setText("NGN "+model.getSCHEDULEAMOUNT());
        }
        else {
            holder.tv_status.setText("Status: "+model.getSHORTTEXT());
            holder.tv_total_installment.setText("NGN "+model.getSCHEDULEAMOUNT());
            holder.tv_interest_amount.setText(context.getResources().getString(R.string.na));
        }
        holder.tv_bill_month.setText(model.getSCHEDULEDESCRIPTION());
        String dateToParse = model.getSCHEDULEDATE();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        Date date;
        String dateToStr="";
        try {
            date = sdf.parse(dateToParse);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            dateToStr= format.format(date);
            //System.out.println(dateToStr);
            //holder.tv_date_value.setText(dateToStr);
        } catch (android.net.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        holder.tv_due_date.setText(context.getResources().getString(R.string.due_by, String.valueOf(dateToStr)));
        holder.tv_principal_amount.setText(context.getResources().getString(R.string.payment_ref_));

        holder.tv_pay_btn.setOnClickListener(v -> {
            selectedPosition = position;
            Intent intent = new Intent(context, PayInstallmentsActivity.class);
            intent.putExtra(Constants.MGR_PAYMENT_ID, String.valueOf(arrayList.get(position).getMGRPAYMENTID()));
            intent.putExtra(Constants.INSTALLMENT_TYPE, Constants.MIGRATION);
            activity.startActivity(intent);
        });
    }

    @Override
    public MeterLoanRCVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_meter_loan_installment, viewGroup, false);
        listHolder = new MeterLoanRCVHolder(mainGroup);
        return listHolder;
    }

    public String getSelectedItem() {
        if ( selectedPosition!= -1) {
           return String.valueOf(selectedPosition);
        }
        return "";
    }

}